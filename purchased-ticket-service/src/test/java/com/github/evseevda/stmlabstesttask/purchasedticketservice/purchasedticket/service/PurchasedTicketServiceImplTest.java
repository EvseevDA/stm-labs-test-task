package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.service;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository.PurchasedTicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchasedTicketServiceImplTest {

    private static final PurchasedTicket PURCHASED_TICKET = PurchasedTicket.builder()
            .ticketId(1L)
            .passengerId(1L)
            .purchaseTimestampUtc(LocalDateTime.now())
            .cost(new BigDecimal("290.00"))
            .build();

    @Mock
    private PurchasedTicketRepository purchasedTicketRepository;

    @InjectMocks
    private PurchasedTicketServiceImpl purchasedTicketService;

    @Test
    void givenUnsavedPurchasedTicketData_whenSaveNewIsCalled_thenPurchasedTicketRepositorySaveNewIsCalledAndPurchasedTicketIsReturned() {
        // arrange
        when(purchasedTicketRepository.saveNew(any(PurchasedTicket.class))).thenReturn(PURCHASED_TICKET);

        // action
        purchasedTicketService.saveNew(PURCHASED_TICKET);

        // assertion
        verify(purchasedTicketRepository).saveNew(any(PurchasedTicket.class));
    }

}