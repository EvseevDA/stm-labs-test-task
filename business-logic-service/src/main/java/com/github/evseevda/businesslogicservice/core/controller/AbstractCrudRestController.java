package com.github.evseevda.businesslogicservice.core.controller;


import com.github.evseevda.businesslogicservice.core.service.CrudService;
import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class AbstractCrudRestController<E, ID, IN, OUT> {

    private final CrudService<E, ID> service;
    private final RequestDtoMapper<E, ID, IN, OUT> mapper;

    @PostMapping
    public ResponseEntity<OUT> saveNew(
            @RequestBody IN requestDto
    ) {
        E entity = mapper.fromRequestDto(requestDto);
        E savedEntity = service.saveNew(entity);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(mapper.toResponseDto(savedEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OUT> update(
            @PathVariable ID id,
            @RequestBody IN requestDto
    ) {
        E entity = mapper.fromRequestDto(id, requestDto);
        E updatedEntity = service.update(entity);
        return ResponseEntity.ok(mapper.toResponseDto(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
