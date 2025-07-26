package com.github.evseevda.businesslogicservice.carrier.dto.out;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarrierOutputDto {

    @EqualsAndHashCode.Include
    private Long id;
    private String companyName;
    private String phone;

}
