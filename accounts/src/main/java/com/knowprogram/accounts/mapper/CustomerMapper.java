package com.knowprogram.accounts.mapper;

import com.knowprogram.accounts.dto.CustomerAllDetailsDto;
import com.knowprogram.accounts.dto.CustomerDTO;
import com.knowprogram.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

    public static CustomerAllDetailsDto mapToCustomerAllDetailsDto(Customer customer, CustomerAllDetailsDto customerAllDetailsDto) {
        customerAllDetailsDto.setName(customer.getName());
        customerAllDetailsDto.setEmail(customer.getEmail());
        customerAllDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerAllDetailsDto;
    }
}
