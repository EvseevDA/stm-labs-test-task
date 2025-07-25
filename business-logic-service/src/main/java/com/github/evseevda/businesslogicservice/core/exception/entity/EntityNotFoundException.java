package com.github.evseevda.businesslogicservice.core.exception;


public class EntityNotFoundException extends BlServiceException {

    private Class<?> entityClass;
    private String identifierName;
    private String identifierValue;

    public EntityNotFoundException(Class<?> entityClass, String identifierName, String identifierValue) {
        this.entityClass = entityClass;
        this.identifierName = identifierName;
        this.identifierValue = identifierValue;
    }

    @Override
    public String getMessage() {
        return "%s not found by %s (%s)".formatted(entityClass.getSimpleName(), identifierName, identifierValue);
    }

}
