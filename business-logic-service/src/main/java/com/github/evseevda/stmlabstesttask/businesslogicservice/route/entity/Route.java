package com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Route implements Entity<Long> {

    private Long id;
    private String startPoint;
    private String destinationPoint;
    private Carrier carrier;
    private Long durationInMinutes;

}
