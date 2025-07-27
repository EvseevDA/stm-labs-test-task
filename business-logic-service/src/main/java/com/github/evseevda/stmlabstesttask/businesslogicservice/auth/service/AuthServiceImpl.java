package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.aspect.annotation.SetupRole;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(@SetupRole("${security.user.roles.default}") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveNew(user);
    }

    @Override
    public User registerAdmin(@SetupRole("${security.user.roles.admin}") User admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return userService.saveNew(admin);
    }
}
