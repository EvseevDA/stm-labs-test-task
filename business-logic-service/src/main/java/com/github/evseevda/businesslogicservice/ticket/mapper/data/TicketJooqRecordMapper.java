package com.github.evseevda.businesslogicservice.ticket.mapper.data;

import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.Route;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.github.evseevda.businesslogicservice.bl.tables.Ticket.TICKET;

@Component
@RequiredArgsConstructor
public class TicketJooqRecordMapper implements JooqRecordMapper<Ticket> {

    private final JooqRecordMapper<Route> routeJooqRecordMapper;

    @Override
    public <R extends Record> Ticket extractEntity(R r) {
        return Ticket.builder()
                .id(r.get(TICKET.ID))
                .dateTimeUtc(r.get(TICKET.DATE_TIME_UTC))
                .seatNumber(r.get(TICKET.SEAT_NUMBER))
                .cost(r.get(TICKET.COST))
                .build();
    }
}
