package com.github.evseevda.businesslogicservice.carrier.mapper.request;


import com.github.evseevda.businesslogicservice.carrier.dto.request.CarrierRequestDto;
import com.github.evseevda.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.util.mapper.UserRequestDtoMapper;

public interface CarrierMapper extends UserRequestDtoMapper<Carrier, Long, CarrierRequestDto, CarrierResponseDto> {


}
