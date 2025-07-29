package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.mapper;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import org.mapstruct.Mapper;

@Mapper
public interface PurchasedTicketMapper {

    PurchasedTicket fromKafkaDto(PurchasedTicketKafkaDto dto);

}
