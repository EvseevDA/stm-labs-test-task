package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.config;


import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository.PurchasedTicketRepository;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository.PurchasedTicketRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestConfig {

    @Bean
    @Qualifier("purchasedTicketRepositoryImpl")
    public PurchasedTicketRepository purchasedTicketRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new PurchasedTicketRepositoryImpl(jdbcTemplate);
    }

}
