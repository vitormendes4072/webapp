package com.example.webapp.exception;

import java.time.Instant;
import java.util.List;

public record ValidationErrorResponse(
        int status,
        String message,
        Instant timestamp,
        List<String> errors
) {
}
