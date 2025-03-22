package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDTO {
    @NotEmpty(message = "Account Number should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type should not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address should not be null or empty")
    private String branchAddress;
}
