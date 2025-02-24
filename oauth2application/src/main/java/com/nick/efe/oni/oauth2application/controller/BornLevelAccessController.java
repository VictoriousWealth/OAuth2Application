package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BornLevelAccessController {

    @GetMapping
    public ResponseEntity<String> getBornLevel() {
        return ResponseEntity.ok("Born Level Access achieved");
    }
}
