package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
public class MasterLevelAccessController {
    @GetMapping
    public ResponseEntity<String> masterLevelAccess() {
        return ResponseEntity.ok("MasterLevelAccess has been granted");
    }
}
