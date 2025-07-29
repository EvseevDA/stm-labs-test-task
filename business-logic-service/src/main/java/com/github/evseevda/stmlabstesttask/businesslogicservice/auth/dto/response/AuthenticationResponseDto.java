package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response;

import com.github.evseevda.stmlabstesttask.businesslogicservice.user.dto.response.DefaultUserResponseDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponseDto {

    private String accessToken;
    private String refreshToken;
    private DefaultUserResponseDto user;

}
