package com.github.evseevda.stmlabstesttask.businesslogicservice.core.security.config;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response.HttpResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response.HttpResponse.forbidden;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

    private final HttpResponseWriter responseWriter;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn(accessDeniedException.getMessage(), accessDeniedException);
        responseWriter.write(forbidden(response).withMessage("Недостаточно прав для данного действия"));
    }
}
