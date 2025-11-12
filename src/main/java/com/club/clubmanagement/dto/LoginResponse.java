package com.club.clubmanagement.dto;

public class LoginResponse {
    public String message;
    public String role;

    public LoginResponse(String message, String role) {
        this.message = message;
        this.role = role;
    }
}
