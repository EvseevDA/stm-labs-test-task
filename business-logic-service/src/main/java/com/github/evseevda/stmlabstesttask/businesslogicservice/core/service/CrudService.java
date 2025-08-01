package com.github.evseevda.stmlabstesttask.businesslogicservice.core.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;

public interface CrudService<T extends Entity<ID>, ID> {

    T saveNew(T entity);
    T update(T entity);
    void delete(ID id);
    T findById(ID id);
    boolean existsById(ID id);

}
