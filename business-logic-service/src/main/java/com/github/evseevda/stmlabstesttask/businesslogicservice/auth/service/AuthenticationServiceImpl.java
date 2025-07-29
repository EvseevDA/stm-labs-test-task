package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.aspect.annotation.SetupRole;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.AuthenticationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RefreshAccessTokenRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.AuthenticatedUserData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.RefreshedAccessTokenData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenType;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtUtils;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenClaim.LOGIN;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

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

    @Override
    public AuthenticatedUserData authenticate(AuthenticationRequestDto request) {
        String login = request.getLogin();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, request.getPassword())
        );
        User user = ((User) userService.loadUserByUsername(login));
        return AuthenticatedUserData.builder()
                .accessToken(jwtUtils.generateAccessToken(user))
                .refreshToken(jwtUtils.generateRefreshToken(user))
                .user(user)
                .build();
    }

    @Override
    public RefreshedAccessTokenData refreshAccessToken(RefreshAccessTokenRequestDto request) {
        String login = jwtUtils.claimsOf(request.getRefreshToken(), JwtTokenType.REFRESH)
                .getClaim(LOGIN, String.class);
        User user = ((User) userService.loadUserByUsername(login));
        return RefreshedAccessTokenData.builder()
                .accessToken(jwtUtils.generateAccessToken(user))
                .build();
    }
}
