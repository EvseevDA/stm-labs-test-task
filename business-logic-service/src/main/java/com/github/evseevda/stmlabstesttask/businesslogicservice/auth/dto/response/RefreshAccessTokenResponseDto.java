package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshAccessTokenResponseDto {

    private String accessToken;

}
