package com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.bl.tables.AppUser.APP_USER;

@Component
@RequiredArgsConstructor
public class UserJooqRecordMapper implements JooqRecordMapper<User> {

    private final JooqRecordMapper<Role> roleJooqRecordMapper;

    @Override
    public <R extends Record> User extractEntity(R r) {
        return new User(
                r.get(APP_USER.ID),
                r.get(APP_USER.LOGIN),
                r.get(APP_USER.PASSWORD),
                r.get(APP_USER.FULL_NAME),
                roleJooqRecordMapper.extractEntity(r)
        );
    }
}
