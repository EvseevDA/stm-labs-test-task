package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;

public interface RequestDtoMapper<E extends Entity<ID>, ID, IN, OUT> {

    E fromRequestDto(IN requestDto);
    E fromRequestDto(ID id, IN requestDto);
    OUT toResponseDto(E entity);

}
