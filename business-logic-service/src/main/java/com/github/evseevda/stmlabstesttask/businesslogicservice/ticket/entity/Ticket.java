package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Ticket implements Entity<Long> {

    private Long id;
    private Route route;
    private LocalDateTime dateTimeUtc;
    private Integer seatNumber;
    private BigDecimal cost;
    private User passenger;

}
