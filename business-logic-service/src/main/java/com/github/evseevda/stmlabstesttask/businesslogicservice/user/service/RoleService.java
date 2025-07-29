package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;

public interface RoleService {

    Role findRoleById(Long roleId);
    Role findRoleByName(String roleName);

}
