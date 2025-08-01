package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.config.TestConfig;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(TestConfig.class)
class PurchasedTicketRepositoryImplTest {

    private static final PurchasedTicket PURCHASED_TICKET = PurchasedTicket.builder()
            .ticketId(1L)
            .passengerId(1L)
            .purchaseTimestampUtc(LocalDateTime.now())
            .cost(new BigDecimal("290.00"))
            .build();

    @Autowired
    private PurchasedTicketRepository purchasedTicketRepository;

    @Test
    void givenUnsavedPurchasedTicket_whenPurchasedTicketRepositorySaveNewIsCalled_thenPurchasedTicketSavedAndReturned() {
        // action
        PurchasedTicket saved = purchasedTicketRepository.saveNew(PURCHASED_TICKET);

        // assertion
        assertThat(saved).isEqualTo(PURCHASED_TICKET);
    }

}