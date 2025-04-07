package com.peoplebank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator peopleBankRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("accounts_route", r -> r
						.path("/peoplebank/accounts/**")
						.filters(f -> f
								.rewritePath("/peoplebank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config
										.setName("accountCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")
								)
						)
						.uri("lb://ACCOUNTS"))
				.route("loans_route", r -> r
						.path("/peoplebank/loans/**")
						.filters(f -> f
								.rewritePath("/peoplebank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						)
						.uri("lb://LOANS"))
				.route("cards_route", r -> r
						.path("/peoplebank/cards/**").
						filters(f -> f
								.rewritePath("/peoplebank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						)
						.uri("lb://CARDS"))
				.build();
	}

}
