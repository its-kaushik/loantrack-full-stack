package com.example.loantrack.user;

public enum Role {
    ADMIN,
    EMPLOYEE;

    public static Role fromString(String role){
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
    }
}
