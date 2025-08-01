package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.data;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.bl.tables.Ticket.TICKET;

@Component
@RequiredArgsConstructor
public class TicketJooqRecordMapper implements JooqRecordMapper<Ticket> {

    private final JooqRecordMapper<Route> routeJooqRecordMapper;

    @Override
    public <R extends Record> Ticket extractEntity(R r) {
        return Ticket.builder()
                .id(r.get(TICKET.ID))
                .route(routeJooqRecordMapper.extractEntity(r))
                .dateTimeUtc(r.get(TICKET.DATE_TIME_UTC))
                .seatNumber(r.get(TICKET.SEAT_NUMBER))
                .cost(r.get(TICKET.COST))
                .build();
    }
}
