package com.github.evseevda.businesslogicservice.auth.util;

import com.github.evseevda.businesslogicservice.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class JwtUtilsImpl implements JwtUtils {

    private final String secret;
    private final Duration accessTokenLifetime;
    private final Duration refreshTokenLifetime;

    @Autowired
    public JwtUtilsImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access.lifetime}") Duration accessTokenLifetime,
            @Value("${jwt.refresh.lifetime}") Duration refreshTokenLifetime
    ) {
        this.secret = secret;
        this.accessTokenLifetime = accessTokenLifetime;
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    @Override
    public String generateAccessToken(User user) {
        return new JwtToken()
                .withSubjectAndLoginClaim(user)
                .withRoleClaim(user.getRole())
                .withLifetime(accessTokenLifetime)
                .signedWith(secret);
    }

    @Override
    public String generateRefreshToken(User user) {
        return new JwtToken()
                .withSubjectAndLoginClaim(user)
                .withLifetime(refreshTokenLifetime)
                .signedWith(secret);
    }

    @Override
    public String getLogin(String token) {
        return JwtToken.Claims.of(token, secret)
                .getClaim("login", String.class);
    }

    @Override
    public String getRole(String token) {
        return JwtToken.Claims.of(token, secret)
                .getClaim("role", String.class);
    }

}
