package com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.security;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.BlServiceException;

public class InvalidJwtException extends BlServiceException {

    public InvalidJwtException(String message) {
        super(message);
    }

}
