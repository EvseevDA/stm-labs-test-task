package com.github.evseevda.businesslogicservice.carrier.controller;


import com.github.evseevda.businesslogicservice.carrier.dto.request.CarrierRequestDto;
import com.github.evseevda.businesslogicservice.carrier.dto.response.CarrierResponseDto;
import com.github.evseevda.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.businesslogicservice.core.controller.AbstractCrudRestController;
import com.github.evseevda.businesslogicservice.core.service.CrudService;
import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Перевозчики")
@RestController
@RequestMapping("/api/carrier")
public class CarrierRestController
        extends AbstractCrudRestController<Carrier, Long, CarrierRequestDto, CarrierResponseDto> {

    @Autowired
    public CarrierRestController(
            CrudService<Carrier, Long> service,
            RequestDtoMapper<Carrier, Long, CarrierRequestDto, CarrierResponseDto> mapper
    ) {
        super(service, mapper);
    }

}
