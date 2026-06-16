package com.ai_app_be.services;

import java.util.Optional;

import com.ai_app_be.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai_app_be.model.Role;

@Service
@Transactional(readOnly = true)
public class RoleService extends AbstractBaseService<Role, Long> {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }
}
