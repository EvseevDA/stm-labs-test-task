package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.controller;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request.PageRequest;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.dto.TicketDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketRestControllerTest {

    private static final Long TICKET_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final TicketRequestDto TICKET_REQUEST_DTO = TicketRequestDto.builder().build();
    private static final TicketResponseDto TICKET_RESPONSE_DTO = TicketResponseDto.builder().build();
    private static final Ticket MOCK_TICKET = Ticket.builder().id(TICKET_ID).build();
    private static final User MOCK_USER = User.builder().id(USER_ID).build();
    private static final PageRequest PAGE_REQUEST = new PageRequest();
    private static final TicketSearchFilter SEARCH_FILTER = new TicketSearchFilter();

    @Mock
    private TicketService service;

    @Mock
    private UserService userService;

    @Mock
    private TicketDtoMapper mapper;

    @InjectMocks
    private TicketRestController ticketRestController;

    @Test
    void givenTicketRequestDto_whenSaveNewIsCalled_thenMapperAndServiceAreCalledAndResponseIsCreated() {
        // arrange
        when(mapper.fromRequestDto(any(TicketRequestDto.class))).thenReturn(MOCK_TICKET);
        when(service.saveNew(any(Ticket.class))).thenReturn(MOCK_TICKET);
        when(mapper.toResponseDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO);

        // action
        ResponseEntity<TicketResponseDto> response = ticketRestController.saveNew(TICKET_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(any(TicketRequestDto.class));
        verify(service).saveNew(any(Ticket.class));
        verify(mapper).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void givenTicketIdAndRequestDto_whenUpdateIsCalled_thenMapperAndServiceAreCalledAndResponseIsOk() {
        // arrange
        when(mapper.fromRequestDto(eq(TICKET_ID), any(TicketRequestDto.class))).thenReturn(MOCK_TICKET);
        when(service.update(any(Ticket.class))).thenReturn(MOCK_TICKET);
        when(mapper.toResponseDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO);

        // action
        ResponseEntity<TicketResponseDto> response = ticketRestController.update(TICKET_ID, TICKET_REQUEST_DTO);

        // assertion
        verify(mapper).fromRequestDto(eq(TICKET_ID), any(TicketRequestDto.class));
        verify(service).update(any(Ticket.class));
        verify(mapper).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenTicketId_whenDeleteIsCalled_thenServiceDeleteIsCalledAndResponseIsNoContent() {
        // arrange
        doNothing().when(service).delete(TICKET_ID);

        // action
        ResponseEntity<Void> response = ticketRestController.delete(TICKET_ID);

        // assertion
        verify(service).delete(TICKET_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void givenPageRequestAndFilter_whenGetAllAvailableTicketsIsCalled_thenServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        Stream<Ticket> mockStream = Stream.of(MOCK_TICKET);
        when(service.findAllAvailableTickets(any(PageRequest.class), any(TicketSearchFilter.class))).thenReturn(mockStream);
        when(mapper.toResponseDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO);

        // action
        ResponseEntity<List<TicketResponseDto>> response = ticketRestController.getAllAvailableTickets(PAGE_REQUEST, SEARCH_FILTER);

        // assertion
        verify(service).findAllAvailableTickets(PAGE_REQUEST, SEARCH_FILTER);
        verify(mapper, atLeastOnce()).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
    }

    @Test
    void givenTicketId_whenBuyTicketIsCalled_thenServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        when(userService.currentUser()).thenReturn(MOCK_USER);
        when(service.buyTicket(TICKET_ID, USER_ID)).thenReturn(MOCK_TICKET);
        when(mapper.toResponseDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO);

        // action
        ResponseEntity<TicketResponseDto> response = ticketRestController.buyTicket(TICKET_ID);

        // assertion
        verify(userService).currentUser();
        verify(service).buyTicket(TICKET_ID, USER_ID);
        verify(mapper).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenNoTickets_whenGetCurrentUserTicketsIsCalled_thenServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        Stream<Ticket> mockStream = Stream.empty();
        when(service.findCurrentUserTickets()).thenReturn(mockStream);

        // action
        ResponseEntity<List<TicketResponseDto>> response = ticketRestController.getCurrentUserTickets();

        // assertion
        verify(service).findCurrentUserTickets();
        verify(mapper, never()).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isEmpty();
    }

    @Test
    void givenCurrentUserTickets_whenGetCurrentUserTicketsIsCalled_thenServiceAndMapperAreCalledAndResponseIsOk() {
        // arrange
        Stream<Ticket> mockStream = Stream.of(MOCK_TICKET);
        when(service.findCurrentUserTickets()).thenReturn(mockStream);
        when(mapper.toResponseDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO);

        // action
        ResponseEntity<List<TicketResponseDto>> response = ticketRestController.getCurrentUserTickets();

        // assertion
        verify(service).findCurrentUserTickets();
        verify(mapper, atLeastOnce()).toResponseDto(any(Ticket.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
    }

}