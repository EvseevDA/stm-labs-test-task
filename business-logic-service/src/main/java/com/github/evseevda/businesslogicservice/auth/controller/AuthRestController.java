package com.github.evseevda.businesslogicservice.auth.controller;


import com.github.evseevda.businesslogicservice.auth.dto.request.RegistrationRequestDto;
import com.github.evseevda.businesslogicservice.auth.service.AuthService;
import com.github.evseevda.businesslogicservice.user.dto.response.DefaultUserResponseDto;
import com.github.evseevda.businesslogicservice.user.entity.User;
import com.github.evseevda.businesslogicservice.user.mapper.dto.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;
    private final UserDtoMapper userMapper;

    @PostMapping("/registration/user")
    public ResponseEntity<DefaultUserResponseDto> registerUser(
            @RequestBody @Valid RegistrationRequestDto requestDto
    ) {
        User newUser = userMapper.fromRegistrationRequestDto(requestDto);
        return register(newUser, authService::registerNewUser);
    }

    @PostMapping("/registration/admin")
    public ResponseEntity<DefaultUserResponseDto> registerAdmin(
            @RequestBody @Valid RegistrationRequestDto requestDto
    ) {
        User newUser = userMapper.fromRegistrationRequestDto(requestDto);
        return register(newUser, authService::registerAdmin);
    }

    private ResponseEntity<DefaultUserResponseDto> register(User newUser, Function<User, User> registerMethod) {
        User registered = registerMethod.apply(newUser);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(userMapper.toDefaultUserResponseDto(registered));
    }

}
