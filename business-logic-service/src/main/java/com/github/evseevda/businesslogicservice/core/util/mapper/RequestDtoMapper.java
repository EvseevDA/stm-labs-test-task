package com.github.evseevda.businesslogicservice.core.util.mapper;


public interface UserRequestDtoMapper<E, ID, IN, OUT> {

    E fromRequestDto(IN requestDto);
    E fromRequestDto(ID id, IN requestDto)
    OUT toResponseDto(E entity);

}
