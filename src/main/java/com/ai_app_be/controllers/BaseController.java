package com.ai_app_be.controllers;

import com.ai_app_be.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class BaseController<T, R> {
    private final BaseService<T, Long> service;

    protected BaseController(BaseService<T, Long> service) {
        this.service = service;
    }

    protected abstract R toResponse(T entity);

    @GetMapping
    public List<R> findAll() {
        return service.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
