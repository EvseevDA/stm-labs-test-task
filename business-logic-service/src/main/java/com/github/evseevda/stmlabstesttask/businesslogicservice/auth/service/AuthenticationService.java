package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.AuthenticationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RefreshAccessTokenRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.AuthenticatedUserData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.RefreshedAccessTokenData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;

public interface AuthenticationService {

    User registerNewUser(User user);
    User registerAdmin(User admin);
    AuthenticatedUserData authenticate(AuthenticationRequestDto request);
    RefreshedAccessTokenData refreshAccessToken(RefreshAccessTokenRequestDto request);

}
