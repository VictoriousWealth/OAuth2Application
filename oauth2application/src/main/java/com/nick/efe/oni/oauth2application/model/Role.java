package com.nick.efe.oni.oauth2application.model;

public enum Role {
    BASIC,
    INTERMEDIATE,
    ADVANCED,
    MASTERY,
    SUPREME;


    public String getName() {
        return this.name().toUpperCase();
    }
}