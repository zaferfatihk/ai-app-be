package com.ai_app_be.dto.response;

public record RoleResponse(
        Long id,
        String name,
        String description
) {
    public static RoleResponse from(com.ai_app_be.model.Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }
}
