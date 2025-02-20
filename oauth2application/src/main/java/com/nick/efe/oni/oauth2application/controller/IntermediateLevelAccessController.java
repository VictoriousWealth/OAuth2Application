package com.nick.efe.oni.oauth2application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/intermediate")
public class IntermediateLevelAccessController {
    @GetMapping
    public ResponseEntity<String> intermediateLevelAccess() {
        return ResponseEntity.ok("IntermediateLevelAccess has been granted");
    }
}

