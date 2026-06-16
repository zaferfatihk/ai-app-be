package com.ai_app_be.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai_app_be.dto.request.CreateRoleRequest;
import com.ai_app_be.dto.response.RoleResponse;
import com.ai_app_be.model.Role;
import com.ai_app_be.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, RoleResponse> {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }
    
    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody CreateRoleRequest roleRequest) {
        Role role = new Role(roleRequest.name(), roleRequest.description());
        Role saved = roleService.save(role);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @GetMapping("/by-name")
    public ResponseEntity<RoleResponse> findByName(@RequestParam String name) {
        return roleService.findByName(name)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    protected RoleResponse toResponse(Role role) {
        return RoleResponse.from(role);
    }
    
}
