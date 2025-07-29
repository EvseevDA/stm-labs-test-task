package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.mapper;


import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.AuthenticationResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.RefreshAccessTokenResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.AuthenticatedUserData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.RefreshedAccessTokenData;

public interface AuthenticationMapper {

    AuthenticationResponseDto toAuthenticationResponse(AuthenticatedUserData authenticatedUserData);
    RefreshAccessTokenResponseDto toRefreshAccessTokenResponse(RefreshedAccessTokenData refreshedAccessTokenData);

}
