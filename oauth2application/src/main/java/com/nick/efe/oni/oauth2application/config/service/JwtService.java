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

        String decodedHeader = decodeToString(encodedHeader); // works
        String decodedPayload = decodeToString(encodedPayload); // works
        String decodedSignature = decodeToString(encodedSignature); // works?
        System.out.println("Decoded header: " + decodedHeader);
        System.out.println("Decoded payload: " + decodedPayload);
        System.out.println("Decoded signature: " + decodedSignature);

        return null;
    }

    private String decodeToString(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
