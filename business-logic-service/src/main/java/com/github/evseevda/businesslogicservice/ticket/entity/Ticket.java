package com.github.evseevda.businesslogicservice.ticket.entity;

import com.github.evseevda.businesslogicservice.route.entity.Route;
import com.github.evseevda.businesslogicservice.user.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Ticket {

    private Long id;
    private Route route;
    private LocalDateTime dateTimeUtc;
    private Integer seatNumber;
    private BigDecimal cost;
    private User passenger;

}
