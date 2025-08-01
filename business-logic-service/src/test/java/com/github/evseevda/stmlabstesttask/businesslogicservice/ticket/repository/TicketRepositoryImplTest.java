package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.config.TestConfig;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request.PageRequest;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(TestConfig.class)
@Sql("/sql/ticket/ticket-test.sql")
class TicketRepositoryImplTest {

    private static final long EXISTENT_TICKET_ID = 27L;
    private static final long EXISTENT_TICKET_ID_1 = 28L;
    private static final long PASSENGER_ID = 27L;
    private static final long ROUTE_ID = 27L;
    private static final long ROUTE_ID_1 = 28L;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void givenUnsavedTicket_whenTicketSaved_thenTicketWithIdReturned() {
        // arrange
        Route route = Route.builder().id(ROUTE_ID).build();
        Ticket ticket = Ticket.builder()
                .route(route)
                .dateTimeUtc(LocalDateTime.of(2025, 8, 2, 10, 0))
                .seatNumber(3)
                .cost(new BigDecimal("200.00"))
                .build();

        // action
        Ticket savedTicket = ticketRepository.saveNew(ticket);

        // assertion
        assertThat(savedTicket.getId()).isNotNull();
    }

    @Test
    void givenNonExistentId_whenFindById_thenOptionalEmptyReturned() {
        // arrange
        Long id = -1L;

        // action
        Optional<Ticket> ticket = ticketRepository.findById(id);

        // assertion
        assertThat(ticket).isEmpty();
    }

    @Test
    void givenExistentId_whenFindById_thenNotEmptyOptionalIsReturned() {
        // arrange
        Long id = EXISTENT_TICKET_ID;

        // action
        Optional<Ticket> ticket = ticketRepository.findById(id);

        // assertion
        assertThat(ticket).isNotEmpty();
    }

    @Test
    void givenUpdatingTicket_whenUpdateExecuted_thenTicketUpdated() {
        // arrange
        Route route = Route.builder().id(ROUTE_ID_1).build();
        LocalDateTime newDateTimeUtc = LocalDateTime.of(2025, 8, 3, 12, 0);
        Integer newSeatNumber = 4;
        BigDecimal newCost = new BigDecimal("250.00");
        Ticket ticket = Ticket.builder()
                .id(EXISTENT_TICKET_ID)
                .route(route)
                .dateTimeUtc(newDateTimeUtc)
                .seatNumber(newSeatNumber)
                .cost(newCost)
                .build();

        // action
        Ticket updatedTicket = ticketRepository.update(ticket);

        // assertion
        assertThat(updatedTicket.getRoute().getId()).isEqualTo(route.getId());
        assertThat(updatedTicket.getDateTimeUtc()).isEqualTo(newDateTimeUtc);
        assertThat(updatedTicket.getSeatNumber()).isEqualTo(newSeatNumber);
        assertThat(updatedTicket.getCost()).isEqualTo(newCost);
    }

    @Test
    void givenExistentId_whenExistsById_thenTrue() {
        // arrange
        Long id = EXISTENT_TICKET_ID;

        // action
        boolean result = ticketRepository.existsById(id);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentId_whenExistsById_thenFalse() {
        // arrange
        Long id = -1L;

        // action
        boolean result = ticketRepository.existsById(id);

        // assertion
        assertThat(result).isFalse();
    }

    @Test
    void givenTicketId_whenDeleteById_thenTicketIsDeleted() {
        // arrange
        Long id = EXISTENT_TICKET_ID;

        // action
        ticketRepository.delete(id);

        // assertion
        assertThat(ticketRepository.existsById(id)).isFalse();
    }

    @Test
    void givenPageRequestAndFilter_whenFindAllAvailableTickets_thenTicketsReturned() {
        // arrange
        PageRequest pageRequest = PageRequest.builder().count(10).page(1).build();
        TicketSearchFilter filter = new TicketSearchFilter();

        // action
        Stream<Ticket> tickets = ticketRepository.findAllAvailableTickets(pageRequest, filter);

        // assertion
        assertThat(tickets).isNotEmpty();
    }

    @Test
    void givenTicketIdAndPassengerId_whenMarkAsBought_thenTicketUpdated() {
        // arrange
        Long ticketId = EXISTENT_TICKET_ID;
        Long passengerId = PASSENGER_ID;

        // action
        Ticket boughtTicket = ticketRepository.markAsBought(ticketId, passengerId);

        // assertion
        assertThat(boughtTicket.getPassenger().getId()).isEqualTo(passengerId);
    }

    @Test
    void givenTicketWithPassenger_whenHasPassenger_thenTrue() {
        // arrange
        Long ticketId = EXISTENT_TICKET_ID_1;

        // action
        boolean result = ticketRepository.hasPassenger(ticketId);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenTicketWithoutPassenger_whenHasPassenger_thenFalse() {
        // arrange
        Long ticketId = EXISTENT_TICKET_ID;

        // action
        boolean result = ticketRepository.hasPassenger(ticketId);

        // assertion
        assertThat(result).isFalse();
    }

    @Test
    void givenPassengerId_whenFindAllTicketsByPassengerId_thenTicketsReturned() {
        // arrange
        Long passengerId = PASSENGER_ID;

        // action
        Stream<Ticket> tickets = ticketRepository.findAllTicketsByPassengerId(passengerId);

        // assertion
        assertThat(tickets).isNotEmpty();
    }

    @Test
    void givenNonExistentPassengerId_whenFindAllTicketsByPassengerId_thenEmptyStreamReturned() {
        // arrange
        Long passengerId = -1L;

        // action
        Stream<Ticket> tickets = ticketRepository.findAllTicketsByPassengerId(passengerId);

        // assertion
        assertThat(tickets).isEmpty();
    }

}