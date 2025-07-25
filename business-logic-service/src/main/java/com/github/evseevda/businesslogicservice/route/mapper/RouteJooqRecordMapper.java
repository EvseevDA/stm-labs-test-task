package com.github.evseevda.businesslogicservice.route.mapper;

import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.RouteEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.Route.ROUTE;


@Component
@RequiredArgsConstructor
public class RouteJooqRecordMapper implements JooqRecordMapper<RouteEntity> {

    private final JooqRecordMapper<CarrierEntity> carrierJooqRecordMapper;

    @Override
    public <R extends Record> RouteEntity extractEntity(R r) {
        return RouteEntity.builder()
                .id(r.get(ROUTE.ID))
                .startPoint(r.get(ROUTE.START_POINT))
                .destinationPoint(r.get(ROUTE.DESTINATION_POINT))
                .carrier(carrierJooqRecordMapper.extractEntity(r))
                .durationInMinutes(r.get(ROUTE.DURATION_IN_MINUTES))
                .build();

    }
}
