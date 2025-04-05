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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST APIs for Customers", description =
        "CRUD REST APIs for PeopleBank to fetch customer details")
@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
public class CustomerController {

    private ICustomerService customerService;

    @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch Customer All details")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerAllDetailsDto> fetchCustomerDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message
            = "Mobile Number must be 10 digits") String mobileNumber) {
        CustomerAllDetailsDto customerAllDetailsDto = customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerAllDetailsDto);
    }

}
