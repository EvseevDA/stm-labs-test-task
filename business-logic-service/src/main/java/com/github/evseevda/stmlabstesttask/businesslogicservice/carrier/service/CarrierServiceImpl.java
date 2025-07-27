package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarrierServiceImpl extends DefaultCrudService<Carrier, Long> implements CarrierService {

    @Autowired
    public CarrierServiceImpl(CrudRepository<Carrier, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    public Class<Carrier> entityType() {
        return Carrier.class;
    }
}
