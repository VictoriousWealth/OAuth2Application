package com.nick.efe.oni.oauth2application.config.filter;

import com.nick.efe.oni.oauth2application.config.CustomJwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomJwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    public CustomJwtAuthFilter(RequestMatcher requiresAuth, AuthenticationManager authManager) {
        super(requiresAuth);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            CustomJwtAuthenticationToken authRequest = new CustomJwtAuthenticationToken(token);
            System.out.println("authRequest: " + authRequest);
            return getAuthenticationManager().authenticate(authRequest);
        }

        return null; // No authentication if token is missing
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response); // Continue the request
    }
}
