package com.github.evseevda.businesslogicservice.route.controller;


import com.github.evseevda.businesslogicservice.core.controller.AbstractCrudRestController;
import com.github.evseevda.businesslogicservice.core.service.CrudService;
import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.businesslogicservice.route.dto.request.RouteRequestDto;
import com.github.evseevda.businesslogicservice.route.dto.response.RouteResponseDto;
import com.github.evseevda.businesslogicservice.route.entity.Route;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Маршруты")
@RestController
@RequestMapping("/api/route")
public class RouteRestController
        extends AbstractCrudRestController<Route, Long, RouteRequestDto, RouteResponseDto> {

    @Autowired
    public RouteRestController(
            CrudService<Route, Long> service,
            RequestDtoMapper<Route, Long, RouteRequestDto, RouteResponseDto> mapper
    ) {
        super(service, mapper);
    }

}
