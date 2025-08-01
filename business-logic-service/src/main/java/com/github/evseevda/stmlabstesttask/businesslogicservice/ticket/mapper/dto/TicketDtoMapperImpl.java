package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.dto;

import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.dto.RouteDtoMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.kafka.PurchasedTicketKafkaDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TicketDtoMapperImpl implements TicketDtoMapper {

    private final RouteDtoMapper routeDtoMapper;

    @Override
    public Ticket fromRequestDto(TicketRequestDto requestDto) {
        return Ticket.builder()
                .route(Route.builder()
                        .id(requestDto.getRouteId())
                        .build()
                )
                .dateTimeUtc(requestDto.getDateTime())
                .seatNumber(requestDto.getSeatNumber())
                .cost(requestDto.getCost())
                .build();
    }

    @Override
    public Ticket fromRequestDto(Long id, TicketRequestDto requestDto) {
        return Ticket.builder()
                .id(id)
                .route(Route.builder()
                        .id(requestDto.getRouteId())
                        .build()
                )
                .dateTimeUtc(requestDto.getDateTime())
                .seatNumber(requestDto.getSeatNumber())
                .cost(requestDto.getCost())
                .build();
    }

    @Override
    public TicketResponseDto toResponseDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .route(routeDtoMapper.toResponseDto(ticket.getRoute()))
                .dateTime(ticket.getDateTimeUtc())
                .seatNumber(ticket.getSeatNumber())
                .cost(ticket.getCost())
                .build();
    }

    @Override
    public PurchasedTicketKafkaDto toPurchasedTicketKafkaDto(Ticket ticket, LocalDateTime purchaseTimestamp) {
        return PurchasedTicketKafkaDto.builder()
                .ticketId(ticket.getId())
                .passengerId(ticket.getPassenger().getId())
                .cost(ticket.getCost())
                .purchaseTimestamp(purchaseTimestamp)
                .build();
    }
}
