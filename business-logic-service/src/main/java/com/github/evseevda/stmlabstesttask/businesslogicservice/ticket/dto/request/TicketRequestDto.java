package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketRequestDto {

    @NotNull
    @PositiveOrZero
    private Long routeId;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @Positive
    private Integer seatNumber;

    @NotNull
    @Positive
    private BigDecimal cost;

}
