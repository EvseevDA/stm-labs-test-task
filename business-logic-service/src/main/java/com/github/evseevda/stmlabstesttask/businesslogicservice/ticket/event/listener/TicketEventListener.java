package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event.listener;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.adapter.KafkaAdapter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.kafka.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.event.TicketPurchasedEvent;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.dto.TicketDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketEventListener {

    private final KafkaAdapter kafkaAdapter;
    private final TicketDtoMapper ticketDtoMapper;
    private final String purchasedTicketTopic;

    @Autowired
    public TicketEventListener(
            KafkaAdapter kafkaAdapter,
            TicketDtoMapper ticketDtoMapper,
            @Value("${spring.kafka.topic.purchased-ticket.name}") String purchasedTicketTopic
    ) {
        this.kafkaAdapter = kafkaAdapter;
        this.ticketDtoMapper = ticketDtoMapper;
        this.purchasedTicketTopic = purchasedTicketTopic;
    }

    @EventListener
    public void handleTicketPurchasedEvent(TicketPurchasedEvent event) {
        Ticket ticket = event.getTicket();
        PurchasedTicketKafkaDto eventDto = ticketDtoMapper
                .toPurchasedTicketKafkaDto(ticket, event.getPurchaseTimestamp());
        kafkaAdapter.send(purchasedTicketTopic, eventDto);
    }

}
