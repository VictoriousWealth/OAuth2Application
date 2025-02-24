package com.nick.efe.oni.oauth2application.config.filter;

import com.nick.efe.oni.oauth2application.config.CustomJwtAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

public class CustomUserDetailsAuthFilter extends AbstractAuthenticationProcessingFilter {
    public CustomUserDetailsAuthFilter(RequestMatcher requiresAuth, AuthenticationManager authManager) {
        super(requiresAuth);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String token = authHeader.substring("Basic ".length());

            //TODO convert token to jwt and delete customUserDetailsAuthenticationProvider & delete customUserDetailsAuthenticationToken
            //TODO return jwtToken instead and let authenticationManager handle that


            CustomJwtAuthenticationToken authRequest = new CustomJwtAuthenticationToken(token);
            return getAuthenticationManager().authenticate(authRequest);
        }


        return null; // Allow request to continue if no basic is present
    }

}
