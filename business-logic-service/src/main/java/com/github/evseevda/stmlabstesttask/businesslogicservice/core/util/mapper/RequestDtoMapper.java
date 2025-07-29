package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;


public interface RequestDtoMapper<E, ID, IN, OUT> {

    E fromRequestDto(IN requestDto);
    E fromRequestDto(ID id, IN requestDto);
    OUT toResponseDto(E entity);

}
