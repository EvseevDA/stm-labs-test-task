package com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity;

import lombok.Getter;

@Getter

public class EntityNotFoundException extends EntityException {

    private Class<?> entityClass;
    private String identifierName;
    private Object identifierValue;

    public EntityNotFoundException(Class<?> entityClass) {
        super("%s not found".formatted(entityClass.getSimpleName()));
    }

    public EntityNotFoundException(Class<?> entityClass, String identifierName, Object identifierValue) {
        super("%s not found by %s (%s)".formatted(entityClass.getSimpleName(), identifierName, String.valueOf(identifierValue)));
        this.entityClass = entityClass;
        this.identifierName = identifierName;
        this.identifierValue = identifierValue;
    }

}
