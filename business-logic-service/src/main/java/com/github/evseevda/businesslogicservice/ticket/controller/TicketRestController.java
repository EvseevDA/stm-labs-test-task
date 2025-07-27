package com.github.evseevda.businesslogicservice.ticket.controller;


import com.github.evseevda.businesslogicservice.core.controller.AbstractCrudRestController;
import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.businesslogicservice.ticket.search.TicketSearchFilter;
import com.github.evseevda.businesslogicservice.ticket.service.TicketService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketRestController
        extends AbstractCrudRestController<Ticket, Long, TicketRequestDto, TicketResponseDto> {

    private final TicketService service;

    @Autowired
    public TicketRestController(
            TicketService service,
            RequestDtoMapper<Ticket, Long, TicketRequestDto, TicketResponseDto> mapper
    ) {
        super(service, mapper);
        this.service = service;
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

    @PatchMapping("/purchase/{ticketId}/{passengerId}")
    public ResponseEntity<Void> buyTicket(
            @NotNull @PositiveOrZero @PathVariable Long ticketId,
            @NotNull @PositiveOrZero @PathVariable Long passengerId
    ) {
        service.buyTicket(ticketId, passengerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<TicketResponseDto>> getCurrentUserTickets() {
        List<TicketResponseDto> tickets = service.findCurrentUserTickets()
                .map(mapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(tickets);
    }

}
