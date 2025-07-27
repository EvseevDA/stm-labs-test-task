package com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository;


import java.util.Optional;

public interface CrudRepository<T, ID> {

    T saveNew(T entity);
    Optional<T> update(T entity);
    void delete(ID id);
    Optional<T> findById(ID id);

}
