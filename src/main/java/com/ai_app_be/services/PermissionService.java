package com.ai_app_be.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai_app_be.model.Permission;
import com.ai_app_be.repositories.PermissionRepository;

@Service
@Transactional(readOnly = true)
public class PermissionService extends AbstractBaseService<Permission, Long> {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository = permissionRepository;
    }
    
}
