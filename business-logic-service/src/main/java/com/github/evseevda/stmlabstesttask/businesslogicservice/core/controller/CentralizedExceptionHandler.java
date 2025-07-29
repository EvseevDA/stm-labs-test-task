package com.github.evseevda.stmlabstesttask.businesslogicservice.core.controller;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.dto.ErrorResponseBody;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.BlServiceException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.security.InvalidJwtException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.exception.TicketAlreadyBoughtException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseBody handleMethodArgumentNotValidException() {
        return ErrorResponseBody.withMessage("Неправильный формат данных");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponseBody handleBadCredentialsException() {
        return ErrorResponseBody.withMessage("Неверный логин или пароль");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponseBody handleAuthenticationException(AuthenticationException e) {
        log.warn(e.getMessage(), e);
        return ErrorResponseBody.withMessage("Ошибка аутентификации");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidJwtException.class)
    public ErrorResponseBody handleInvalidJwtException(InvalidJwtException e) {
        log.warn(e.getMessage(), e);
        return ErrorResponseBody.withMessage("Ошибка аутентификации");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponseBody handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ErrorResponseBody.withMessage("Пользователь с логином %s уже существует".formatted(e.getLogin()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponseBody handleEntityNotFoundException() {
        return ErrorResponseBody.withMessage("Сущность не найдена");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TicketAlreadyBoughtException.class)
    public ErrorResponseBody handleTicketAlreadyBoughtException() {
        return ErrorResponseBody.withMessage("Билет уже куплен");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BlServiceException.class)
    public ErrorResponseBody handleBlServiceException(BlServiceException e) {
        log.error(e.getMessage(), e);
        return ErrorResponseBody.withMessage("Произошла неизвестная ошибка. Повторите ошибку позже.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResponseBody handleAnyException(Throwable e) {
        log.error(e.getMessage(), e);
        return ErrorResponseBody.withMessage("Произошла неизвестная ошибка. Повторите ошибку позже.");
    }

}
