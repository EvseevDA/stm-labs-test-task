package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarrierRequestDto {

    @NotBlank
    @Size(min = 6, max = 128)
    private String companyName;

    @NotBlank
    @Size(min = 11, max = 32)
    private String phone;

}
