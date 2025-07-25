package com.github.evseevda.businesslogicservice.user.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity {

    private Long id;
    private String login;
    private String password;
    private String fullName;

}
