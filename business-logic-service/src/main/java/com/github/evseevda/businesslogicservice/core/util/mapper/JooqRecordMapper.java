package com.github.evseevda.businesslogicservice.core.util.mapper;

import org.jooq.Record;


public interface JooqRecordMapper<T> {

    <R extends Record> T extractEntity(R record);

}
