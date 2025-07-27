package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
