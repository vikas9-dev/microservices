package com.knowprogram.loans.service;

import com.knowprogram.loans.constants.LoansConstants;
import com.knowprogram.loans.dto.LoansDto;
import com.knowprogram.loans.entity.Loans;
import com.knowprogram.loans.exception.LoanAlreadyExistException;
import com.knowprogram.loans.exception.ResourceNotFoundException;
import com.knowprogram.loans.mapper.LoansMapper;
import com.knowprogram.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoansService implements ILoanService {
    @Autowired
    LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistException("Loan already exist for mobile number: " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        long randomLoanNumber = (long) (Math.random() * 900000000000L) + 100000000000L;
        return Loans.builder().loanNumber(String.valueOf(randomLoanNumber)).mobileNumber(mobileNumber).loanType(LoansConstants.HOME_LOAN).totalLoan(LoansConstants.NEW_LOAN_LIMIT).amountPaid(0).outstandingAmount(LoansConstants.NEW_LOAN_LIMIT).build();
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans =
                loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException(
                        "Loan", "mobileNumber", mobileNumber));
        return LoansMapper.mapToLoansDto(loans, new LoansDto());

    }

    @Override
    public Boolean updateLoan(LoansDto loansDto) {
        Loans loans =
                loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", loansDto.getMobileNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public Boolean deleteLoan(String mobileNumber) {
        Loans loans =
                loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException(
                        "Loan", "mobileNumber", mobileNumber));
        loansRepository.delete(loans);
        return true;
    }
}
