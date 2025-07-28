package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.aspect;


import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.exception.TicketAlreadyBoughtException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class TicketValidationAspect {

    private final TicketService ticketService;

    @Autowired
    public TicketValidationAspect(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Order(0)
    @Before("""
        execution(* com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.buyTicket(..))
        && args(ticketId, ..)
    """)
    public void requireTicketNotBought(Long ticketId) {
        if (ticketService.hasPassenger(ticketId)) {
            throw new TicketAlreadyBoughtException(ticketId);
        }
    }

}
