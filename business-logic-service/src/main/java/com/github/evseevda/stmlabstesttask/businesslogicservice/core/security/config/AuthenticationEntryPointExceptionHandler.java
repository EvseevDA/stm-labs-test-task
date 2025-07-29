package com.github.evseevda.stmlabstesttask.businesslogicservice.core.security.config;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response.HttpResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response.HttpResponse.unauthorized;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    private final HttpResponseWriter responseWriter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn(authException.getMessage(), authException);
        responseWriter.write(unauthorized(response).withMessage("Ошибка аутентификации"));
    }
}
