package com.github.evseevda.businesslogicservice.user.service;


import com.github.evseevda.businesslogicservice.user.entity.Role;

public interface RoleService {

    Role findRoleById(Long roleId);
    Role findRoleByName(String roleName);

}
