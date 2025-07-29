package com.github.evseevda.stmlabstesttask.purchasedticketservice.kafka.config;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    private final String bootstrapAddress;
    private final Duration backoffInterval;
    private final Integer backoffAttempts;
    private final String purchasedTicketGroupId;

    public KafkaConfig(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapAddress,
            @Value("${spring.kafka.backoff.interval}") Duration backoffInterval,
            @Value("${spring.kafka.backoff.attempts}") Integer backoffAttempts,
            @Value("${spring.kafka.consumer.purchased-ticket-group-id}") String purchasedTicketGroupId
    ) {
        this.bootstrapAddress = bootstrapAddress;
        this.backoffInterval = backoffInterval;
        this.backoffAttempts = backoffAttempts;
        this.purchasedTicketGroupId = purchasedTicketGroupId;
    }

    @Bean
    public ConsumerFactory<String, PurchasedTicketKafkaDto> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress
        );
        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                purchasedTicketGroupId
        );
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(PurchasedTicketKafkaDto.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PurchasedTicketKafkaDto>
    containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PurchasedTicketKafkaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
