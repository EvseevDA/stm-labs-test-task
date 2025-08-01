package com.github.evseevda.stmlabstesttask.businesslogicservice.route.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RouteRequestDto {

    @NotBlank
    @Size(min = 5, max = 512)
    private String startPoint;

    @NotBlank
    @Size(min = 5, max = 512)
    private String destinationPoint;

    @NotNull
    @PositiveOrZero
    private Long carrierId;

    @NotNull
    @Positive
    private Long durationInMillis;

}
