package com.github.evseevda.businesslogicservice.ticket.dto.response;

import com.github.evseevda.businesslogicservice.route.dto.response.RouteResponseDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class TicketResponseDto {

    private Long id;
    private RouteResponseDto route;
    private LocalDateTime dateTime;
    private Integer seatNumber;
    private BigDecimal cost;

}
