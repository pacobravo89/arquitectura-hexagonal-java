package com.pacobravo.hexagonal.infrastructure.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {

    @Schema(description = "Timestamp indicating when the error occurred", example = "2024-10-23T14:30:00")
    private LocalDateTime timestamp;
    @Schema(description = "HTTP status code of the error", example = "404")
    private HttpStatus status;
    @Schema(description = "Message providing details about the error", example = "Resource not found")
    private String message;
    @Schema(description = "Detailed information or path of the request that caused the error", example = "/prices")
    private String details;

    public ErrorDetails(HttpStatus status, String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}

