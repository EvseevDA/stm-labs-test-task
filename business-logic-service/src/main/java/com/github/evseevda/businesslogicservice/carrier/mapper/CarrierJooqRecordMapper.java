package com.github.evseevda.businesslogicservice.carrier.mapper;

import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.Carrier.CARRIER;


@Component
public class CarrierJooqRecordMapper implements JooqRecordMapper<CarrierEntity> {

    @Override
    public <R extends Record> CarrierEntity extractEntity(R r) {
        return new CarrierEntity(
                r.get(CARRIER.ID),
                r.get(CARRIER.TITLE),
                r.get(CARRIER.PHONE)
        );

    }
}
