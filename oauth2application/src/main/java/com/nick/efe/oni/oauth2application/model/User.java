package com.nick.efe.oni.oauth2application.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "app_user") // 🔥 Fix for reserved keyword issue
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean isEnabled;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return Collections.singleton(role);
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
