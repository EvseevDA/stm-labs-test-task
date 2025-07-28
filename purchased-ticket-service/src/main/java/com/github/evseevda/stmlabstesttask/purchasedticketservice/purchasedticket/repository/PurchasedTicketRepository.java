package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository;


import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;

public interface PurchasedTicketRepository {

    PurchasedTicket saveNew(PurchasedTicket purchasedTicket);

}
