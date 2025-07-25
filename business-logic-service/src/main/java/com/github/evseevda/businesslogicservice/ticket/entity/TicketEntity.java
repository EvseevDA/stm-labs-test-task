package com.github.evseevda.businesslogicservice.ticket.entity;

import com.github.evseevda.businesslogicservice.route.entity.RouteEntity;
import com.github.evseevda.businesslogicservice.user.entity.UserEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * TODO Class Description
 *
 * @author Дмитрий Евсеев
 * @since 24.07.2025
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class TicketEntity {

    private Long id;
    private RouteEntity route;
    private LocalDateTime dateTimeUtc;
    private Integer seatNumber;
    private BigDecimal cost;
    private UserEntity passenger;

}
