package com.github.evseevda.businesslogicservice.core.exception;


public class BlServiceException extends RuntimeException {

    public BlServiceException() {
    }

    public BlServiceException(String message) {
        super(message);
    }

    public BlServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
