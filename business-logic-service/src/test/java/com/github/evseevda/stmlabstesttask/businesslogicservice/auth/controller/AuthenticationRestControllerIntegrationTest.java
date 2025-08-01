package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.dto.request.RegistrationRequestDto;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.cache.redis.adapter.RedisTemplateAdapter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.kafka.adapter.KafkaAdapter;
import com.github.evseevda.stmlabstesttask.businesslogicservice.ticket.dto.kafka.PurchasedTicketKafkaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class AuthenticationRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private KafkaAdapter kafkaAdapter;

    @MockitoBean
    private RedisTemplateAdapter redisTemplateAdapter;

    private static final String TEST_LOGIN = "test_login";
    private static final String TEST_PASSWORD = "1234567891";
    private static final String TEST_FULLNAME = "Anatoliy";
    private static RegistrationRequestDto registrationRequest =
            RegistrationRequestDto.builder()
                    .login(TEST_LOGIN)
                    .password(TEST_PASSWORD)
                    .fullName(TEST_FULLNAME)
            .build();

    @BeforeEach
    void createMocks() {
        doNothing().when(kafkaAdapter).send(anyString(), any(PurchasedTicketKafkaDto.class));
    }

    @Test
    void givenUserRegistrationData_WhenRegisterUser_ThenStatusCreatedAndUserReturned() throws Exception {
        // arrange
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/auth/registration/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationRequest));

        // action
        mockMvc.perform(request)

                // assertion
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""   
                                {
                                    "login": "test_login",
                                    "fullName": "Anatoliy"                                      
                                }
                                """, JsonCompareMode.LENIENT)
                );
    }

    @Test
    void givenUserRegistrationData_WhenRegisterAdminWithRoleAdmin_ThenStatusCreatedAndUserReturned() throws Exception {
        // arrange
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/auth/registration/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationRequest))
                .with(user(new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    }

                    @Override
                    public String getPassword() {
                        return null;
                    }

                    @Override
                    public String getUsername() {
                        return "admin";
                    }
                }));

        // action
        mockMvc.perform(request)

                // assertion
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""   
                                {
                                    "login": "test_login",
                                    "fullName": "Anatoliy"                                      
                                }
                                """, JsonCompareMode.LENIENT)
                );
    }

    @Test
    void givenUserRegistrationData_WhenRegisterAdminWithUserPurchaser_ThenStatusForbiddenAndErrorMessage() throws Exception {
        // arrange
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/auth/registration/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationRequest))
                .with(user(new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return List.of(new SimpleGrantedAuthority("ROLE_PURCHASER"));
                    }

                    @Override
                    public String getPassword() {
                        return null;
                    }

                    @Override
                    public String getUsername() {
                        return "user@mail.ru";
                    }
                }));

        // action
        mockMvc.perform(request)

                // assertion
                .andDo(print())
                .andExpect(
                        status().isForbidden()
                );
    }

}
