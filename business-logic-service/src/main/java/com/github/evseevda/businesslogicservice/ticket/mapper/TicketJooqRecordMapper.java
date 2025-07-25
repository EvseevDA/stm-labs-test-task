package com.github.evseevda.businesslogicservice.ticket.mapper;

import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.RouteEntity;
import com.github.evseevda.businesslogicservice.ticket.entity.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.tables.Ticket.TICKET;

@Component
@RequiredArgsConstructor
public class TicketJooqRecordMapper implements JooqRecordMapper<TicketEntity> {

    private final JooqRecordMapper<RouteEntity> routeJooqRecordMapper;

    @Override
    public <R extends Record> TicketEntity extractEntity(R r) {
        return TicketEntity.builder()
                .id(r.get(TICKET.ID))
                .dateTimeUtc(r.get(TICKET.DATE_TIME_UTC))
                .seatNumber(r.get(TICKET.SEAT_NUMBER))
                .cost(r.get(TICKET.COST))
                .route(routeJooqRecordMapper.extractEntity(r))
                .dateTimeUtc(r.get(TICKET.DATE_TIME_UTC))
                .seatNumber(r.get(TICKET.SEAT_NUMBER))
                .cost(r.get(TICKET.COST))
                .build();
    }
}
