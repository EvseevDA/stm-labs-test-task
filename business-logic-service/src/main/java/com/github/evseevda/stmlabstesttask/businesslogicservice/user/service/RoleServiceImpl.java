package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleById(Integer roleId) {
        return roleRepository.findRoleById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, "id", String.valueOf(roleId)));
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, "name", roleName));
    }
}
