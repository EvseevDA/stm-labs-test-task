package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.kafka.listener;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.mapper.PurchasedTicketMapper;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.service.PurchasedTicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PurchasedTicketListenerTest {

    private static final PurchasedTicket MOCKED_PURCHASED_TICKET = PurchasedTicket.builder().build();
    private static final PurchasedTicketKafkaDto MOCKED_KAFKA_DTO = PurchasedTicketKafkaDto.builder().build();

    @Mock
    private PurchasedTicketService purchasedTicketService;
    @Mock
    private PurchasedTicketMapper purchasedTicketMapper;

    @InjectMocks
    private PurchasedTicketListener purchasedTicketListener;

    @Test
    void givenKafkaDto_whenPurchasedTicketListenerHandleTicketPurchase_thenServiceSaveNewIsCalledAndMapperFromKafkaDtoIsCalled() {
        // arrange
        when(purchasedTicketService.saveNew(any(PurchasedTicket.class))).thenReturn(null);
        when(purchasedTicketMapper.fromKafkaDto(any(PurchasedTicketKafkaDto.class))).thenReturn(MOCKED_PURCHASED_TICKET);

        // action
        purchasedTicketListener.handleTicketPurchase(MOCKED_KAFKA_DTO);

        // assertion
        verify(purchasedTicketService).saveNew(any(PurchasedTicket.class));
        verify(purchasedTicketMapper).fromKafkaDto(any(PurchasedTicketKafkaDto.class));
    }

}