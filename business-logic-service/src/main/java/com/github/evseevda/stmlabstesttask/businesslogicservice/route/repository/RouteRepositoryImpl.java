package com.github.evseevda.stmlabstesttask.businesslogicservice.route.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<Route> routeJdbcRecordMapper;

    @Override
    public Optional<Route> findById(Long routeId) {
        String sql = "SELECT * FROM bl.route WHERE id = :routeId";
        Map<String, ?> params = Map.of("routeId", routeId);
        return jdbcTemplate.query(sql, params, routeJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public Route saveNew(Route entity) {
        String sql = """
                INSERT INTO bl.route (start_point, destination_point, carrier_id, duration_in_minutes)
                VALUES
                (:startPoint, :destinationPoint, :carrierId, :durationInMinutes)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "startPoint", entity.getStartPoint(),
                "destinationPoint", entity.getDestinationPoint(),
                "carrierId", entity.getCarrier().getId(),
                "durationInMinutes", entity.getDurationInMinutes()
        );
        return jdbcTemplate.query(sql, params, routeJdbcRecordMapper::extractEntity);
    }

    @Override
    public Optional<Route> update(Route entity) {
        String sql = """
                UPDATE bl.route
                SET
                    start_point = :startPoint,
                    destination_point = :destinationPoint,
                    carrier_id = :carrierId,
                    duration_in_minutes = :durationInMinutes
                WHERE id = :id
                """;
        Map<String, ?> params = Map.of(
                "id", entity.getId(),
                "startPoint", entity.getStartPoint(),
                "destinationPoint", entity.getDestinationPoint(),
                "carrierId", entity.getCarrier().getId(),
                "durationInMinutes", entity.getDurationInMinutes()
        );
        return jdbcTemplate.query(sql, params, routeJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM bl.route WHERE id = :id";
        Map<String, ?> params = Map.of("id", id);
        jdbcTemplate.update(sql, params);
    }

}
