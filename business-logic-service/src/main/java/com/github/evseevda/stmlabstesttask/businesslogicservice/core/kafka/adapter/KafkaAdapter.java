package com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.adapter;


import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.kafka.PurchasedTicketKafkaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaAdapter {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topicName, PurchasedTicketKafkaDto ticket) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(
                topicName,
                String.valueOf(ticket.getPassengerId()),
                ticket
        );
        kafkaTemplate.send(record);
    }

}
