package com.github.evseevda.businesslogicservice.ticket.service;


import com.github.evseevda.businesslogicservice.core.service.CrudService;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.businesslogicservice.ticket.search.TicketSearchFilter;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;

public interface TicketService extends CrudService<Ticket, Long> {

    Stream<Ticket> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter);
    Ticket buyTicket(Long ticketId, Long passengerId);
    Stream<Ticket> findCurrentUserTickets();

}
