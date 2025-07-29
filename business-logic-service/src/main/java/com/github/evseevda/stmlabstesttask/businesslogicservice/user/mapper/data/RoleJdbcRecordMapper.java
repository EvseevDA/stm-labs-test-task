package com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleJdbcRecordMapper extends AbstractJdbcRecordMapper<Role> {

    @Override
    public Role extractEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        return new Role(id, name);
    }

}
