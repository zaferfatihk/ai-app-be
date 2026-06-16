package com.ai_app_be.dto.request;

public record CreateUserRequest(String username, String email, String passwordHash) {
}
