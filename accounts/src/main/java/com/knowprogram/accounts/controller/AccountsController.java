package com.knowprogram.accounts.controller;

import com.knowprogram.accounts.constants.AccountConstants;
import com.knowprogram.accounts.dto.CustomerDTO;
import com.knowprogram.accounts.dto.ErrorResponseDTO;
import com.knowprogram.accounts.dto.ResponseDTO;
import com.knowprogram.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Accounts", description =
        "CRUD REST APIs for Accounts to create, update, delete and " + "fetch account details")
@RestController
@RequestMapping("/api")
@Validated
public class AccountsController {

    @Autowired
    private IAccountService accountService;

    @Operation(summary = "Create Account REST API", description = "REST API to create new Customer & Account")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 - Created"), @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
    @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(AccountConstants.STATUS_201,
                AccountConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Account REST API", description = "REST API to fetch account details")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"), @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
    @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message
            = "Mobile Number must be 10 digits") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAccount(mobileNumber));
    }

    @Operation(summary = "Update Account REST API", description = "REST API to update existing Customer & Account")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"),
            @ApiResponse(responseCode = "417", description = "HTTP Status 417 - Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        return isUpdated ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200,
                AccountConstants.MESSAGE_200)) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(AccountConstants.STATUS_500
                        , AccountConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete Account REST API", description = "REST API to delete existing Customer & Account")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        return isDeleted ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200,
                AccountConstants.MESSAGE_200)) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(AccountConstants.STATUS_500
                        , AccountConstants.MESSAGE_417_DELETE));
    }
}
