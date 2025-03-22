Create a Spring Boot project for accounts Microservice with the following dependencies:-

- Spring Web
- H2 DB
- Spring Data JPA
- Spring Boot Actuator
- Spring Boot DevTools
- Lombok
- Validation

In IntelliJ IDE go to the settings and enable Annotation processing. You can also install "Codeium" plugin. It is very similar to GitHub copilot, but it is free. Login to the [Codeium](https://codeium.com/). In IDE you can give instruction to it using CTRL + I.

Put the following in the resources folder:-

**application.yml**

```
server:
  port: 8080
spring:
  application:
    name: accounts
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

**schema.sql**

```
CREATE TABLE IF NOT EXISTS "customer" (
  "customer_id" INT PRIMARY KEY AUTO_INCREMENT,
  "name" VARCHAR(255) NOT NULL,
  "email" VARCHAR(255) NOT NULL UNIQUE,
  "mobile_number" VARCHAR(20) NOT NULL UNIQUE,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "created_by" VARCHAR(50) NOT NULL,
  "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "updated_by" VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "account" (
  "customer_id" INT NOT NULL,
  "account_number" INT PRIMARY KEY AUTO_INCREMENT,
  "account_type" VARCHAR(100) NOT NULL,
  "balance" DECIMAL(10, 2) NOT NULL,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "created_by" VARCHAR(50) NOT NULL,
  "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "updated_by" VARCHAR(50) DEFAULT NULL
);
```

After running the application, the H2 console will be available at '/h2-console' and Database will be available at 'jdbc:h2:mem:testdb'. Go to http://localhost:8080/h2-console/ and check the H2 DB.

Entity Classes

```
@MappedSuperclass
@Setter
@Getter
@ToString
public class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}
```

```
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
}
```

```
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}
```

Create Repositories for these 2 entity classes.

```
public interface AccountRepository extends JpaRepository<Account, Long> {}

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
```

Assume that you have to return response with both Account, and Customer entity info. But we can't return two classes at a time. In that case we can use DTO (Data Transfer Object) class.

```
@Data
public class CustomerDetailsDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private String accountNumber;
    private String accountType;
    private String branchAddress;
}

@Data
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String mobileNumber;
}
```

<hr/>

**Others**

How to preview a md file in VS Code?
You can also right-click on the editor Tab and select Open Preview (Ctrl+Shift+V) or use the Command Palette (Ctrl+Shift+P) to run the Markdown: Open Preview to the Side command (Ctrl+K V).
