package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request.PageRequest;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository.TicketRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    private static final Long TICKET_ID = 1L;
    private static final Long PASSENGER_ID = 2L;
    private static final Ticket MOCK_TICKET = Ticket.builder().id(TICKET_ID).build();
    private static final User MOCK_USER = User.builder().id(PASSENGER_ID).build();
    private static final PageRequest PAGE_REQUEST = new PageRequest();
    private static final TicketSearchFilter SEARCH_FILTER = new TicketSearchFilter();

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void givenTicket_whenSaveNewIsCalled_thenCrudRepositorySaveNewIsCalled() {
        // arrange
        when(ticketRepository.saveNew(any(Ticket.class))).thenReturn(MOCK_TICKET);

        // action
        ticketService.saveNew(MOCK_TICKET);

        // assertion
        verify(ticketRepository).saveNew(any(Ticket.class));
    }

    @Test
    void givenTicket_whenUpdateIsCalled_andTicketExists_thenCrudRepositoryUpdateIsCalled() {
        // arrange
        when(ticketRepository.existsById(TICKET_ID)).thenReturn(true);
        when(ticketRepository.update(any(Ticket.class))).thenReturn(MOCK_TICKET);

        // action
        ticketService.update(MOCK_TICKET);

        // assertion
        verify(ticketRepository).existsById(TICKET_ID);
        verify(ticketRepository).update(any(Ticket.class));
    }

    @Test
    void givenTicket_whenUpdateIsCalled_andTicketDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(ticketRepository.existsById(TICKET_ID)).thenReturn(false);

        // action & assertion
        assertThatThrownBy(() -> ticketService.update(MOCK_TICKET))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Ticket.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(TICKET_ID));
        verify(ticketRepository).existsById(TICKET_ID);
        verify(ticketRepository, never()).update(any(Ticket.class));
    }

    @Test
    void givenTicketId_whenDeleteIsCalled_thenCrudRepositoryDeleteIsCalled() {
        // arrange
        doNothing().when(ticketRepository).delete(any(Long.class));

        // action
        ticketService.delete(TICKET_ID);

        // assertion
        verify(ticketRepository).delete(TICKET_ID);
    }

    @Test
    void givenTicketId_whenFindByIdIsCalled_andTicketExists_thenCrudRepositoryFindByIdIsCalledAndTicketIsReturned() {
        // arrange
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.of(MOCK_TICKET));

        // action
        Ticket result = ticketService.findById(TICKET_ID);

        // assertion
        verify(ticketRepository).findById(TICKET_ID);
        assertThat(result).isEqualTo(MOCK_TICKET);
    }

    @Test
    void givenTicketId_whenFindByIdIsCalled_andTicketDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> ticketService.findById(TICKET_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Ticket.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(TICKET_ID));
        verify(ticketRepository).findById(TICKET_ID);
    }

    @Test
    void givenTicketId_whenExistsByIdIsCalled_thenCrudRepositoryExistsByIdIsCalledAndResultIsReturned() {
        // arrange
        when(ticketRepository.existsById(TICKET_ID)).thenReturn(true);

        // action
        boolean result = ticketService.existsById(TICKET_ID);

        // assertion
        verify(ticketRepository).existsById(TICKET_ID);
        assertThat(result).isTrue();
    }

    @Test
    void givenPageRequestAndFilter_whenFindAllAvailableTicketsIsCalled_thenTicketRepositoryFindAllAvailableTicketsIsCalled() {
        // arrange
        Stream<Ticket> mockStream = Stream.of(MOCK_TICKET);
        when(ticketRepository.findAllAvailableTickets(any(PageRequest.class), any(TicketSearchFilter.class)))
                .thenReturn(mockStream);

        // action
        Stream<Ticket> result = ticketService.findAllAvailableTickets(PAGE_REQUEST, SEARCH_FILTER);

        // assertion
        verify(ticketRepository).findAllAvailableTickets(PAGE_REQUEST, SEARCH_FILTER);
        assertThat(result).isEqualTo(mockStream);
    }

    @Test
    void givenTicketIdAndPassengerId_whenBuyTicketIsCalled_andTicketIsBought_thenTicketRepositoryMarkAsBoughtIsCalledAndTicketIsReturned() {
        // arrange
        when(ticketRepository.markAsBought(TICKET_ID, PASSENGER_ID)).thenReturn(MOCK_TICKET);

        // action
        Ticket result = ticketService.buyTicket(TICKET_ID, PASSENGER_ID);

        // assertion
        verify(ticketRepository).markAsBought(TICKET_ID, PASSENGER_ID);
        assertThat(result).isEqualTo(MOCK_TICKET);
    }

    @Test
    void givenTicketId_whenHasPassengerIsCalled_thenTicketRepositoryHasPassengerIsCalledAndResultIsReturned() {
        // arrange
        when(ticketRepository.hasPassenger(TICKET_ID)).thenReturn(true);

        // action
        Boolean result = ticketService.hasPassenger(TICKET_ID);

        // assertion
        verify(ticketRepository).hasPassenger(TICKET_ID);
        assertThat(result).isTrue();
    }

    @Test
    void givenCurrentUser_whenFindCurrentUserTicketsIsCalled_thenTicketRepositoryFindAllTicketsByPassengerIdIsCalled() {
        // arrange
        Stream<Ticket> mockStream = Stream.of(MOCK_TICKET);
        when(userService.currentUser()).thenReturn(MOCK_USER);
        when(ticketRepository.findAllTicketsByPassengerId(PASSENGER_ID)).thenReturn(mockStream);

        // action
        Stream<Ticket> result = ticketService.findCurrentUserTickets();

        // assertion
        verify(userService).currentUser();
        verify(ticketRepository).findAllTicketsByPassengerId(PASSENGER_ID);
        assertThat(result).isEqualTo(mockStream);
    }

}