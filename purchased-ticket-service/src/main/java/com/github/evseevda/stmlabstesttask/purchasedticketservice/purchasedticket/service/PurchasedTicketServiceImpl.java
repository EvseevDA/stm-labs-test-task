package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.service;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository.PurchasedTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PurchasedTicketServiceImpl implements PurchasedTicketService {

    private final PurchasedTicketRepository purchasedTicketRepository;

    @Override
    public PurchasedTicket saveNew(PurchasedTicket purchasedTicket) {
        return purchasedTicketRepository.saveNew(purchasedTicket);
    }
}
