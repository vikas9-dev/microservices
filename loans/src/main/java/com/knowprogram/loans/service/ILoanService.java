package com.knowprogram.loans.service;

import com.knowprogram.loans.dto.LoansDto;

public interface ILoanService {
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    Boolean updateLoan(LoansDto loansDto);
    Boolean deleteLoan(String mobileNumber);
}
