package com.github.evseevda.businesslogicservice.route.mapper.data;

import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.carrier.repository.CarrierRepository;
import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class RouteJdbcRecordMapper extends AbstractJdbcRecordMapper<Route> {

    private final CarrierRepository carrierRepository;

    @Override
    public Route extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String startPoint = rs.getString("start_point");
        String destinationPoint = rs.getString("destination_point");
        Long carrierId = rs.getLong("carrier_id");
        Long durationInMinutes = rs.getLong("duration_in_minutes");

        Carrier carrier = carrierRepository.findById(carrierId).orElseThrow();

        return Route.builder()
                .id(id)
                .startPoint(startPoint)
                .destinationPoint(destinationPoint)
                .carrier(carrier)
                .durationInMinutes(durationInMinutes)
                .build();
    }
}
