package com.knowprogram.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice Rest API Documentation", version = "1.0", description = "Documentation Accounts API v1.0", contact = @Contact(name = "Vaibhav", email = "example@gmail.com", url = "https://www.knowprogram.com/"), license = @License(name = "Apache 2.0", url = "https://www.knowprogram.com/")), externalDocs = @ExternalDocumentation(url = "https://www.knowprogram.com/"))
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
