package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PurchasedTicket {

    private Long ticketId;
    private Long passengerId;
    private BigDecimal cost;
    private LocalDateTime purchaseTimestampUtc;

}
