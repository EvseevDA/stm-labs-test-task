package com.github.evseevda.businesslogicservice.core.service;


public interface CrudService<T, ID> {

    T saveNew(T entity);
    T update(T entity);
    void delete(ID id);
    T findById(ID id);

}
