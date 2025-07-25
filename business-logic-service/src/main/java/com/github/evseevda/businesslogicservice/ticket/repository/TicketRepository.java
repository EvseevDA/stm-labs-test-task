package com.github.evseevda.businesslogicservice.ticket.repository;


import com.github.evseevda.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.businesslogicservice.ticket.entity.TicketEntity;
import com.github.evseevda.businesslogicservice.ticket.search.TicketSearchFilter;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    Stream<TicketEntity> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter);
    void markAsBought(Long ticketId, Long passengerId);
    Stream<TicketEntity> findAllTicketsByPassengerId(Long passengerId);

}
