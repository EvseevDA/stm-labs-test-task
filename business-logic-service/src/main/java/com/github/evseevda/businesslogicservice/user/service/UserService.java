package com.github.evseevda.businesslogicservice.user.service;


import com.github.evseevda.businesslogicservice.user.entity.User;
import com.github.evseevda.businesslogicservice.user.exception.LoginAlreadyExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveNew(User user) throws LoginAlreadyExistsException;
    User findById(Long userId);
    User currentUser();

}
