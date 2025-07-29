package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshedAccessTokenData {

    private String accessToken;

}
