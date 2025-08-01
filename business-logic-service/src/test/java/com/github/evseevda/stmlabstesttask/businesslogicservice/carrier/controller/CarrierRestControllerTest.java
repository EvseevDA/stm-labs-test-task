package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.controller;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.dto.request.CarrierRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.mapper.dto.CarrierDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.CrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarrierRestControllerTest {

    private static final Long CARRIER_ID = 1L;
    private static final CarrierRequestDto CARRIER_REQUEST_DTO = CarrierRequestDto.builder().build();
    private static final CarrierResponseDto CARRIER_RESPONSE_DTO = CarrierResponseDto.builder().build();
    private static final Carrier MOCK_CARRIER = Carrier.builder().id(CARRIER_ID).build();

    @Mock
    private CrudService<Carrier, Long> service;

    @Mock
    private CarrierDtoMapper mapper;

    @InjectMocks
    private CarrierRestController carrierRestController;

    @Test
    void givenCarrierRequestDto_whenSaveNewIsCalled_thenMapperAndServiceAreCalledAndResponseIsCreated() {
        // arrange
        when(mapper.fromRequestDto(any(CarrierRequestDto.class))).thenReturn(MOCK_CARRIER);
        when(service.saveNew(any(Carrier.class))).thenReturn(MOCK_CARRIER);
        when(mapper.toResponseDto(any(Carrier.class))).thenReturn(CARRIER_RESPONSE_DTO);

        // action
        ResponseEntity<CarrierResponseDto> response = carrierRestController.saveNew(CARRIER_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(any(CarrierRequestDto.class));
        verify(service).saveNew(any(Carrier.class));
        verify(mapper).toResponseDto(any(Carrier.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void givenCarrierIdAndRequestDto_whenUpdateIsCalled_thenMapperAndServiceAreCalledAndResponseIsOk() {
        // arrange
        when(mapper.fromRequestDto(eq(CARRIER_ID), any(CarrierRequestDto.class))).thenReturn(MOCK_CARRIER);
        when(service.update(any(Carrier.class))).thenReturn(MOCK_CARRIER);
        when(mapper.toResponseDto(any(Carrier.class))).thenReturn(CARRIER_RESPONSE_DTO);

        // action
        ResponseEntity<CarrierResponseDto> response = carrierRestController.update(CARRIER_ID, CARRIER_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(eq(CARRIER_ID), any(CarrierRequestDto.class));
        verify(service).update(any(Carrier.class));
        verify(mapper).toResponseDto(any(Carrier.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenCarrierId_whenDeleteIsCalled_thenServiceDeleteIsCalledAndResponseIsNoContent() {
        // arrange
        doNothing().when(service).delete(CARRIER_ID);

        // action
        ResponseEntity<Void> response = carrierRestController.delete(CARRIER_ID);

        // assertion
        verify(service).delete(CARRIER_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}