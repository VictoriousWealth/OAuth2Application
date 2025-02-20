package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supreme")
public class SupremeLevelAccessController {
    @GetMapping
    public ResponseEntity<String> supremeLevelAccess() {
        return ResponseEntity.ok("SupremeLevelAccess has been granted");
    }
}
