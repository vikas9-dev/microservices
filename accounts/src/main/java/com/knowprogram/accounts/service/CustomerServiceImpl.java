package com.knowprogram.accounts.service;

import com.knowprogram.accounts.client.CardsFeignClient;
import com.knowprogram.accounts.client.LoansFeignClient;
import com.knowprogram.accounts.dto.AccountDTO;
import com.knowprogram.accounts.dto.CardsDto;
import com.knowprogram.accounts.dto.CustomerAllDetailsDto;
import com.knowprogram.accounts.dto.LoansDto;
import com.knowprogram.accounts.entity.Account;
import com.knowprogram.accounts.entity.Customer;
import com.knowprogram.accounts.exception.ResourceNotFoundException;
import com.knowprogram.accounts.mapper.AccountMapper;
import com.knowprogram.accounts.mapper.CustomerMapper;
import com.knowprogram.accounts.repository.AccountRepository;
import com.knowprogram.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;

    @Override
    public CustomerAllDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer =
                customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "mobileNumber", mobileNumber));
        Account account =
                accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())));
        CustomerAllDetailsDto customerAllDetailsDto = CustomerMapper.mapToCustomerAllDetailsDto(customer,
                new CustomerAllDetailsDto());
        customerAllDetailsDto.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,
                mobileNumber);
        if (loansDtoResponseEntity != null && loansDtoResponseEntity.getBody() != null) {
            customerAllDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,
                mobileNumber);
        if (cardsDtoResponseEntity != null && cardsDtoResponseEntity.getBody() != null) {
            customerAllDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerAllDetailsDto;
    }
}
