package com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.security.tables.Role.ROLE;

@Component
public class RoleJooqRecordMapper implements JooqRecordMapper<Role> {

    @Override
    public <R extends Record> Role extractEntity(R record) {
        Integer id = record.get(ROLE.ID);
        String name = record.get(ROLE.NAME);
        return new Role(id, name);
    }
}
