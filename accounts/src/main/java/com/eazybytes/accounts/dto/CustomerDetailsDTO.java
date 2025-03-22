package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class CustomerDetailsDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private String accountNumber;
    private String accountType;
    private String branchAddress;
}
