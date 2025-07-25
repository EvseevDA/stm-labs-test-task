package com.github.evseevda.businesslogicservice.route.mapper;

import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import com.github.evseevda.businesslogicservice.carrier.repository.CarrierRepository;
import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import com.github.evseevda.businesslogicservice.route.entity.RouteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class RouteJdbcRecordMapper extends AbstractJdbcRecordMapper<RouteEntity> {

    private final CarrierRepository carrierRepository;

    @Override
    public RouteEntity extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String startPoint = rs.getString("start_point");
        String destinationPoint = rs.getString("destination_point");
        Long carrierId = rs.getLong("carrier_id");
        Long durationInMinutes = rs.getLong("duration_in_minutes");

        CarrierEntity carrier = carrierRepository.findById(carrierId).orElseThrow();

        return RouteEntity.builder()
                .id(id)
                .startPoint(startPoint)
                .destinationPoint(destinationPoint)
                .carrier(carrier)
                .durationInMinutes(durationInMinutes)
                .build();
    }
}
