package com.github.evseevda.businesslogicservice.core.util.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface JdbcRecordMapper<T> {

    T extractEntity(ResultSet rs) throws SQLException;
    Optional<T> extractNullableEntity(ResultSet rs) throws SQLException;

}
