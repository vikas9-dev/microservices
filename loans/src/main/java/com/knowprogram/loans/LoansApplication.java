package com.knowprogram.loans;

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
@OpenAPIDefinition(info = @Info(title = "Loans Microservice REST API Documentation", version = "1.0", description =
        "Loans Microservice REST API Documentation", contact = @Contact(name = "Vikas", url = "https://knowprogram" +
        ".com", email = "2Vj0y@example.com"), license = @License(name = "Apache 2.0", url = "http://www.apache" +
        ".org/licenses/LICENSE-2.0")), externalDocs = @ExternalDocumentation(description = "Loans Microservice REST " +
        "API Documentation", url = "https://knowprogram.com"))
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
