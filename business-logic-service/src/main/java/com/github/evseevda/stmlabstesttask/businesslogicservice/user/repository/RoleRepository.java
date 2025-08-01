package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findRoleById(Integer roleId);
    Optional<Role> findRoleByName(String roleName);

}
