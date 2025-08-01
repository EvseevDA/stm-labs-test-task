package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.mapper;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import org.springframework.stereotype.Component;

@Component
public class PurchasedTicketMapperImpl implements PurchasedTicketMapper {
    @Override
    public PurchasedTicket fromKafkaDto(PurchasedTicketKafkaDto dto) {
        return PurchasedTicket.builder()
                .ticketId(dto.getTicketId())
                .passengerId(dto.getPassengerId())
                .cost(dto.getCost())
                .purchaseTimestampUtc(dto.getPurchaseTimestamp())
                .build();
    }
}
