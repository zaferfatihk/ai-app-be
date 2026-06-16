package com.ai_app_be.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public abstract class AbstractBaseService<T, ID> implements BaseService<T, ID> {
    private final JpaRepository<T, ID> repository;

    protected AbstractBaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
