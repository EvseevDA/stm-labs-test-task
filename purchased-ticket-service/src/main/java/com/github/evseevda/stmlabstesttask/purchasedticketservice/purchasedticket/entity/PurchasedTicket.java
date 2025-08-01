package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchasedTicket {

    @EqualsAndHashCode.Include
    private Long ticketId;
    @EqualsAndHashCode.Include
    private Long passengerId;
    private BigDecimal cost;
    private LocalDateTime purchaseTimestampUtc;

}
