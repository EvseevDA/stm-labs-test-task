package com.github.evseevda.businesslogicservice.user.repository;

import com.github.evseevda.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.businesslogicservice.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<User> userJdbcRecordMapper;

    @Override
    @Transactional
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

        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractEntity);
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
        return jdbcTemplate.query(sql, params, rs -> {
                    return rs.getBoolean("exists");
                }
        );
    }
}
