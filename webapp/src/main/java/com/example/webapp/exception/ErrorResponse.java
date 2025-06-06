package com.example.webapp.exception;

public record ErrorResponse(
        int status,
        String message,
        java.time.Instant timestamp
) {
}
