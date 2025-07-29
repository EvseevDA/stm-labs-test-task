package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util;

import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class JwtUtilsImpl implements JwtUtils {

    private final String accessTokenSecret;
    private final String refreshTokenSecret;
    private final Duration accessTokenLifetime;
    private final Duration refreshTokenLifetime;

    @Autowired
    public JwtUtilsImpl(
            @Value("${jwt.access.secret}") String accessTokenSecret,
            @Value("${jwt.refresh.secret}") String refreshTokenSecret,
            @Value("${jwt.access.lifetime}") Duration accessTokenLifetime,
            @Value("${jwt.refresh.lifetime}") Duration refreshTokenLifetime
    ) {
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
        this.accessTokenLifetime = accessTokenLifetime;
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    @Override
    public String generateAccessToken(User user) {
        return new JwtToken()
                .withSubjectAndLoginClaim(user)
                .withRoleClaim(user.getRole())
                .withLifetime(accessTokenLifetime)
                .signedWith(accessTokenSecret);
    }

    @Override
    public String generateRefreshToken(User user) {
        return new JwtToken()
                .withSubjectAndLoginClaim(user)
                .withLifetime(refreshTokenLifetime)
                .signedWith(refreshTokenSecret);
    }

    @Override
    public JwtToken.Claims claimsOf(String token, JwtTokenType tokenType) {
        return JwtToken.Claims.of(token, secretByType(tokenType));
    }

    private String secretByType(JwtTokenType tokenType) {
        return switch(tokenType) {
            case ACCESS -> accessTokenSecret;
            case REFRESH -> refreshTokenSecret;
        };
    }

}
