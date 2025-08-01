package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<User> userJdbcRecordMapper;

    @Override
    public User saveNew(User user) {
        String sql = """
                INSERT INTO bl.app_user (login, password, full_name, role_id)
                VALUES
                (:login, :password, :fullName, :roleId)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "login", user.getLogin(),
                "password", user.getPassword(),
                "fullName", user.getFullName(),
                "roleId", user.getRole().getId()
        );

        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractNonNullableEntity);
    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM bl.app_user WHERE id = :userId";
        Map<String, ?> params = Map.of("userId", userId);
        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT * FROM bl.app_user WHERE login = :login";
        Map<String, ?> params = Map.of("login", login);
        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public boolean existsByLogin(String login) {
        String sql = "SELECT EXISTS(SELECT id FROM bl.app_user WHERE login = :login)";
        Map<String, ?> params = Map.of("login", login);
        return jdbcTemplate.queryForObject(sql, params, Boolean.class);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT id FROM bl.app_user WHERE id = :id)";
        Map<String, ?> params = Map.of("id", id);
        return jdbcTemplate.queryForObject(sql, params, Boolean.class);
    }
}
