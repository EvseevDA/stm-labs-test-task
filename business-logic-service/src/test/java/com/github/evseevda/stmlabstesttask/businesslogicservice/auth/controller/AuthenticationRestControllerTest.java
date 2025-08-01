package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.controller;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.AuthenticationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RefreshAccessTokenRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RegistrationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.AuthenticationResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.RefreshAccessTokenResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.mapper.AuthenticationMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.AuthenticationService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.AuthenticatedUserData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.RefreshedAccessTokenData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.dto.response.DefaultUserResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.dto.UserDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AuthenticationRestControllerTest {

    private static final RegistrationRequestDto REGISTRATION_REQUEST_DTO = RegistrationRequestDto.builder().build();
    private static final AuthenticationRequestDto AUTHENTICATION_REQUEST_DTO = AuthenticationRequestDto.builder().build();
    private static final RefreshAccessTokenRequestDto REFRESH_TOKEN_REQUEST_DTO = RefreshAccessTokenRequestDto.builder().build();
    private static final User MOCK_USER = User.builder().build();
    private static final DefaultUserResponseDto USER_RESPONSE_DTO = DefaultUserResponseDto.builder().build();
    private static final AuthenticatedUserData AUTHENTICATED_USER_DATA = AuthenticatedUserData.builder().build();
    private static final AuthenticationResponseDto AUTHENTICATION_RESPONSE_DTO = AuthenticationResponseDto.builder().build();
    private static final RefreshedAccessTokenData REFRESHED_ACCESS_TOKEN_DATA = RefreshedAccessTokenData.builder().build();
    private static final RefreshAccessTokenResponseDto REFRESH_TOKEN_RESPONSE_DTO = RefreshAccessTokenResponseDto.builder().build();

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationMapper authenticationMapper;
    @Mock
    private UserDtoMapper userMapper;

    @InjectMocks
    private AuthenticationRestController authenticationRestController;

    @Test
    void givenRegistrationRequest_whenRegisterUserIsCalled_thenUserMapperAndAuthenticationServiceAreCalledAndResponseIsCreated() {
        // arrange
        when(userMapper.fromRegistrationRequestDto(any(RegistrationRequestDto.class))).thenReturn(MOCK_USER);
        when(authenticationService.registerNewUser(any(User.class))).thenReturn(MOCK_USER);
        when(userMapper.toDefaultUserResponseDto(any(User.class))).thenReturn(USER_RESPONSE_DTO);

        // action
        ResponseEntity<DefaultUserResponseDto> response = authenticationRestController.registerUser(REGISTRATION_REQUEST_DTO);

        // assertion
        verify(userMapper).fromRegistrationRequestDto(any(RegistrationRequestDto.class));
        verify(authenticationService).registerNewUser(any(User.class));
        verify(userMapper).toDefaultUserResponseDto(any(User.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(USER_RESPONSE_DTO);
    }

    @Test
    void givenRegistrationRequest_whenRegisterAdminIsCalled_thenUserMapperAndAuthenticationServiceAreCalledAndResponseIsCreated() {
        // arrange
        when(userMapper.fromRegistrationRequestDto(any(RegistrationRequestDto.class))).thenReturn(MOCK_USER);
        when(authenticationService.registerAdmin(any(User.class))).thenReturn(MOCK_USER);
        when(userMapper.toDefaultUserResponseDto(any(User.class))).thenReturn(USER_RESPONSE_DTO);

        // action
        ResponseEntity<DefaultUserResponseDto> response = authenticationRestController.registerAdmin(REGISTRATION_REQUEST_DTO);

        // assertion
        verify(userMapper).fromRegistrationRequestDto(any(RegistrationRequestDto.class));
        verify(authenticationService).registerAdmin(any(User.class));
        verify(userMapper).toDefaultUserResponseDto(any(User.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(USER_RESPONSE_DTO);
    }

    @Test
    void givenAuthenticationRequest_whenAuthenticateIsCalled_thenAuthenticationServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        when(authenticationService.authenticate(any(AuthenticationRequestDto.class))).thenReturn(AUTHENTICATED_USER_DATA);
        when(authenticationMapper.toAuthenticationResponse(any(AuthenticatedUserData.class))).thenReturn(AUTHENTICATION_RESPONSE_DTO);

        // action
        ResponseEntity<AuthenticationResponseDto> response = authenticationRestController.authenticate(AUTHENTICATION_REQUEST_DTO);

        // assertion
        verify(authenticationService).authenticate(any(AuthenticationRequestDto.class));
        verify(authenticationMapper).toAuthenticationResponse(any(AuthenticatedUserData.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(AUTHENTICATION_RESPONSE_DTO);
    }

    @Test
    void givenRefreshTokenRequest_whenRefreshAccessTokenIsCalled_thenAuthenticationServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        when(authenticationService.refreshAccessToken(any(RefreshAccessTokenRequestDto.class))).thenReturn(REFRESHED_ACCESS_TOKEN_DATA);
        when(authenticationMapper.toRefreshAccessTokenResponse(any(RefreshedAccessTokenData.class))).thenReturn(REFRESH_TOKEN_RESPONSE_DTO);

        // action
        ResponseEntity<RefreshAccessTokenResponseDto> response = authenticationRestController.refreshAccessToken(REFRESH_TOKEN_REQUEST_DTO);

        // assertion
        verify(authenticationService).refreshAccessToken(any(RefreshAccessTokenRequestDto.class));
        verify(authenticationMapper).toRefreshAccessTokenResponse(any(RefreshedAccessTokenData.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(REFRESH_TOKEN_RESPONSE_DTO);
    }

}