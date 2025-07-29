package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface JdbcRecordMapper<T> {

    T extractNonNullableEntity(ResultSet rs) throws SQLException;
    Optional<T> extractNullableEntity(ResultSet rs) throws SQLException;

}
