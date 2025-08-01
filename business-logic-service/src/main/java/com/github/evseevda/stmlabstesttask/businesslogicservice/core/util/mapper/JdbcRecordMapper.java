package com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface JdbcRecordMapper<T> {

    /**
     * Реализация не содержит вызова {@link ResultSet#next()}, а просто описывает то, как
     * извлечь сущность из набора
     */
    T justExtractEntity(ResultSet rs) throws SQLException;

    /**
     * Реализация вызывает {@link ResultSet#next()} без проверки возвращенного результата, затем
     * извлекает сущность.<br>
     * Метод создан для случая, когда нужно извлечь ровно одну сущность и точно известно, что в результирующем
     * наборе она будет
     */
    T extractNonNullableEntity(ResultSet rs) throws SQLException;

    /**
     * Реализация метода в зависимости от результата вызова {@link ResultSet#next()}, извлекает сущность из набора,
     * либо возвращает {@link Optional#empty()}.<br>
     * Метод создан для случая, когда нужно извлечь ровно одну сущность и неизвестно, будет она в результирующем
     * наборе или нет.
     */
    Optional<T> extractNullableEntity(ResultSet rs) throws SQLException;

}
