package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.aspect;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.exception.TicketAlreadyBoughtException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Order(0)
public class TicketValidationAspect {

    private final TicketService ticketService;
    private final UserService userService;

    @Order(0)
    @Before("""
        execution(* com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.buyTicket(..))
        && args(ticketId, passengerId, ..)
    """)
    public void requireEntitiesExists(Long ticketId, Long passengerId) {
        if (!ticketService.existsById(ticketId)) {
            throw new EntityNotFoundException(Ticket.class, "id", ticketId);
        }
        if (!userService.existsById(passengerId)) {
            throw new EntityNotFoundException(User.class, "id", passengerId);
        }
    }

    @Order(1)
    @Before("""
        execution(* com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.buyTicket(..))
        && args(ticketId, ..)
    """)
    public void requireTicketNotBought(Long ticketId) {
        if (ticketService.hasPassenger(ticketId)) {
            throw new TicketAlreadyBoughtException(ticketId);
        }
    }

    @Before("""
        execution(* com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.hasPassenger(..))
        && args(ticketId, ..)
    """)
    public void requireEntityExists(Long ticketId) {
        if (!ticketService.existsById(ticketId)) {
            throw new EntityNotFoundException(Ticket.class, "id", ticketId);
        }
    }

}
