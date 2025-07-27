package com.github.evseevda.businesslogicservice.user.mapper.dto;

import com.github.evseevda.businesslogicservice.auth.dto.request.RegistrationRequestDto;
import com.github.evseevda.businesslogicservice.user.dto.response.DefaultUserResponseDto;
import com.github.evseevda.businesslogicservice.user.dto.response.PassengerDto;
import com.github.evseevda.businesslogicservice.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapperImpl implements UserDtoMapper {
    @Override
    public PassengerDto toPassengerDto(User user) {
        return new PassengerDto(user.getId(), user.getFullName());
    }

    @Override
    public DefaultUserResponseDto toDefaultUserResponseDto(User user) {
        return DefaultUserResponseDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    public User fromRegistrationRequestDto(RegistrationRequestDto requestDto) {
        return User.builder()
                .login(requestDto.getLogin())
                .password(requestDto.getPassword())
                .fullName(requestDto.getFullName())
                .build();
    }
}
