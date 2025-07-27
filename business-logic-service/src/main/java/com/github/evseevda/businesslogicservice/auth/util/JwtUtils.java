package com.github.evseevda.businesslogicservice.auth.util;


import com.github.evseevda.businesslogicservice.user.entity.User;

public interface JwtUtils {

    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String getLogin(String token);
    String getRole(String token);

}
