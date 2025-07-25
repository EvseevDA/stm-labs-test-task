package com.github.evseevda.businesslogicservice.route.service;

import com.github.evseevda.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.businesslogicservice.core.service.DefaultCrudService;
import com.github.evseevda.businesslogicservice.route.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends DefaultCrudService<Route, Long> {

    @Autowired
    public RouteServiceImpl(CrudRepository<Route, Long> crudRepository) {
        super(crudRepository);
    }

}
