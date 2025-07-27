package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;

public interface AuthService {

    User registerNewUser(User user);
    User registerAdmin(User admin);

}
