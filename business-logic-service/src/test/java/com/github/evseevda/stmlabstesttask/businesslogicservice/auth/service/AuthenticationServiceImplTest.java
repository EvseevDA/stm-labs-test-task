package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.AuthenticationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RefreshAccessTokenRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtToken;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenClaim;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtTokenType;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util.JwtUtils;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    private static final User USER_WITH_ONLY_PASSWORD = User.builder().password("").build();
    private static final User EMPTY_USER = User.builder().build();
    private static final User MOCK_USER = User.builder().login("1").password("1").build();
    private static final AuthenticationRequestDto EMPTY_AUTH_DATA = AuthenticationRequestDto.builder()
            .login("")
            .password("")
            .build();
    private static final RefreshAccessTokenRequestDto REFRESH_DATA = RefreshAccessTokenRequestDto.builder()
            .refreshToken("")
            .build();

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    void givenRegisteringUser_whenRegisterNewUserIsCalled_thenPasswordEncoderIsUsedAndUserServiceSaveNewIsCalled() {
        // arrange
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("");
        when(userService.saveNew(any(User.class))).thenReturn(EMPTY_USER);

        // action
        authenticationService.registerNewUser(USER_WITH_ONLY_PASSWORD);

        // assertion
        verify(passwordEncoder).encode(any(CharSequence.class));
        verify(userService).saveNew(any(User.class));
    }

    @Test
    void givenRegisteringUser_whenRegisterAdminIsCalled_thenPasswordEncoderIsUsedAndUserServiceSaveNewIsCalled() {
        // arrange
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("");
        when(userService.saveNew(any(User.class))).thenReturn(EMPTY_USER);

        // action
        authenticationService.registerAdmin(USER_WITH_ONLY_PASSWORD);

        // assertion
        verify(passwordEncoder).encode(any(CharSequence.class));
        verify(userService).saveNew(any(User.class));
    }

    @Test
    void givenAuthenticationData_whenAuthenticateIsCalled_thenAuthManagerAuthenticateIsCalledAndAccessAndRefreshTokenGenerateMethodsIsCalled() {
        // arrange
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(null);
        when(userService.loadUserByUsername(anyString())).thenReturn(MOCK_USER);
        when(jwtUtils.generateAccessToken(any(User.class))).thenReturn("");
        when(jwtUtils.generateRefreshToken(any(User.class))).thenReturn("");

        // action
        authenticationService.authenticate(EMPTY_AUTH_DATA);

        // assertion
        verify(authenticationManager).authenticate(any(Authentication.class));
        verify(jwtUtils).generateAccessToken(any(User.class));
        verify(jwtUtils).generateRefreshToken(any(User.class));
    }

    @Test
    void givenRefreshToken_whenRefreshAccessTokenIsCalled_thenJwtsUtilsGenerateAccessTokenIsCalled() {
        // arrange
        JwtToken.Claims mockedClaims = mock(JwtToken.Claims.class);
        when(jwtUtils.claimsOf(anyString(), any(JwtTokenType.class))).thenReturn(mockedClaims);
        when(mockedClaims.getClaim(any(JwtTokenClaim.class), any(Class.class))).thenReturn("");
        when(userService.loadUserByUsername(anyString())).thenReturn(EMPTY_USER);
        when(jwtUtils.generateAccessToken(any(User.class))).thenReturn("");

        // action
        authenticationService.refreshAccessToken(REFRESH_DATA);

        // assertion
        verify(jwtUtils).generateAccessToken(any(User.class));
    }

}