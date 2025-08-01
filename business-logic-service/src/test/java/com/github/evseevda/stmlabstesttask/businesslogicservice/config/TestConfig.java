package com.github.evseevda.stmlabstesttask.businesslogicservice.config;


import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.mapper.data.CarrierJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.mapper.data.CarrierJooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.repository.CarrierRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.repository.CarrierRepositoryImpl;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.util.mapper.JooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.data.RouteJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.mapper.data.RouteJooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.repository.RouteRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.repository.RouteRepositoryImpl;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.entity.Ticket;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.data.TicketJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.mapper.data.TicketJooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository.TicketRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.repository.TicketRepositoryImpl;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data.RoleJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data.RoleJooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data.UserJdbcRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.mapper.data.UserJooqRecordMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.RoleRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.RoleRepositoryImpl;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepositoryImpl;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jooq.AutoConfigureJooq;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
@AutoConfigureJooq
public class TestConfig {

    @Bean
    public JdbcRecordMapper<Carrier> carrierJdbcRecordMapper() {
        return new CarrierJdbcRecordMapper();
    }

    @Bean("carrierRepositoryImpl")
    public CarrierRepository carrierRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcRecordMapper<Carrier> mapper) {
        return new CarrierRepositoryImpl(jdbcTemplate, mapper);
    }

    @Bean
    public JdbcRecordMapper<Route> routeJdbcRecordMapper(CarrierRepository carrierRepository) {
        return new RouteJdbcRecordMapper(carrierRepository);
    }

    @Bean("routeRepositoryImpl")
    public RouteRepository routeRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcRecordMapper<Route> mapper) {
        return new RouteRepositoryImpl(jdbcTemplate, mapper);
    }

    @Bean
    public JooqRecordMapper<User> userJooqRecordMapper(JooqRecordMapper<Role> roleJooqRecordMapper) {
        return new UserJooqRecordMapper(roleJooqRecordMapper);
    }

    @Bean
    public JooqRecordMapper<Role> roleJooqRecordMapper() {
        return new RoleJooqRecordMapper();
    }

    @Bean
    public JdbcRecordMapper<Role> roleJdbcRecordMapper() {
        return new RoleJdbcRecordMapper();
    }

    @Bean
    @Qualifier("roleRepositoryImpl")
    public RoleRepository roleRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcRecordMapper<Role> roleJdbcRecordMapper) {
        return new RoleRepositoryImpl(jdbcTemplate, roleJdbcRecordMapper);
    }

    @Bean
    public JdbcRecordMapper<User> userJdbcRecordMapper(RoleRepository roleRepository) {
        return new UserJdbcRecordMapper(roleRepository);
    }

    @Bean
    @Qualifier("userRepositoryImpl")
    public UserRepository userRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcRecordMapper<User> userJdbcRecordMapper) {
        return new UserRepositoryImpl(jdbcTemplate, userJdbcRecordMapper);
    }

    @Bean
    public JdbcRecordMapper<Ticket> ticketJdbcRecordMapper(RouteRepository routeRepository, UserRepository userRepository) {
        return new TicketJdbcRecordMapper(routeRepository, userRepository);
    }

    @Bean
    public JooqRecordMapper<Carrier> carrierJooqRecordMapper() {
        return new CarrierJooqRecordMapper();
    }

    @Bean
    public JooqRecordMapper<Route> routeJooqRecordMapper(JooqRecordMapper<Carrier> carrierJooqRecordMapper) {
        return new RouteJooqRecordMapper(carrierJooqRecordMapper);
    }

    @Bean
    public JooqRecordMapper<Ticket> ticketJooqRecordMapper(JooqRecordMapper<Route> routeJooqRecordMapper) {
        return new TicketJooqRecordMapper(routeJooqRecordMapper);
    }

    @Bean
    @Qualifier("ticketRepositoryImpl")
    public TicketRepository ticketRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            DSLContext dsl,
            JdbcRecordMapper<Ticket> jdbcMapper,
            JooqRecordMapper<Ticket> jooqMapper
    ) {
        return new TicketRepositoryImpl(jdbcTemplate, dsl, jdbcMapper, jooqMapper);
    }

}
