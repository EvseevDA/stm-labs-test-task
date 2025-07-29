package com.github.evseevda.stmlabstesttask.businesslogicservice.core.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponseBody {

    private String message;
    private LocalDateTime timestamp;

    public static ErrorResponseBody withMessage(String message) {
        return new ErrorResponseBody(message, LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
    }

}
