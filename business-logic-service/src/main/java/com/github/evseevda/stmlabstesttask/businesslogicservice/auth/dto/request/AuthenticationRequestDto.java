package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request;


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
public class AuthenticationRequestDto {

    @NotBlank
    @Size(min = 5, max = 64)
    private String login;

    @NotBlank
    @Size(min = 10, max = 16)
    private String password;

}
