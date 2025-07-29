package com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.BlServiceException;

public class EntityException extends BlServiceException {

    public EntityException() {
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
