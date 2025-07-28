package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.stream.Stream;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Stream<Ticket> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter);
    Optional<Ticket> markAsBought(Long ticketId, Long passengerId);
    Boolean hasPassenger(Long ticketId);
    Stream<Ticket> findAllTicketsByPassengerId(Long passengerId);

}
