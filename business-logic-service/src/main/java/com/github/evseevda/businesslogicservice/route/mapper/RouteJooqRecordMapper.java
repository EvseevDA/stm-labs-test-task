package com.github.evseevda.businesslogicservice.route.mapper;

import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.Route;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.Route.ROUTE;


@Component
@RequiredArgsConstructor
public class RouteJooqRecordMapper implements JooqRecordMapper<Route> {

    private final JooqRecordMapper<Carrier> carrierJooqRecordMapper;

    @Override
    public <R extends Record> Route extractEntity(R r) {
        return Route.builder()
                .id(r.get(ROUTE.ID))
                .startPoint(r.get(ROUTE.START_POINT))
                .destinationPoint(r.get(ROUTE.DESTINATION_POINT))
                .carrier(carrierJooqRecordMapper.extractEntity(r))
                .durationInMinutes(r.get(ROUTE.DURATION_IN_MINUTES))
                .build();

    }
}
