package com.github.evseevda.businesslogicservice.auth.service;


import com.github.evseevda.businesslogicservice.user.entity.User;

public interface AuthService {

    User registerNewUser(User user);

}
