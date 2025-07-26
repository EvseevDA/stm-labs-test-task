package com.github.evseevda.businesslogicservice.auth.service;

import com.github.evseevda.businesslogicservice.auth.annotation.SetupDefaultRoles;
import com.github.evseevda.businesslogicservice.user.entity.User;
import com.github.evseevda.businesslogicservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(@SetupDefaultRoles User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveNew(user);
    }
}
