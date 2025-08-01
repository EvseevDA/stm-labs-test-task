package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.mapper.data;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JooqRecordMapper;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.bl.tables.Carrier.CARRIER;


@Component
public class CarrierJooqRecordMapper implements JooqRecordMapper<Carrier> {

    @Override
    public <R extends Record> Carrier extractEntity(R r) {
        return new Carrier(
                r.get(CARRIER.ID),
                r.get(CARRIER.COMPANY_NAME),
                r.get(CARRIER.PHONE)
        );

    }
}
