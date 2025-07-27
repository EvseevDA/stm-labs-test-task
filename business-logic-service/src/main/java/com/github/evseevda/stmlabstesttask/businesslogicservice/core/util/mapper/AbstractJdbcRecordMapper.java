package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractJdbcRecordMapper<T> implements JdbcRecordMapper<T> {

    public abstract T extractEntity(ResultSet rs) throws SQLException;

    public Optional<T> extractNullableEntity(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(extractEntity(rs));
        } else {
            return Optional.empty();
        }
    }

}
