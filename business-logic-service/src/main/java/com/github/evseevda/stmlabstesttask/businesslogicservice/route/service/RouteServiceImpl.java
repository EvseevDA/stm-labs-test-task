package com.github.evseevda.stmlabstesttask.businesslogicservice.route.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.DefaultCrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends DefaultCrudService<Route, Long> implements RouteService {

    @Autowired
    public RouteServiceImpl(CrudRepository<Route, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    public Class<Route> entityType() {
        return Route.class;
    }
}
