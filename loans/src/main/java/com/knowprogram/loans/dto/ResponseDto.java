package com.knowprogram.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold Response details")
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(description = "Status Code")
    private String statusCode;
    @Schema(description = "Status Message")
    private String statusMsg;
}
