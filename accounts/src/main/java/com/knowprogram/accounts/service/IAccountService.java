package com.knowprogram.accounts.service;

import com.knowprogram.accounts.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}

