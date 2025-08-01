package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshAccessTokenRequestDto {

    @NotBlank
    private String refreshToken;

}
