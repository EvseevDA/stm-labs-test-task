package com.github.evseevda.businesslogicservice.user.mapper;

import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.businesslogicservice.user.entity.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserJdbcRecordMapper extends AbstractJdbcRecordMapper<User> {

    @Override
    public User extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String fullName = rs.getString("full_name");

        return new User(id, login, password, fullName);
    }
}
