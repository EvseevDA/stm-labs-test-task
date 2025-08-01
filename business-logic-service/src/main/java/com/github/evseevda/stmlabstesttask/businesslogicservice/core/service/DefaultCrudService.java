package com.github.evseevda.stmlabstesttask.businesslogicservice.core.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class DefaultCrudService<T extends Entity<ID>, ID> implements CrudService<T, ID> {

    private final CrudRepository<T, ID> crudRepository;

    @Override
    public T saveNew(T entity) {
        return crudRepository.saveNew(entity);
    }

    @Override
    public T update(T entity) {
        ID id = entity.getId();
        if (!existsById(id)) {
            throw new EntityNotFoundException(entityType(), "id", id);
        }
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

    @Override
    public boolean existsById(ID id) {
        return crudRepository.existsById(id);
    }

    protected abstract Class<T> entityType();

}
