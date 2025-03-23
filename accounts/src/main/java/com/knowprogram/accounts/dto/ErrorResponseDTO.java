package com.knowprogram.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Schema to hold error details")
@Builder
@Data
public class ErrorResponseDTO {

    @Schema(description = "API Path", example = "/accounts/fetch")
    private String apiPath;

    @Schema(description = "Error Code", example = "400")
    private HttpStatus errorCode;

    @Schema(description = "Error Message", example = "Mobile Number must be 10 digits")
    private String errorMessage;

    @Schema(description = "Error Time", example = "2023-06-30T10:00:00")
    private LocalDateTime errorTime;
}
