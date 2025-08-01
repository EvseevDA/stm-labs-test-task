package com.github.evseevda.stmlabstesttask.businesslogicservice.core.security.config;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenType;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtUtils;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.security.InvalidJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenClaim.LOGIN;
import static com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenClaim.ROLE;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String login = null;
        String jwt = null;

        String bearer = "Bearer ";
        if (authHeader != null && authHeader.startsWith(bearer)) {
            jwt = authHeader.substring(bearer.length());
            login = extractLogin(jwt);
        }

        setAuthentication(login, jwt);

        filterChain.doFilter(request, response);
    }

    private String extractLogin(String token) {
        try {
            return jwtUtils.claimsOf(token, JwtTokenType.ACCESS)
                    .getClaim(LOGIN, String.class);
        } catch (JwtException e) {
            throw new InvalidJwtException("Access token is not valid");
        }
    }

    private void setAuthentication(String login, String jwt) {
        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String role = jwtUtils.claimsOf(jwt, JwtTokenType.ACCESS)
                    .getClaim(ROLE, String.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    login,
                    null,
                    List.of(new SimpleGrantedAuthority(role))
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }

}
