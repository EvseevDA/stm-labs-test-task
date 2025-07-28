package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.aspect;

import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event.TicketPurchasedEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class TicketEventPublisherAspect {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TicketEventPublisherAspect(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @AfterReturning(
            pointcut = """
                execution(com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket
                com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.buyTicket(..))
            """,
            returning = "ticket"
    )
    public void publishTicketPurchasedEvent(JoinPoint jp, Ticket ticket) {
        eventPublisher.publishEvent(new TicketPurchasedEvent(jp.getTarget(), ticket));
    }

}
