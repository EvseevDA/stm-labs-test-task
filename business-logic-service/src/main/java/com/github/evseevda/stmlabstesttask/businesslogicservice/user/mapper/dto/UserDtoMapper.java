package com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.dto;


import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RegistrationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.dto.response.DefaultUserResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.dto.response.PassengerDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;

public interface UserDtoMapper {

    PassengerDto toPassengerDto(User user);
    DefaultUserResponseDto toDefaultUserResponseDto(User user);
    User fromRegistrationRequestDto(RegistrationRequestDto requestDto);

}
