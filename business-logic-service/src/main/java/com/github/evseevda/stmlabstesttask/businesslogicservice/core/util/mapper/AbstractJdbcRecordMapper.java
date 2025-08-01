package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractJdbcRecordMapper<T> implements JdbcRecordMapper<T> {

    @Override
    public T extractNonNullableEntity(ResultSet rs) throws SQLException {
        rs.next();
        return justExtractEntity(rs);
    }

    @Override
    public Optional<T> extractNullableEntity(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(justExtractEntity(rs));
        } else {
            return Optional.empty();
        }
    }

}
