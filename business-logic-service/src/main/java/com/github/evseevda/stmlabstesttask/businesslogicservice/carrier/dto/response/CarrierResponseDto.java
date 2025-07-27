package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarrierResponseDto {

    @EqualsAndHashCode.Include
    private Long id;
    private String companyName;
    private String phone;

}
