package com.knowprogram.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "CustomerAllDetailsDto", description = "Schema to hold Customer, Account, Cards and Loans information")
@Data
public class CustomerAllDetailsDto {
    @Schema(description = "Name of the customer", example = "John Doe")
    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "iR4E7@example.com")
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Invalid email address")
    private String email;

    @Schema(description = "Mobile Number of the customer", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account Details of the customer")
    private AccountDTO accountDTO;
    @Schema(description = "Loans Details of the customer")
    private LoansDto loansDto;
    @Schema(description = "Cards Details of the customer")
    private CardsDto cardsDto;
}
