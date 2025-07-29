package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.data;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.repository.RouteRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class TicketJdbcRecordMapper extends AbstractJdbcRecordMapper<Ticket> {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    @Override
    public Ticket extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long routeId = rs.getLong("route_id");
        LocalDateTime dateTimeUtc = rs.getObject("date_time_utc", LocalDateTime.class);
        int seatNumber = rs.getInt("seat_number");
        BigDecimal cost = rs.getObject("cost", BigDecimal.class);
        Long passengerId = rs.getObject("passenger_id", Long.class);

        Route route = routeRepository.findById(routeId).get();
        User passenger = userRepository.findById(passengerId).orElse(null);

        return Ticket.builder()
                .id(id)
                .route(route)
                .dateTimeUtc(dateTimeUtc)
                .seatNumber(seatNumber)
                .cost(cost)
                .passenger(passenger)
                .build();
    }
}
