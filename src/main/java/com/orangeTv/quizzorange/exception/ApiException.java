package com.orangeTv.quizzorange.exception;

import java.time.ZonedDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class ApiException {
    private String message;
    private Throwable object;
    private ZonedDateTime timestamp;

    public ApiException(String message, Throwable object, ZonedDateTime timestamp) {
        this.message = message;
        this.object = object;
        this.timestamp = timestamp;
    }
}
