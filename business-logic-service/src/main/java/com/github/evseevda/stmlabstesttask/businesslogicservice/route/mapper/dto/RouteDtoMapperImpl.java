package com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.dto;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.mapper.dto.CarrierDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.request.RouteRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.response.RouteResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RouteDtoMapperImpl implements RouteDtoMapper {

    private final CarrierDtoMapper carrierMapper;

    @Override
    public Route fromRequestDto(RouteRequestDto requestDto) {
        return Route.builder()
                .startPoint(requestDto.getStartPoint())
                .destinationPoint(requestDto.getDestinationPoint())
                .carrier(Carrier.builder()
                                .id(requestDto.getCarrierId())
                                .build()
                )
                .durationInMinutes(requestDto.getDurationInMillis())
                .build();
    }

    @Override
    public Route fromRequestDto(Long id, RouteRequestDto requestDto) {
        return Route.builder()
                .id(id)
                .startPoint(requestDto.getStartPoint())
                .destinationPoint(requestDto.getDestinationPoint())
                .carrier(Carrier.builder()
                        .id(requestDto.getCarrierId())
                        .build()
                )
                .durationInMinutes(requestDto.getDurationInMillis())
                .build();
    }

    @Override
    public RouteResponseDto toResponseDto(Route route) {
        return RouteResponseDto.builder()
                .id(route.getId())
                .startPoint(route.getStartPoint())
                .destinationPoint(route.getDestinationPoint())
                .carrier(carrierMapper.toResponseDto(route.getCarrier()))
                .durationInMillis(route.getDurationInMinutes())
                .build();
    }
}
