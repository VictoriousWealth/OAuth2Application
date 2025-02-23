package com.nick.efe.oni.oauth2application.config.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService {

    @Value("SECRET_KEY")
    private String secretKey;

    public String validateTokenAndGetUsername(String token) {
        String[] split = token.split("\\.");
        String encodedHeader = split[0];
        String encodedPayload = split[1];
        String encodedSignature = split[2];

        Object decodedPayload = Base64.getUrlDecoder().decode(encodedPayload);

        System.out.println("Decoded payload: " + decodedPayload);

        return null;
    }
}
