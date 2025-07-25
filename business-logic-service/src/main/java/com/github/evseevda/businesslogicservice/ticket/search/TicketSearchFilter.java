package com.github.evseevda.businesslogicservice.ticket.search;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketSearchFilter {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private String startPoint;
    private String destinationPoint;
    private String carrierName;

}
