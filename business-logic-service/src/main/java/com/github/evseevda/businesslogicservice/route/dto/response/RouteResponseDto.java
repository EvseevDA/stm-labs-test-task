package com.github.evseevda.businesslogicservice.route.dto.response;

import com.github.evseevda.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class RouteResponseDto {

    @EqualsAndHashCode.Include
    private Long id;
    private String startPoint;
    private String destinationPoint;
    private CarrierResponseDto carrier;
    private Long durationInMillis;

}
