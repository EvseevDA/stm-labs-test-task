package com.github.evseevda.stmlabstesttask.businesslogicservice.user.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class PassengerDto {

    @EqualsAndHashCode.Include
    private Long id;
    private String fullName;

}
