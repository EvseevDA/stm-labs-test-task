package com.github.evseevda.businesslogicservice.carrier.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CarrierEntity {

    private Long id;
    private String companyName;
    private String phone;

}
