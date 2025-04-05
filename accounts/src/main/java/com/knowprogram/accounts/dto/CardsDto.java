package com.knowprogram.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Cards", description = "Schema to hold Cards details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {

    @NotEmpty(message = "Mobile Number cannot be empty or null")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile Number should be 10 digits")
    @Schema(description = "Mobile Number of the Customer", example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be empty or null")
    @Pattern(regexp = "[0-9]{12}", message = "Card Number should be 12 digits")
    @Schema(description = "Card Number of the Customer", example = "123456789012")
    private String cardNumber;

    @NotEmpty(message = "Card Type cannot be empty or null")
    @Schema(description = "Card Type of the Customer", example = "Home Card")
    private String cardType;

    @Positive(message = "Total Limit should be greater than 0")
    @Schema(description = "Total Limit amount of the Customer", example = "100000")
    private Integer totalLimit;

    @PositiveOrZero(message = "Amount Used should be equal to or greater than 0")
    @Schema(description = "Amount Used by the Customer", example = "50000")
    private Integer amountUsed;

    @PositiveOrZero(message = "Available Amount should be equal to or greater than 0")
    @Schema(description = "Available Amount of the Customer", example = "50000")
    private Integer availableAmount;
}
