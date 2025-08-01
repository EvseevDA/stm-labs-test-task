package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CarrierRepositoryImpl implements CarrierRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcRecordMapper<Carrier> carrierJdbcRecordMapper;

    @Override
    public Optional<Carrier> findById(Long carrierId) {
        String sql = "SELECT * FROM bl.carrier WHERE id = :carrierId";
        Map<String, ?> params = Map.of("carrierId", carrierId);
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractNullableEntity);
    }

    @Override
    public Carrier saveNew(Carrier entity) {
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
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractNonNullableEntity);
    }

    @Override
    public Carrier update(Carrier entity) {
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
        return jdbcTemplate.query(sql, params, carrierJdbcRecordMapper::extractNonNullableEntity);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM bl.carrier WHERE id = :id";
        Map<String, ?> params = Map.of("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM bl.carrier WHERE id = :id)";
        Map<String, ?> params = Map.of("id", id);
        return jdbcTemplate.queryForObject(sql, params, Boolean.class);
    }
}
