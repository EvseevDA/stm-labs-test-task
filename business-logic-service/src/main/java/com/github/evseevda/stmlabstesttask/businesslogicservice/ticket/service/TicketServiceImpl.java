package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.DefaultCrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository.TicketRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
public class TicketServiceImpl extends DefaultCrudService<Ticket, Long> implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Autowired
    public TicketServiceImpl(
            CrudRepository<Ticket, Long> crudRepository,
            TicketRepository ticketRepository,
            UserService userService
    ) {
        super(crudRepository);
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    @Override
    public Stream<Ticket> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter) {
        return ticketRepository.findAllAvailableTickets(pageRequest, filter);
    }

    @Override
    public Ticket buyTicket(Long ticketId, Long passengerId) {
        return ticketRepository.markAsBought(ticketId, passengerId)
                .orElseThrow(() -> new EntityNotFoundException(entityType(), "id", String.valueOf(ticketId)));
    }

    @Override
    public Stream<Ticket> findCurrentUserTickets() {
        return ticketRepository.findAllTicketsByPassengerId(userService.currentUser().getId());
    }

    @Override
    public Class<Ticket> entityType() {
        return Ticket.class;
    }
}
