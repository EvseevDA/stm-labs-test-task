package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<Role> mapper;

    @Override
    public Optional<Role> findRoleById(Integer roleId) {
        String sql = "SELECT * FROM security.role WHERE id = :roleId";
        Map<String, ?> params = Map.of("roleId", roleId);
        return jdbcTemplate.query(sql, params, mapper::extractNullableEntity);
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        String sql = "SELECT * FROM security.role WHERE name = :name";
        Map<String, ?> params = Map.of("name", roleName);
        return jdbcTemplate.query(sql, params, mapper::extractNullableEntity);
    }
}
