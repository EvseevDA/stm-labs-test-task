package com.github.evseevda.businesslogicservice.carrier.mapper.dto;

import com.github.evseevda.businesslogicservice.carrier.dto.request.CarrierRequestDto;
import com.github.evseevda.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierDtoMapperImpl implements CarrierDtoMapper {

    @Override
    public Carrier fromRequestDto(CarrierRequestDto requestDto) {
        return Carrier.builder()
                .companyName(requestDto.getCompanyName())
                .phone(requestDto.getPhone())
                .build();
    }

    @Override
    public Carrier fromRequestDto(Long id, CarrierRequestDto requestDto) {
        return Carrier.builder()
                .id(id)
                .companyName(requestDto.getCompanyName())
                .phone(requestDto.getPhone())
                .build();
    }

    @Override
    public CarrierResponseDto toResponseDto(Carrier entity) {
        return new CarrierResponseDto(
                entity.getId(),
                entity.getCompanyName(),
                entity.getPhone()
        );
    }

}
