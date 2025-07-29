package com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.aspect;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.cache.redis.adapter.RedisTemplateAdapter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.cache.util.CacheKey;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;


@Aspect
@Component
@Order(1)
public class TicketsCacheAspect {

    private final UserService userService;
    private final RedisTemplateAdapter redisTemplateAdapter;
    private final Duration ticketListLifetime;

    @Autowired
    public TicketsCacheAspect(
            UserService userService,
            RedisTemplateAdapter redisTemplateAdapter,
            @Value("${cache.tickets.list.lifetime}") Duration ticketListLifetime
    ) {
        this.userService = userService;
        this.redisTemplateAdapter = redisTemplateAdapter;
        this.ticketListLifetime = ticketListLifetime;
    }

    @Around("""
                execution(java.util.stream.Stream<com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket>
                com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.findCurrentUserTickets(..))
            """)
    public Stream<Ticket> cacheCurrentUserTickets(ProceedingJoinPoint joinPoint) throws Throwable {
        User currentUser = userService.currentUser();

        String ticketsKey = CacheKey.tickets(currentUser);
        if (redisTemplateAdapter.existsByKey(ticketsKey)) {
            return redisTemplateAdapter.findAll(ticketsKey, Ticket.class);
        } else {
            @SuppressWarnings("unchecked")
            List<Ticket> ticketList = ((Stream<Ticket>) joinPoint.proceed()).toList();
            if (!ticketList.isEmpty()) {
                redisTemplateAdapter
                        .saveAll(ticketsKey, ticketList)
                        .withExpiration(ticketsKey, ticketListLifetime);
            }
            return ticketList.stream();
        }
    }

    @Around("execution(com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.service.TicketService.buyTicket(..))")
    public Ticket cacheBoughtTicket(ProceedingJoinPoint joinPoint) throws Throwable {
        Ticket ticket = (Ticket) joinPoint.proceed();
        User currentUser = userService.currentUser();
        String ticketsKey = CacheKey.tickets(currentUser);
        redisTemplateAdapter.saveToList(ticketsKey, ticket);
        return ticket;
    }

}
