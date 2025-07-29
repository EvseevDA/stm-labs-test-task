package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.kafka.listener;


import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.mapper.PurchasedTicketMapper;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.service.PurchasedTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchasedTicketListener {

    private final PurchasedTicketService purchasedTicketService;
    private final PurchasedTicketMapper purchasedTicketMapper;

    @KafkaListener(
            topics = "${spring.kafka.topic.purchased-ticket}",
            containerFactory = "containerFactory",
            groupId = "${spring.kafka.consumer.purchased-ticket-group-id}"
    )
    public void handleTicketPurchase(@Payload PurchasedTicketKafkaDto dto) {
        purchasedTicketService.saveNew(purchasedTicketMapper.fromKafkaDto(dto));
    }

}
