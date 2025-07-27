package com.github.evseevda.businesslogicservice.core.service;

import com.github.evseevda.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.businesslogicservice.core.repository.CrudRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class DefaultCrudService<T, ID> implements CrudService<T, ID> {

    private final CrudRepository<T, ID> crudRepository;

    @Override
    public T saveNew(T entity) {
        return crudRepository.saveNew(entity);
    }

    @Override
    public T update(T entity) {
        return crudRepository.update(entity);
    }

    @Override
    public void delete(ID id) {
        crudRepository.delete(id);
    }

    @Override
    public T findById(ID id) {
        return crudRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityType(), "id", String.valueOf(id)));
    }
}
