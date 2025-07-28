package com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.repository;

import com.github.evseevda.stmlabstesttask.purchasedticketservice.purchasedticket.entity.PurchasedTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PurchasedTicketRepositoryImpl implements PurchasedTicketRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public PurchasedTicket saveNew(PurchasedTicket purchasedTicket) {
        String sql = """
                INSERT INTO main.purchased_ticket
                (ticket_id, passenger_id, cost, purchase_timestamp_utc)
                VALUES
                (:ticketId, :passengerId, :cost, :purchaseTimestampUtc)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "ticketId", purchasedTicket.getTicketId(),
                "passengerId", purchasedTicket.getPassengerId(),
                "cost", purchasedTicket.getCost(),
                "purchaseTimestampUtc", purchasedTicket.getPurchaseTimestampUtc()
        );
        return jdbcTemplate.query(sql, params, this::extractPurchasedTicket);
    }

    private PurchasedTicket extractPurchasedTicket(ResultSet rs) throws SQLException {
        long ticketId = rs.getLong("ticket_id");
        long passengerId = rs.getLong("passenger_id");
        BigDecimal cost = rs.getObject("cost", BigDecimal.class);
        LocalDateTime purchaseTimestampUtc = rs.getObject("purchase_timestamp_utc", LocalDateTime.class);

        return PurchasedTicket.builder()
                .ticketId(ticketId)
                .passengerId(passengerId)
                .cost(cost)
                .purchaseTimestampUtc(purchaseTimestampUtc)
                .build();
    }
}
