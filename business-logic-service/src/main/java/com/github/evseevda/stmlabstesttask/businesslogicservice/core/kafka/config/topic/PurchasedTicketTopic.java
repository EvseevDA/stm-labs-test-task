package com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.config.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchasedTicketTopic {

    private final String name;
    private final int partitions;
    private final short replicationFactor;

    @Autowired
    public PurchasedTicketTopic(
            @Value("${spring.kafka.topic.purchased-ticket.name}") String name,
            @Value("${spring.kafka.topic.purchased-ticket.partitions}") int partitions,
            @Value("${spring.kafka.topic.purchased-ticket.replication-factor}") short replicationFactor
    ) {
        this.name = name;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    @Bean
    public NewTopic purchasedTicket() {
        return new NewTopic(name, partitions, replicationFactor);
    }

}
