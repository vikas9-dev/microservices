package com.peoplebank.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
								.retry(config -> config
										.setRetries(3)
										// .setExceptions(NullPointerException.class, TimeoutException.class)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
								)
						)
						.uri("lb://LOANS"))
				.route("cards_route", r -> r
						.path("/peoplebank/cards/**").
						filters(f -> f
								.rewritePath("/peoplebank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
						)
						.uri("lb://CARDS"))
				.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id).circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()).build());
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("user") != null ? exchange.getRequest().getHeaders().getFirst("user") : "anonymous");
	}

	@Bean
	RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1); // replenishRate, burstCapacity, requestedTokens
	}

}
