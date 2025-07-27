package com.github.evseevda.stmlabstesttask.businesslogicservice.core.security.config;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String PURCHASER_ROLE = "PURCHASER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/api/auth/registration/user",
                                        "/api/ticket/all-available",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/actuator/health"
                                ).permitAll()
                                .requestMatchers(
                                        "/api/auth/registration/admin"
                                ).hasRole(ADMIN_ROLE)
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/carrier",
                                        "/api/route",
                                        "/api/ticket"
                                ).hasRole(ADMIN_ROLE)
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/carrier/{id}",
                                        "/api/route/{id}",
                                        "/api/ticket/{id}"
                                ).hasRole(ADMIN_ROLE)
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/carrier/{id}",
                                        "/api/route/{id}",
                                        "/api/ticket/{id}"
                                ).hasRole(ADMIN_ROLE)
                                .requestMatchers(
                                        "/api/ticket/my",
                                        "/api/ticket/purchase/{ticketId}"
                                ).hasRole(PURCHASER_ROLE)
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(
                userService
        );
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
