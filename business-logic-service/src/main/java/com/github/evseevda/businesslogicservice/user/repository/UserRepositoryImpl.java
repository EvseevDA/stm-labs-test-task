package com.github.evseevda.businesslogicservice.user.repository;

import com.github.evseevda.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.businesslogicservice.user.entity.User;
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
                INSERT INTO bl.app_user (login, password, full_name)
                VALUES
                (:login, :password, :fullName)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "login", user.getLogin(),
                "password", user.getPassword(),
                "fullName", user.getFullName()
        );

        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractEntity);
    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM bl.app_user WHERE id = :userId";
        Map<String, ?> params = Map.of("userId", userId);
        return jdbcTemplate.query(sql, params, userJdbcRecordMapper::extractNullableEntity);
    }

}
