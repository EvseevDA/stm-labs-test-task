package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.exception;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.BlServiceException;


public class TicketAlreadyBoughtException extends BlServiceException {

    public TicketAlreadyBoughtException(Long ticketId) {
        super("Ticket with id (%s) already bought");
    }
}
