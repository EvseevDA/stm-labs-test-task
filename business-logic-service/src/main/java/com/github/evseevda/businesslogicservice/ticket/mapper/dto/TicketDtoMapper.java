package com.github.evseevda.businesslogicservice.ticket.mapper.dto;


import com.github.evseevda.businesslogicservice.core.util.mapper.RequestDtoMapper;
import com.github.evseevda.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;

public interface TicketDtoMapper
        extends RequestDtoMapper<Ticket, Long, TicketRequestDto, TicketResponseDto> {
}
