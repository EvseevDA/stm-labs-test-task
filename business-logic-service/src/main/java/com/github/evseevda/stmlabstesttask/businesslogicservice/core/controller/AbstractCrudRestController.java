package com.github.evseevda.stmlabstesttask.businesslogicservice.core.controller;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.service.CrudService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
public class AbstractCrudRestController<E extends Entity<ID>, ID, IN, OUT> {

    protected final CrudService<E, ID> service;
    protected final RequestDtoMapper<E, ID, IN, OUT> mapper;

    @Operation(
            summary = "Добавление сущности",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Если сущность успешно обновлена"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если у пользователя нет прав на обновление сущности"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<OUT> saveNew(
            @Valid @RequestBody IN requestDto
    ) {
        E entity = mapper.fromRequestDto(requestDto);
        E savedEntity = service.saveNew(entity);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(mapper.toResponseDto(savedEntity));
    }

    @Operation(
            summary = "Обновление сущности",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Если сущность успешно обновлена"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если сущность не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если у пользователя нет прав на обновление сущности"
                    )
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<OUT> update(
            @NotNull @PathVariable ID id,
            @Valid @RequestBody IN requestDto
    ) {
        E entity = mapper.fromRequestDto(id, requestDto);
        E updatedEntity = service.update(entity);
        return ResponseEntity.ok(mapper.toResponseDto(updatedEntity));
    }

    @Operation(
            summary = "Удаление сущности",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Если сущность успешно обновлена"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если у пользователя нет прав на обновление сущности"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
