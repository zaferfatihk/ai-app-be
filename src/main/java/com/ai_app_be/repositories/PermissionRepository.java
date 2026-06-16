package com.ai_app_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai_app_be.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
}
