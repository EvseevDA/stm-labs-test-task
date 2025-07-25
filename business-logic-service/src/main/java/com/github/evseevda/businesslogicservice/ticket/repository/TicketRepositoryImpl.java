package com.github.evseevda.businesslogicservice.ticket.repository;

import com.github.evseevda.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.businesslogicservice.ticket.search.TicketSearchFilter;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.evseevda.businesslogicservice.tables.Carrier.CARRIER;
import static com.github.evseevda.businesslogicservice.tables.Route.ROUTE;
import static com.github.evseevda.businesslogicservice.tables.Ticket.TICKET;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DSLContext dsl;
    private final JdbcRecordMapper<Ticket> ticketJdbcRecordMapper;
    private final JooqRecordMapper<Ticket> ticketJooqRecordMapper;

    @Override
    public Stream<Ticket> findAllAvailableTickets(PageRequest pageRequest, TicketSearchFilter filter) {
        SelectConditionStep<? extends Record> baseQuery = dsl.select(
                        TICKET.ID,
                        TICKET.DATE_TIME_UTC,
                        TICKET.SEAT_NUMBER,
                        TICKET.COST,

                        TICKET.route().ID,
                        TICKET.route().START_POINT, TICKET.route().DESTINATION_POINT,
                        TICKET.route().DURATION_IN_MINUTES,

                        ROUTE.carrier().ID,
                        ROUTE.carrier().TITLE,
                        ROUTE.carrier().PHONE
                )
                .from(TICKET.join(ROUTE).on(TICKET.ROUTE_ID.equal(ROUTE.ID))
                                .join(CARRIER).on(ROUTE.CARRIER_ID.equal(CARRIER.ID))
                )
                .where(TICKET.PASSENGER_ID.isNull());

        return addSearchFilter(baseQuery, filter)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(ticketJooqRecordMapper::extractEntity)
                .stream();
    }

    private SelectConditionStep<? extends Record> addSearchFilter(
            SelectConditionStep<? extends Record> baseQuery,
            TicketSearchFilter filter
    ) {
        LocalDateTime fromDateTime = filter.getFromDateTime();
        if (fromDateTime != null) {
            baseQuery.and(TICKET.DATE_TIME_UTC.greaterOrEqual(fromDateTime));
        }

        LocalDateTime toDateTime = filter.getToDateTime();
        if (toDateTime != null) {
            baseQuery.and(TICKET.DATE_TIME_UTC.lessOrEqual(toDateTime));
        }

        String startPoint = filter.getStartPoint();
        if (startPoint != null) {
            baseQuery.and(ROUTE.START_POINT.containsIgnoreCase(startPoint));
        }

        String destinationPoint = filter.getDestinationPoint();
        if (destinationPoint != null) {
            baseQuery.and(ROUTE.DESTINATION_POINT.containsIgnoreCase(destinationPoint));
        }

        String carrierName = filter.getCarrierName();
        if (carrierName != null) {
            baseQuery.and(CARRIER.TITLE.containsIgnoreCase(carrierName));
        }

        return baseQuery;
    }

    @Override
    public void markAsBought(Long ticketId, Long passengerId) {
        String sql = "UPDATE bl.ticket SET passenger_id = :passengerId WHERE id = :ticketId";
        Map<String, ?> params = Map.of(
                "ticketId", ticketId,
                "passengerId", passengerId
        );
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Stream<Ticket> findAllTicketsByPassengerId(Long passengerId) {
        String sql = "SELECT * FROM bl.ticket WHERE passenger_id = :passengerId";
        Map<String, ?> params = Map.of("passengerId", passengerId);
        return jdbcTemplate.queryForStream(sql, params, (rs, rowNum) -> ticketJdbcRecordMapper.extractEntity(rs));
    }

    @Override
    public Ticket saveNew(Ticket entity) {
        String sql = """
                INSERT INTO bl.ticket (route_id, date_time_utc, seat_number, cost)
                VALUES
                (:routeId, :dateTimeUtc, :seatNumber, :cost)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "routeId", entity.getRoute().getId(),
                "dateTimeUtc", entity.getDateTimeUtc(),
                "seatNumber", entity.getSeatNumber(),
                "cost", entity.getCost()
        );
        return jdbcTemplate.query(sql, params, ticketJdbcRecordMapper::extractEntity);
    }

    @Override
    public Ticket update(Ticket entity) {
        String sql = """
                UPDATE bl.ticket
                SET
                    route_id = :routeId,
                    date_time_utc = :date_time_utc,
                    seat_number = :seat_number,
                    cost = :cost
                WHERE id = :id
                """;
        Map<String, ?> params = Map.of(
                "id", entity.getId(),
                "routeId", entity.getRoute().getId(),
                "dateTimeUtc", entity.getDateTimeUtc(),
                "seatNumber", entity.getSeatNumber(),
                "cost", entity.getCost()
        );
        return jdbcTemplate.query(sql, params, ticketJdbcRecordMapper::extractEntity);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM bl.ticket WHERE id = :id";
        Map<String, ?> params = Map.of("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        String sql = "SELECT * FROM bl.ticket WHERE id = :id";
        Map<String, ?> params = Map.of("id", id);
        return jdbcTemplate.query(sql, params, ticketJdbcRecordMapper::extractNullableEntity);
    }

}
