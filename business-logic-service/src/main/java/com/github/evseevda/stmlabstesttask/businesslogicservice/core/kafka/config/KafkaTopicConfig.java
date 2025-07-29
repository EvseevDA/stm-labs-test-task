package com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    private final String purchasedTicketTopic;

    public KafkaTopicConfig(
            @Value("${spring.kafka.topic.purchased-ticket}") String purchasedTicketTopic
    ) {
        this.purchasedTicketTopic = purchasedTicketTopic;
    }

    @Bean
    public NewTopic boughtTicketTopic() {
        return new NewTopic(purchasedTicketTopic, 3, ((short) 3));
    }

}
