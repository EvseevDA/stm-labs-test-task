package com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserJdbcRecordMapper extends AbstractJdbcRecordMapper<User> {

    private final RoleRepository roleRepository;

    @Override
    public User justExtractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String fullName = rs.getString("full_name");
        int roleId = rs.getInt("role_id");

        Role role = roleRepository.findRoleById(roleId).get();

        return new User(id, login, password, fullName, role);
    }
}
