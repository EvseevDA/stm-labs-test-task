package com.github.evseevda.businesslogicservice.core.exception.entity;


public class EntityNotFoundException extends EntityException {

    private Class<?> entityClass;
    private String identifierName;
    private String identifierValue;

    public EntityNotFoundException(Class<?> entityClass) {
        super("%s not found".formatted(entityClass.getSimpleName()));
    }

    public EntityNotFoundException(Class<?> entityClass, String identifierName, String identifierValue) {
        super("%s not found by %s (%s)".formatted(entityClass.getSimpleName(), identifierName, identifierValue));
        this.entityClass = entityClass;
        this.identifierName = identifierName;
        this.identifierValue = identifierValue;
    }

}
