package com.ai_app_be.dto;

public record CreateUserRequest(String username, String email, String passwordHash) {
}
