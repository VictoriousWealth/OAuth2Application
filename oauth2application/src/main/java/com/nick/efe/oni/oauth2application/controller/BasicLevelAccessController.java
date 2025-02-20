package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicLevelAccessController {
    @GetMapping
    public ResponseEntity<String> basicLevelAccess() {
        return ResponseEntity.ok("BasicLevelAccess has been granted");
    }
}
