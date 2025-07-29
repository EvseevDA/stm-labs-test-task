package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveNew(User user) throws UserAlreadyExistsException;
    User findById(Long userId);
    User currentUser();

}
