package com.ai_app_be.dto.response;

import com.ai_app_be.model.Permission;

public record PermissionResponse(
        Long id,
        String name
) {
    public static PermissionResponse from(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getName()
        );
    }
}
