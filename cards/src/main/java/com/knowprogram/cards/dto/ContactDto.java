package com.knowprogram.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record ContactDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

}
