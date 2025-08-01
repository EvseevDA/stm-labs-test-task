package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Carrier implements Entity<Long> {

    private Long id;
    private String companyName;
    private String phone;

}
