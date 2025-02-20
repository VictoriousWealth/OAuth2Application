package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advanced")
public class AdvancedLevelAccessController {
    @GetMapping
    public ResponseEntity<String> advancedLevelAccess() {
        return ResponseEntity.ok("AdvancedLevelAccess has been granted");
    }
}
