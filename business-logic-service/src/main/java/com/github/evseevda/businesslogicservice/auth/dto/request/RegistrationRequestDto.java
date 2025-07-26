package com.github.evseevda.businesslogicservice.auth.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationRequestDto {

    @NotBlank
    @Size(min = 5, max = 64)
    private String login;

    @NotBlank
    @Size(min = 10, max = 16)
    private String password;

    @NotBlank
    @Size(min = 3, max = 512)
    private String fullName;

}
