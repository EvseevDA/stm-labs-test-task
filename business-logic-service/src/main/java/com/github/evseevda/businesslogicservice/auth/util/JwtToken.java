package com.github.evseevda.businesslogicservice.auth.util;

import com.github.evseevda.businesslogicservice.user.entity.Role;
import com.github.evseevda.businesslogicservice.user.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtToken {

    private final Map<String, Object> claims = new HashMap<>();
    private final JwtBuilder builder = Jwts.builder();

    JwtToken withClaim(String name, Object value) {
        claims.put(name, value);
        return this;
    }

    JwtToken withSubjectAndLoginClaim(User user) {
        builder.subject(user.getLogin());
        return withClaim("login", user.getLogin());
    }

    JwtToken withRoleClaim(Role role) {
        return withClaim("role", role.getName());
    }

    JwtToken withLifetime(Duration lifetime) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + lifetime.toMillis());
        builder
                .issuedAt(issuedAt)
                .expiration(expiration);
        return this;
    }

    String signedWith(String secret) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        builder.signWith(key);
        return builder
                .claims(claims)
                .compact();
    }

    public static class Claims {

        private final io.jsonwebtoken.Claims claims;

        private Claims(io.jsonwebtoken.Claims claims) {
            this.claims = claims;
        }

        public static Claims of(String token, String secret) {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            io.jsonwebtoken.Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return new Claims(claims);
        }

        public <T> T getClaim(String claimName, Class<T> requiredType) {
            return claims.get(claimName, requiredType);
        }

    }

}
