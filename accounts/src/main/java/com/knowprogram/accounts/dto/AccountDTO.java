package com.knowprogram.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Account", description = "Schema to Hold Account Information")
@Data
public class AccountDTO {

    @Schema(description = "Account Number", example = "1234567890")
    @NotEmpty(message = "Account Number should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account Type", example = "Savings")
    @NotEmpty(message = "Account Type should not be null or empty")
    private String accountType;

    @Schema(description = "Branch Address", example = "123 New York Street")
    @NotEmpty(message = "Branch Address should not be null or empty")
    private String branchAddress;
}
