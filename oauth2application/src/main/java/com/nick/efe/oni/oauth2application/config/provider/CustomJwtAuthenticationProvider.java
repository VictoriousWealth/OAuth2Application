package com.nick.efe.oni.oauth2application.config.provider;

import com.nick.efe.oni.oauth2application.config.CustomJwtAuthenticationToken;
import com.nick.efe.oni.oauth2application.config.service.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService; // Your custom JWT validation service

    public CustomJwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Authenticating " + authentication.getPrincipal());
        String token = (String) authentication.getCredentials();
        String username = jwtService.validateTokenAndGetUsername(token);

        if (username == null) {
            throw new BadCredentialsException("Invalid JWT Token");
        }

        return new CustomJwtAuthenticationToken(token, username); // Successfully authenticated
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomJwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
