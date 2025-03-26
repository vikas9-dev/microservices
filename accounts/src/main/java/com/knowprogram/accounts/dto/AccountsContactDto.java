package com.knowprogram.accounts.dto;

import jdk.jfr.Category;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
