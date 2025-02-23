package com.nick.efe.oni.oauth2application.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collections;

public class CustomJwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final String username;

    public CustomJwtAuthenticationToken(String token) {
        super(Collections.emptyList()); // No authorities initially
        this.token = token;
        this.username = null; // Initially unknown
        setAuthenticated(false); // Not authenticated at first
    }

    public CustomJwtAuthenticationToken(String token, String username) {
        super(Collections.emptyList()); // You can add roles later
        this.token = token;
        this.username = username;
        setAuthenticated(true); // Now authenticated
    }

    @Override
    public Object getCredentials() {
        return token; // The JWT token itself
    }

    @Override
    public Object getPrincipal() {
        return username; // Extracted username from the token
    }
}
