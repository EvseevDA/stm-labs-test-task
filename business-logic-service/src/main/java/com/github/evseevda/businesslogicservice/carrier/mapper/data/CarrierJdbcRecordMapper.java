package com.github.evseevda.businesslogicservice.carrier.mapper.data;


import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.util.mapper.AbstractJdbcRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CarrierJdbcRecordMapper extends AbstractJdbcRecordMapper<Carrier> {

    @Override
    public Carrier extractEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String companyName = rs.getString("company_name");
        String phone = rs.getString("phone");

        return new Carrier(id, companyName, phone);
    }

}
