package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request.PageRequest;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.CrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;

import java.util.stream.Stream;

public interface TicketService extends CrudService<Ticket, Long> {

    Stream<Ticket> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter);
    Ticket buyTicket(Long ticketId, Long passengerId);
    Boolean hasPassenger(Long ticketId);
    Stream<Ticket> findCurrentUserTickets();

}
