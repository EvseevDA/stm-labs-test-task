package com.github.evseevda.businesslogicservice.user.repository;

import com.github.evseevda.businesslogicservice.user.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Optional<Role> findRoleById(Long roleId) {
        String sql = "SELECT * FROM security.role WHERE id = :roleId";
        Map<String, ?> params = Map.of("roleId", roleId);
        return Optional.empty();
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return Optional.empty();
    }
}
