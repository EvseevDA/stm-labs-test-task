package com.github.evseevda.businesslogicservice.carrier.mapper.dto;


import com.github.evseevda.businesslogicservice.carrier.dto.request.CarrierRequestDto;
import com.github.evseevda.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;

public interface CarrierDtoMapper
        extends RequestDtoMapper<Carrier, Long, CarrierRequestDto, CarrierResponseDto> {

}
