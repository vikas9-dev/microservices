package com.knowprogram.accounts.client;

import com.knowprogram.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards") // microservice name
public interface CardsFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
