package com.github.evseevda.businesslogicservice.user.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultUserResponseDto {

    private Long id;
    private String login;
    private String fullName;

}
