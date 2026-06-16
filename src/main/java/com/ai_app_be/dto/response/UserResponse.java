package com.ai_app_be.dto.response;

import java.time.Instant;

import com.ai_app_be.model.User;

public record UserResponse(
        Long id,
        Long version,
        String username,
        String email,
        boolean enabled,
        Instant createdAt,
        Instant updatedAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getVersion(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
