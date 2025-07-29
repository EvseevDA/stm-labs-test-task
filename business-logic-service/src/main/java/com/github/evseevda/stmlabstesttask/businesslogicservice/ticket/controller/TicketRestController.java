package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.controller;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.controller.AbstractCrudRestController;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    /**
     * В любом запросе с {@link TicketRequestDto} пытается найти параметр запроса
     * {@code timeOffset} и применить его к {@link TicketRequestDto#dateTime}
     * @param timeOffset смещение времени в часах
     * @param requestDto объект передачи данных из пользовательского запроса
     */
    @ModelAttribute
    public void applyTimeOffset(
            @RequestParam(defaultValue = "0") Integer timeOffset,
            @RequestBody TicketRequestDto requestDto
    ) {
        if (timeOffset != 0) {
            requestDto.setDateTime(requestDto.getDateTime().plusHours(timeOffset));
        }
    }

    @Operation(
            summary = "Получение всех доступных для покупки билетов"
    )
    @GetMapping("/all-available")
    public ResponseEntity<List<TicketResponseDto>> getAllAvailableTickets(
            @ModelAttribute PageRequest pageRequest,
            @ModelAttribute TicketSearchFilter filter
    ) {
        List<TicketResponseDto> tickets = service.findAllAvailableTickets(pageRequest, filter)
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(tickets);
    }

    @Operation(
            summary = "Покупка билета"
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
