package com.github.evseevda.businesslogicservice.route.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
