package com.github.evseevda.businesslogicservice.user.mapper;


import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.user.entity.User;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.AppUser.APP_USER;

@Component
public class UserJooqRecordMapper implements JooqRecordMapper<User> {

    @Override
    public <R extends Record> User extractEntity(R r) {
        return new User(
                r.get(APP_USER.ID),
                r.get(APP_USER.LOGIN),
                r.get(APP_USER.PASSWORD),
                r.get(APP_USER.FULL_NAME)
        );
    }
}
