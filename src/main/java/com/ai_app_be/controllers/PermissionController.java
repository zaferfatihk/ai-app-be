package com.ai_app_be.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ai_app_be.dto.response.PermissionResponse;
import com.ai_app_be.model.Permission;
import com.ai_app_be.services.PermissionService;

@RestController
public class PermissionController extends BaseController <Permission, PermissionResponse> {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        super(permissionService);
        this.permissionService = permissionService;
    }

    @Override
    protected PermissionResponse toResponse(Permission permission) {
        return PermissionResponse.from(permission);
    }
    
}
