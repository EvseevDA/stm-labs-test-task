package com.github.evseevda.businesslogicservice.carrier.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Carrier {

    private Long id;
    private String companyName;
    private String phone;

}
