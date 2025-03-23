package com.knowprogram.accounts.service;

import com.knowprogram.accounts.constants.AccountConstants;
import com.knowprogram.accounts.dto.AccountDTO;
import com.knowprogram.accounts.dto.CustomerDTO;
import com.knowprogram.accounts.entity.Account;
import com.knowprogram.accounts.entity.Customer;
import com.knowprogram.accounts.exception.CustomerAlreadyExistsException;
import com.knowprogram.accounts.exception.ResourceNotFoundException;
import com.knowprogram.accounts.mapper.AccountMapper;
import com.knowprogram.accounts.mapper.CustomerMapper;
import com.knowprogram.accounts.repository.AccountRepository;
import com.knowprogram.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @param customerDTO
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number " + customer.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        long randomAccNumber = 100000000 + new Random().nextInt(900000000);
        return Account.builder().customerId(customer.getCustomerId()).accountNumber(randomAccNumber).accountType(AccountConstants.SAVINGS).branchAddress(AccountConstants.ADDRESS).build();
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        AccountDTO accountDTO = AccountMapper.mapToAccountDTO(account, new AccountDTO());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Account account = accountRepository.findById(accountDTO.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", String.valueOf(accountDTO.getAccountNumber())));
            AccountMapper.mapToAccount(accountDTO, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", String.valueOf(customerId)));
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
