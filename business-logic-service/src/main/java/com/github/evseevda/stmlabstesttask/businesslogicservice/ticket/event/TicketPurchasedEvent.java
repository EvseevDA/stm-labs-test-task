package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event;


import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class TicketPurchasedEvent extends ApplicationEvent {

    private final Ticket ticket;
    private final LocalDateTime purchaseTimestamp;

    public TicketPurchasedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
        this.purchaseTimestamp = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
    }
}
