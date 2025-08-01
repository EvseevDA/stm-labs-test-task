package com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class User implements UserDetails, Entity<Long> {

    private Long id;
    private String login;
    private String password;
    private String fullName;
    private Role role;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }
}
