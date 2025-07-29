package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshAccessTokenRequestDto {

    @NotBlank
    private String refreshToken;

}
