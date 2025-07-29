package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticatedUserData {

    private String accessToken;
    private String refreshToken;
    private User user;

}
