package com.knowprogram.loans.controller;

import com.knowprogram.loans.constants.LoansConstants;
import com.knowprogram.loans.dto.ErrorResponseDto;
import com.knowprogram.loans.dto.LoansDto;
import com.knowprogram.loans.dto.ResponseDto;
import com.knowprogram.loans.service.LoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vikas
 * @since 1.0
 */
@Schema(name = "CRUD REST APIs for Loans", description = "CRUD REST APIs for Loans to create, update, delete and " +
        "fetch loan details")
@RestController
@RequestMapping("/api")
@Validated
public class LoansController {

    @Autowired
    private LoansService loansService;

    @Operation(summary = "Create Loan REST API", description = "REST API to create new Loan")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema =
            @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile " +
            "Number should be 10 digits") String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch Loan REST API", description = "REST API to fetch loan details")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Loan details fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema =
            @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile " +
            "Number should be 10 digits") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(loansService.fetchLoan(mobileNumber));
    }

    @Operation(summary = "Update Loan REST API", description = "REST API to update existing Loan")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Loan updated successfully"),
            @ApiResponse(responseCode = "417", description = "Loan update failed"), @ApiResponse(responseCode = "500"
            , description = "Internal Server Error", content = @Content(schema = @Schema(implementation =
            ErrorResponseDto.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoansDto loansDto) {
        Boolean isUpdate = loansService.updateLoan(loansDto);
        return isUpdate ? new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200)
                , HttpStatus.OK) : new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_417,
                LoansConstants.MESSAGE_417_UPDATE), HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(summary = "Delete Loan REST API", description = "REST API to delete existing Loan")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Loan deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Loan delete failed"), @ApiResponse(responseCode = "500"
            , description = "Internal Server Error", content = @Content(schema = @Schema(implementation =
            ErrorResponseDto.class)))})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile " +
            "Number should be 10 digits") String mobileNumber) {
        Boolean isDeleted = loansService.deleteLoan(mobileNumber);
        return isDeleted ? new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_200,
                LoansConstants.MESSAGE_200), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE),
                        HttpStatus.EXPECTATION_FAILED);
    }

}
