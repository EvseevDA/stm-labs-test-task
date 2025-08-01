package com.github.evseevda.stmlabstesttask.purchasedticketservice.kafka.config;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

    private final String bootstrapAddress;
    private final Integer backoffAttempts;
    private final String purchasedTicketGroupId;

    public KafkaConfig(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapAddress,
            @Value("${spring.kafka.backoff.attempts}") Integer backoffAttempts,
            @Value("${spring.kafka.consumer.purchased-ticket-group-id}") String purchasedTicketGroupId
    ) {
        this.bootstrapAddress = bootstrapAddress;
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

    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler(
                (consumerRecord, e) -> log.error(errorMessage(consumerRecord), e),
                new ExponentialBackOffWithMaxRetries(backoffAttempts)
        );
    }

    private String errorMessage(ConsumerRecord<?, ?> consumerRecord) {
        return "Kafka error occurred. Consumer record: %s.".formatted(consumerRecord);
    }

}
