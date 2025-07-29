package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.mapper;

import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.AuthenticationResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.response.RefreshAccessTokenResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.AuthenticatedUserData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.output.RefreshedAccessTokenData;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.dto.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthenticationMapperImpl implements AuthenticationMapper {

    private final UserDtoMapper userDtoMapper;

    @Override
    public AuthenticationResponseDto toAuthenticationResponse(AuthenticatedUserData authenticatedUserData) {
        return AuthenticationResponseDto.builder()
                .accessToken(authenticatedUserData.getAccessToken())
                .refreshToken(authenticatedUserData.getRefreshToken())
                .user(userDtoMapper.toDefaultUserResponseDto(authenticatedUserData.getUser()))
                .build();
    }

    @Override
    public RefreshAccessTokenResponseDto toRefreshAccessTokenResponse(RefreshedAccessTokenData refreshedAccessTokenData) {
        return RefreshAccessTokenResponseDto.builder()
                .accessToken(refreshedAccessTokenData.getAccessToken())
                .build();
    }
}
