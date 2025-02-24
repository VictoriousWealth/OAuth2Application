package com.nick.efe.oni.oauth2application.config.filter;

import com.nick.efe.oni.oauth2application.config.CustomJwtAuthenticationToken;
import com.nick.efe.oni.oauth2application.config.service.JwtService;
import com.nick.efe.oni.oauth2application.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class CustomJwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public CustomJwtAuthFilter(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/")) {
            filterChain.doFilter(request, response);
        } else if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            String token = request.getHeader("Authorization").substring("Bearer ".length());
            System.out.println("token: " + token);
            CustomJwtAuthenticationToken authentication = new CustomJwtAuthenticationToken(token);
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            filterChain.doFilter(request, response);
        } else if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Basic ")) {
            String token = new String(Base64.getUrlDecoder().decode(request.getHeader("Authorization").substring("Basic ".length())));
            System.out.println("username:password " + token);

            String username = token.split(":")[0];
            if (userRepository.findByEmail(username).isPresent()) {
                System.out.println("User found");
                String role = userRepository.findByEmail(username).get().getRoles().toArray()[0].toString();
                try {
                    token = jwtService.generateToken(username, role);
                    System.out.println("token generated successfully :" + token);
                    CustomJwtAuthenticationToken authentication = new CustomJwtAuthenticationToken(token);
                    Authentication authResult = authenticationManager.authenticate(authentication);
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    System.out.println("NoSuchAlgorithmException or InvalidKeyException");
                    throw new ServletException(e);
                }
            } else {
                System.out.println("Invalid username or password");
                throw new ServletException("Invalid username or password");
            }
        } else {
            throw new ServletException("Unauthorized");
        }
    }
}
