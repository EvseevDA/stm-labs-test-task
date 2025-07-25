package com.github.evseevda.businesslogicservice.carrier.mapper;


import com.github.evseevda.businesslogicservice.carrier.entity.CarrierEntity;
import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CarrierJdbcRecordMapper extends AbstractJdbcRecordMapper<CarrierEntity> {

    @Override
    public CarrierEntity extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String companyName = rs.getString("company_name");
        String phone = rs.getString("phone");

        return new CarrierEntity(id, companyName, phone);
    }

}
