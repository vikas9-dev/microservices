package com.knowprogram.accounts.controller;

import com.knowprogram.accounts.dto.CustomerAllDetailsDto;
import com.knowprogram.accounts.dto.CustomerDetailsDTO;
import com.knowprogram.accounts.dto.ErrorResponseDTO;
import com.knowprogram.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Customers", description =
        "CRUD REST APIs for PeopleBank to fetch customer details")
@RestController
@RequestMapping("/api")
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;

    @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch Customer All details")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerAllDetailsDto> fetchCustomerDetails(
            @RequestHeader("peoplebank-correlation-id") String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message
            = "Mobile Number must be 10 digits") String mobileNumber) {
        logger.debug("peoplebank-correlation-id found: {}", correlationId);
        CustomerAllDetailsDto customerAllDetailsDto = customerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerAllDetailsDto);
    }

}
