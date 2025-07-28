package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PurchasedTicketKafkaDto {

    private Long ticketId;
    private Long passengerId;
    private BigDecimal cost;
    private LocalDateTime purchaseTimestamp;

}
