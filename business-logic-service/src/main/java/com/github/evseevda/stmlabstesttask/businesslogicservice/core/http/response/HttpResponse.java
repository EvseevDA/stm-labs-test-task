package com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response;


import com.github.evseevda.stmlabstesttask.businesslogicservice.core.dto.ErrorResponseBody;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class HttpResponse {

    private HttpServletResponse originalResponse;
    @Getter
    private ErrorResponseBody body;

    public HttpResponse(HttpServletResponse originalResponse) {
        this.originalResponse = originalResponse;
    }

    public HttpResponse withStatus(HttpStatus httpStatus) {
        originalResponse.setStatus(httpStatus.value());
        return this;
    }

    public HttpResponse withContentType(String contentType) {
        originalResponse.setContentType(contentType);
        return this;
    }

    public HttpResponse withCharacterEncoding(Charset characterEncoding) {
        originalResponse.setCharacterEncoding(characterEncoding.name());
        return this;
    }

    public HttpResponse withMessage(String message) {
        this.body = ErrorResponseBody.withMessage(message);
        return this;
    }

    public PrintWriter writer() throws IOException {
        return originalResponse.getWriter();
    }


    public static HttpResponse unauthorized(HttpServletResponse originalResponse) {
        return withDefaults(originalResponse)
                .withStatus(HttpStatus.UNAUTHORIZED);
    }

    public static HttpResponse forbidden(HttpServletResponse originalResponse) {
        return withDefaults(originalResponse)
                .withStatus(HttpStatus.FORBIDDEN);
    }

    private static HttpResponse withDefaults(HttpServletResponse originalResponse) {
        return new HttpResponse(originalResponse)
                .withContentType(MediaType.APPLICATION_JSON_VALUE)
                .withCharacterEncoding(StandardCharsets.UTF_8);
    }

}
