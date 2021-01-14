package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {

    ROLE_USER("User"),
    ROLE_ADMIN("Administrator");

    private final String name;

    public String getName() {
        return name;
    }
}
