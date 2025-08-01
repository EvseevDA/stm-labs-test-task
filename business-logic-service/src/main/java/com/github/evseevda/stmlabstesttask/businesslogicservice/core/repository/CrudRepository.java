package com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;

import java.util.Optional;

public interface CrudRepository<T extends Entity<ID>, ID> {

    T saveNew(T entity);
    T update(T entity);
    void delete(ID id);
    Optional<T> findById(ID id);
    boolean existsById(ID id);

}
