package com.github.evseevda.businesslogicservice.ticket.mapper.dto;

import com.github.evseevda.businesslogicservice.route.entity.Route;
import com.github.evseevda.businesslogicservice.route.mapper.dto.RouteDtoMapper;
import com.github.evseevda.businesslogicservice.ticket.dto.request.TicketRequestDto;
import com.github.evseevda.businesslogicservice.ticket.dto.response.TicketResponseDto;
import com.github.evseevda.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.businesslogicservice.user.mapper.dto.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketDtoMapperImpl implements TicketDtoMapper {

    private final RouteDtoMapper routeDtoMapper;
    private final UserDtoMapper userDtoMapper;

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
}
