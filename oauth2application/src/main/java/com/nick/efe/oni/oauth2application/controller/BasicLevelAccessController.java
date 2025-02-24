package com.nick.efe.oni.oauth2application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicLevelAccessController {
    @GetMapping
    public ResponseEntity<String> basicLevelAccess(HttpServletRequest request) {
        System.out.println("Basic Level Access authorization header that has been detected "+request.getHeader("Authorization"));
        return ResponseEntity.ok("BasicLevelAccess has been granted");
    }
}
