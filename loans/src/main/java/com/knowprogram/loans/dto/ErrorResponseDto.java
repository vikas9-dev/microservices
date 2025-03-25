package com.knowprogram.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Schema to hold error details")
@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    @Schema(description = "API path invoked by client")
    private String apiPath;
    @Schema(description = "Error Code representing the error happened")
    private HttpStatus errorCode;
    @Schema(description = "Error Message representing the error happened")
    private String errorMessage;
    @Schema(description = "Time representing the error happened")
    private LocalDateTime errorTime;
}
