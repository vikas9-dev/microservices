package com.peoplebank.gatewayserver.filters;

import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "peoplebank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders){
        if(requestHeaders.get(CORRELATION_ID) != null){
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            if (requestHeaderList != null && !requestHeaderList.isEmpty()) {
                return requestHeaderList.stream().findFirst().orElse(null);
            }
        }
        return null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId){
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
