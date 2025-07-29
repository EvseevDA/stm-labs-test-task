package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event.listener;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.adapter.KafkaAdapter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.kafka.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event.TicketPurchasedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketEventListener {

    private final KafkaAdapter kafkaAdapter;
    private final String purchasedTicketTopic;

    @Autowired
    public TicketEventListener(
            KafkaAdapter kafkaAdapter,
            @Value("${spring.kafka.topic.purchased-ticket.name}") String purchasedTicketTopic
    ) {
        this.kafkaAdapter = kafkaAdapter;
        this.purchasedTicketTopic = purchasedTicketTopic;
    }

    @EventListener
    public void handleTicketPurchasedEvent(TicketPurchasedEvent event) {
        PurchasedTicketKafkaDto eventDto = PurchasedTicketKafkaDto.builder()
                .ticketId(event.getTicket().getId())
                .purchaseTimestamp(event.getPurchaseTimestamp())
                .build();
        kafkaAdapter.send(purchasedTicketTopic, eventDto);
    }

}
