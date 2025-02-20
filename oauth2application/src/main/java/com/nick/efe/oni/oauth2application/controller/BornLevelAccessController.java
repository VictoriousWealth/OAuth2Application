package com.nick.efe.oni.oauth2application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BornLevelAccessController {

    @GetMapping
    public String getBornLevel() {
        return "Born Level Access achieved";
    }
}
