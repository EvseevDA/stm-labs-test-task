package com.github.evseevda.stmlabstesttask.businesslogicservice.route.controller;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.CrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.request.RouteRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.response.RouteResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.dto.RouteDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteRestControllerTest {

    private static final Long ROUTE_ID = 1L;
    private static final RouteRequestDto ROUTE_REQUEST_DTO = RouteRequestDto.builder().build();
    private static final RouteResponseDto ROUTE_RESPONSE_DTO = RouteResponseDto.builder().build();
    private static final Route MOCK_ROUTE = Route.builder().id(ROUTE_ID).build();

    @Mock
    private CrudService<Route, Long> service;

    @Mock
    private RouteDtoMapper mapper;

    @InjectMocks
    private RouteRestController routeRestController;

    @Test
    void givenRouteRequestDto_whenSaveNewIsCalled_thenMapperAndServiceAreCalledAndResponseIsCreated() {
        // arrange
        when(mapper.fromRequestDto(any(RouteRequestDto.class))).thenReturn(MOCK_ROUTE);
        when(service.saveNew(any(Route.class))).thenReturn(MOCK_ROUTE);
        when(mapper.toResponseDto(any(Route.class))).thenReturn(ROUTE_RESPONSE_DTO);

        // action
        ResponseEntity<RouteResponseDto> response = routeRestController.saveNew(ROUTE_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(any(RouteRequestDto.class));
        verify(service).saveNew(any(Route.class));
        verify(mapper).toResponseDto(any(Route.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void givenRouteIdAndRequestDto_whenUpdateIsCalled_thenMapperAndServiceAreCalledAndResponseIsOk() {
        // arrange
        when(mapper.fromRequestDto(eq(ROUTE_ID), any(RouteRequestDto.class))).thenReturn(MOCK_ROUTE);
        when(service.update(any(Route.class))).thenReturn(MOCK_ROUTE);
        when(mapper.toResponseDto(any(Route.class))).thenReturn(ROUTE_RESPONSE_DTO);

        // action
        ResponseEntity<RouteResponseDto> response = routeRestController.update(ROUTE_ID, ROUTE_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(eq(ROUTE_ID), any(RouteRequestDto.class));
        verify(service).update(any(Route.class));
        verify(mapper).toResponseDto(any(Route.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenRouteId_whenDeleteIsCalled_thenServiceDeleteIsCalledAndResponseIsNoContent() {
        // arrange
        doNothing().when(service).delete(ROUTE_ID);

        // action
        ResponseEntity<Void> response = routeRestController.delete(ROUTE_ID);

        // assertion
        verify(service).delete(ROUTE_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}