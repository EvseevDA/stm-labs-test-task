package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.controller;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.controller.AbstractCrudRestController;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request.PageRequest;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Билеты")
@RestController
@RequestMapping("/api/ticket")
public class TicketRestController
        extends AbstractCrudRestController<Ticket, Long, TicketRequestDto, TicketResponseDto> {

    private final TicketService service;
    private final UserService userService;

    @Autowired
    public TicketRestController(
            TicketService service,
            UserService userService,
            RequestDtoMapper<Ticket, Long, TicketRequestDto, TicketResponseDto> mapper
    ) {
        super(service, mapper);
        this.service = service;
        this.userService = userService;
    }

    @Operation(
            summary = "Получение всех доступных для покупки билетов"
    )
    @GetMapping("/all-available")
    public ResponseEntity<List<TicketResponseDto>> getAllAvailableTickets(
            @Valid @ModelAttribute PageRequest pageRequest,
            @Valid @ModelAttribute TicketSearchFilter filter
    ) {
        List<TicketResponseDto> tickets = service.findAllAvailableTickets(pageRequest, filter)
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(tickets);
    }

    @Operation(
            summary = "Покупка билета",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Если билет успешно куплен"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Если билет уже куплен"
                    )
            }
    )
    @PatchMapping("/purchase/{ticketId}")
    public ResponseEntity<TicketResponseDto> buyTicket(
            @NotNull @PositiveOrZero @PathVariable Long ticketId
    ) {
        Ticket ticket = service.buyTicket(ticketId, userService.currentUser().getId());
        return ResponseEntity.ok(mapper.toResponseDto(ticket));
    }

    @Operation(
            summary = "Получение купленных билетов текущего пользователя"
    )
    @GetMapping("/my")
    public ResponseEntity<List<TicketResponseDto>> getCurrentUserTickets() {
        List<TicketResponseDto> tickets = service.findCurrentUserTickets()
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(tickets);
    }

}
