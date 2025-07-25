package com.github.evseevda.businesslogicservice.carrier.repository;

import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import com.github.evseevda.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CarrierRepositoryImpl implements CarrierRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<CarrierEntity> carrierJdbcRecordMapper;

    @Override
    public Optional<CarrierEntity> findById(Long carrierId) {
        String sql = "SELECT * FROM bl.carrier WHERE id = :carrierId";
        Map<String, ?> params = Map.of("carrierId", carrierId);
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public CarrierEntity saveNew(CarrierEntity entity) {
        String sql = """
                INSERT INTO bl.carrier (company_name, phone)
                VALUES
                (:companyName, :phone)
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "companyName", entity.getCompanyName(),
                "phone", entity.getPhone()
        );
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractEntity);
    }

    @Override
    public CarrierEntity update(CarrierEntity entity) {
        String sql = """
                UPDATE bl.carrier
                SET company_name = :companyName, phone = :phone
                WHERE id = :id
                RETURNING *
                """;
        Map<String, ?> params = Map.of(
                "id", entity.getId(),
                "companyName", entity.getCompanyName(),
                "phone", entity.getPhone()
        );
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractEntity);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM bl.carrier WHERE id = :id";
        Map<String, ?> params = Map.of("id", id);
        jdbcTemplate.update(sql, params);
    }

}
