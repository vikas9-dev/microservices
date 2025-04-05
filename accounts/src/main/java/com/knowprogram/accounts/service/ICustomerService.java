package com.knowprogram.accounts.service;

import com.knowprogram.accounts.dto.CustomerAllDetailsDto;

public interface ICustomerService {
    CustomerAllDetailsDto fetchCustomerDetails(String mobileNumber);
}
