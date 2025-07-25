package com.github.evseevda.businesslogicservice.route.entity;

import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class RouteEntity {

    private Long id;
    private String startPoint;
    private String destinationPoint;
    private CarrierEntity carrier;
    private Long durationInMinutes;

}
