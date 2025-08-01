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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    private final AuthenticationMapper authenticationMapper;
    private final UserDtoMapper userMapper;

    @Operation(
            summary = "Регистрация пользователя с правами покупателя"
    )
    @PostMapping("/registration/user")
    public ResponseEntity<DefaultUserResponseDto> registerUser(
            @RequestBody @Valid RegistrationRequestDto requestDto
    ) {
        return register(requestDto, authenticationService::registerNewUser);
    }

    @Operation(
            summary = "Регистрация пользователя с правами администратора"
    )
    @PostMapping("/registration/admin")
    public ResponseEntity<DefaultUserResponseDto> registerAdmin(
            @RequestBody @Valid RegistrationRequestDto requestDto
    ) {
        return register(requestDto, authenticationService::registerAdmin);
    }

    private ResponseEntity<DefaultUserResponseDto> register(
            RegistrationRequestDto requestDto,
            Function<User, User> registerMethod
    ) {
        User registered = registerMethod.apply(userMapper.fromRegistrationRequestDto(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(userMapper.toDefaultUserResponseDto(registered));
    }

    @Operation(
            summary = "Аутентификация"
    )
    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody @Valid AuthenticationRequestDto request
    ) {
        AuthenticatedUserData authData = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationMapper.toAuthenticationResponse(authData));
    }

    @Operation(
            summary = "Обновление access токена"
    )
    @PostMapping("/new-access-token")
    public ResponseEntity<RefreshAccessTokenResponseDto> refreshAccessToken(
            @RequestBody @Valid RefreshAccessTokenRequestDto request
    ) {
        RefreshedAccessTokenData tokenData = authenticationService.refreshAccessToken(request);
        return ResponseEntity.ok(authenticationMapper.toRefreshAccessTokenResponse(tokenData));
    }

}
