package com.github.evseevda.businesslogicservice.carrier.mapper.data;

import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.Carrier.CARRIER;


@Component
public class CarrierJooqRecordMapper implements JooqRecordMapper<Carrier> {

    @Override
    public <R extends Record> Carrier extractEntity(R r) {
        return new Carrier(
                r.get(CARRIER.ID),
                r.get(CARRIER.TITLE),
                r.get(CARRIER.PHONE)
        );

    }
}
