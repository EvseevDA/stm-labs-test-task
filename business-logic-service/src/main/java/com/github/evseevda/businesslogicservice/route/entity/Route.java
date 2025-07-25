package com.github.evseevda.businesslogicservice.route.entity;

import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Route {

    private Long id;
    private String startPoint;
    private String destinationPoint;
    private Carrier carrier;
    private Long durationInMinutes;

}
