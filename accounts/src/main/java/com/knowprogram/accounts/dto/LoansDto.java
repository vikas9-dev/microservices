package com.knowprogram.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Loans", description = "Schema to hold Loans details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoansDto {

    @NotEmpty(message = "Mobile Number cannot be empty or null")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile Number should be 10 digits")
    @Schema(description = "Mobile Number of the Customer", example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be empty or null")
    @Pattern(regexp = "[0-9]{12}", message = "Loan Number should be 12 digits")
    @Schema(description = "Loan Number of the Customer", example = "123456789012")
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be empty or null")
    @Schema(description = "Loan Type of the Customer", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total Loan should be greater than 0")
    @Schema(description = "Total Loan amount of the Customer", example = "100000")
    private Integer totalLoan;

    @PositiveOrZero(message = "Amount Paid should be equal to or greater than 0")
    @Schema(description = "Amount Paid by the Customer", example = "50000")
    private Integer amountPaid;

    @PositiveOrZero(message = "Outstanding Amount should be equal to or greater than 0")
    @Schema(description = "Outstanding Amount of the Customer", example = "50000")
    private Integer outstandingAmount;
}
