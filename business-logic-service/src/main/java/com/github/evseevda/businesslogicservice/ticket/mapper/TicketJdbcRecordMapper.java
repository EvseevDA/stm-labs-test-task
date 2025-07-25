package com.github.evseevda.businesslogicservice.ticket.mapper;

import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.RouteEntity;
import com.github.evseevda.businesslogicservice.route.repository.RouteRepository;
import com.github.evseevda.businesslogicservice.ticket.entity.TicketEntity;
import com.github.evseevda.businesslogicservice.user.entity.UserEntity;
import com.github.evseevda.businesslogicservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class TicketJdbcRecordMapper extends AbstractJdbcRecordMapper<TicketEntity> {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    @Override
    public TicketEntity extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long routeId = rs.getLong("route_id");
        LocalDateTime dateTimeUtc = rs.getObject("date_time_utc", LocalDateTime.class);
        int seatNumber = rs.getInt("seat_number");
        BigDecimal cost = rs.getObject("cost", BigDecimal.class);
        Long passengerId = rs.getObject("passenger_id", Long.class);

        RouteEntity route = routeRepository.findById(routeId).orElseThrow();
        UserEntity passenger = userRepository.findById(passengerId).orElse(null);

        return TicketEntity.builder()
                .id(id)
                .route(route)
                .dateTimeUtc(dateTimeUtc)
                .seatNumber(seatNumber)
                .cost(cost)
                .passenger(passenger)
                .build();
    }
}
