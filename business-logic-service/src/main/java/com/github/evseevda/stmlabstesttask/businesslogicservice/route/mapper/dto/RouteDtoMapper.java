package com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.dto;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.request.RouteRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.response.RouteResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;

public interface RouteDtoMapper
        extends RequestDtoMapper<Route, Long, RouteRequestDto, RouteResponseDto> {

}
