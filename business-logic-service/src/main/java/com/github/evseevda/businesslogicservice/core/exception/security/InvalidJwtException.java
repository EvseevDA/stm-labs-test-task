package com.github.evseevda.businesslogicservice.core.exception.security;


import com.github.evseevda.businesslogicservice.core.exception.BlServiceException;

public class InvalidJwtException extends BlServiceException {

    public InvalidJwtException(String message) {
        super(message);
    }

}
