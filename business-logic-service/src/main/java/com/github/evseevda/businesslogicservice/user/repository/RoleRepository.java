package com.github.evseevda.businesslogicservice.user.repository;


import com.github.evseevda.businesslogicservice.user.entity.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findRoleById(Long roleId);
    Optional<Role> findRoleByName(String roleName);

}
