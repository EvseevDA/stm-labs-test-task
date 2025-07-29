package com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends EntityException {

    private String login;

    public UserAlreadyExistsException(String login) {
        this.login = login;
    }

    @Override
    public String getMessage() {
        return User.class.getSimpleName() + " with login (%s) already exists".formatted(login);
    }
}
