## 1. Accounts Microservice

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

---

## 2. Input Validation in Accounts Microservices

**Handling Runtime and Custom Exceptions**

In our **AccountsMicroservices**, we ensure that all runtime exceptions and custom exceptions are properly handled. However, another crucial standard that must be followed when building microservices and REST APIs is **validating input data** received from client applications.

**Why is Input Validation Important?**

Client applications send various types of data such as **name, email, and mobile number**. Without validation, improper data can lead to unnecessary database queries, invalid operations, and potential security vulnerabilities. Consider these scenarios:

- A user enters a **nine-digit or five-digit** mobile number instead of ten digits.
- An email address is not in a valid format.
- A name is provided with only **two or three characters**.
- An account number is not ten digits.
- An account type or branch address is left empty.

Allowing such invalid data can lead to inefficient database operations, unexpected errors, and poor API reliability. To prevent these issues, input validation must be enforced at the API level.

### Adding Validation to Spring Boot DTOs

Spring Boot provides built-in validation annotations that help enforce data integrity. Before implementing validation, ensure that the **Spring Boot Starter Validation** dependency is added in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Applying Validation Annotations

We enforce validation rules inside **DTO classes** (`CustomerDto` and `AccountsDto`) using Java's annotation-based approach. Below are some key validations applied:

#### CustomerDto Validations:

```java
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Customer name should be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "[0-9]{10}", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;
}
```

#### AccountsDto Validations:

```java
public class AccountsDto {

    @Pattern(regexp = "[0-9]{10}", message = "Account number must be exactly 10 digits")
    @NotEmpty(message = "Account number cannot be empty")
    private String accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
```

### Validating API Inputs in Controller

In the `AccountsController`, we must ensure that input data is validated before processing requests. This is achieved using `@Valid` annotation:

```java
@Validated
@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        // Business logic here
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody AccountsDto accountsDto) {
        // Business logic here
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchAccount(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile number must be exactly 10 digits") String mobileNumber) {
        // Business logic here
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile number must be exactly 10 digits") String mobileNumber) {
        // Business logic here
    }
}
```

### Handling Validation Failures with GlobalExceptionHandler

If validation fails, the API should return a meaningful error response. This is managed using **GlobalExceptionHandler** by overriding the `handleMethodArgumentNotValid()` method:

```java
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```

### Testing Input Validation

We can test our API validation using **Postman**:

#### Case 1: Invalid Input

- Sending a request with an **invalid email, short name, and incorrect mobile number** results in:

```json
{
  "mobileNumber": "Mobile number must be ten digits",
  "name": "Customer name should be between 5 and 30 characters",
  "email": "Email address should be a valid value"
}
```

#### Case 2: Valid Input

- Sending a **valid request** results in a **successful response**.

```json
{
  "message": "Account created successfully"
}
```

**Conclusion**:- By enforcing **strict input validation**, we ensure that our microservices handle only well-formed data, reducing errors and optimizing performance. Proper validations prevent unnecessary database queries, improve security, and enhance user experience. This approach should be followed across all microservices to maintain **data integrity and reliability**. Implementing these validation mechanisms in your microservices will significantly enhance API robustness and prevent incorrect data from propagating through the system.

---

## 3. Implementing Auditing with Spring Data JPA

Auditing metadata is crucial in microservices to track record creation and updates, including the user responsible for these actions. In our **AccountsMicroservices**, we maintain four metadata columns:

- **CREATED_AT** – Timestamp when a record is created
- **CREATED_BY** – User who created the record
- **UPDATED_AT** – Timestamp when a record is updated
- **UPDATED_BY** – User who updated the record

Previously, these columns were manually updated, but we encountered issues where UPDATED_AT and UPDATED_BY were not being updated during modifications. To resolve this, we leveraged **Spring Data JPA's auditing feature** to automatically update these metadata fields.

---

### Enabling Spring Data JPA Auditing

Spring Data JPA provides built-in support for auditing with annotations. To implement this feature, we followed these steps:

#### 1. Define Metadata Columns in BaseEntity

We placed the metadata columns inside a **BaseEntity** class and added appropriate **Spring Data JPA auditing annotations**:

```java
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;
}
```

#### 2. Implement AuditorAware

Spring Data JPA can determine timestamps but does not know who performed the operation. We created an **AuditAwareImpl** class to provide this information.

```java
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
```

> _Later, we can integrate this with Spring Security to capture the actual logged-in user._

#### 3. Enable Auditing in Main Class

We enabled **JPA Auditing** in the Spring Boot application by adding the `@EnableJpaAuditing` annotation.

```java
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
```

### Validating the Implementation

#### 1. Creating a New Record

We created a new record and checked the database. The **CREATED_AT** and **CREATED_BY** fields were automatically populated:

```sql
SELECT created_by, created_at FROM accounts;
-- Output: CREATED_BY = "ACCOUNTS_MS", CREATED_AT = "2025-03-22 10:00:00"
```

#### 2. Updating an Existing Record

After updating a record, the **UPDATED_AT** and **UPDATED_BY** fields were correctly populated:

```sql
SELECT updated_by, updated_at FROM accounts;
-- Output: UPDATED_BY = "ACCOUNTS_MS", UPDATED_AT = "2025-03-22 12:00:00"
```

**Conclusion**:- By leveraging **Spring Data JPA Auditing**, we automated the management of metadata columns, ensuring proper tracking of record creation and updates without writing explicit SQL queries. This implementation improves code maintainability and is a best practice for microservices development.

> _In the future, we will integrate Spring Security to dynamically fetch the actual logged-in user for auditing._

---

## 4. Documentation of REST APIs using springdoc openapi

In the pom.xml file add the following dependency from (springdoc)[https://springdoc.org/]:-

```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.5</version>
</dependency>
```

Update the maven pom.xml to downloaded the new dependecies and go to the URL:- http://localhost:8080/swagger-ui/index.html

Here’s the extracted section on enhancing REST API documentation with OpenAPI:  

---

### Enhancing REST API Documentation with OpenAPI  
To improve our **REST API documentation**, we leveraged OpenAPI annotations in our **Spring Boot main class (AccountsApplication)**.  

#### 1. Adding OpenAPI Definition  
We added the `@OpenAPIDefinition` annotation to provide essential API details:  

```java
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts Microservice REST API Documentation",
        description = "Accounts Microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "KnowProgram Support",
            email = "support@knowprogram.com",
            url = "https://www.knowprogram.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Accounts Microservice REST API Documentation",
        url = "https://docs.knowprogram.com"
    )
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
```

After adding the annotations:  
- **Built the project**  
- **Refreshed Swagger UI**  
- **Confirm** the enhanced documentation with title, description, contact details, licensing, and external docs.  

### Improving Controller-Level Documentation
To make our REST API documentation more informative and user-friendly, we enhanced our **AccountsController** documentation using OpenAPI annotations.

#### 1. Adding @Tag Annotation to Controller
To provide a meaningful summary and description for all APIs inside the **AccountsController**, we used the `@Tag` annotation:

```java
@Tag(
    name = "Accounts API",
    description = "CRUD REST APIs in EasyBank to create, update, fetch, and delete account details."
)
@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
    // API methods here
}
```

This replaces the generic **AccountsController** name in Swagger UI with a more descriptive title and explanation.

---

### Enhancing API-Specific Documentation
For each API method, we added annotations to describe their functionality.

#### 1. Documenting the Create Account API
We used the `@Operation` annotation to provide a summary and description:

```java
@Operation(
    summary = "Create Account REST API",
    description = "REST API to create a new Customer and Account inside EasyBank."
)
@ApiResponse(
    responseCode = "201",
    description = "HttpStatus.CREATED"
)
@PostMapping("/create")
public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
    // API logic here
}
```

This improves Swagger UI by displaying a clear **summary**, **description**, and the correct **response code (201 - Created)**.

#### 2. Documenting the Fetch, Update, and Delete APIs
We applied the same annotations to other endpoints:

```java
@Operation(summary = "Fetch Account REST API", description = "REST API to fetch account details by ID.")
@ApiResponse(responseCode = "200", description = "HttpStatus.OK")
@GetMapping("/{id}")
public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
    // API logic here
}
```

```java
@Operation(summary = "Update Account REST API", description = "REST API to update existing account details.")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "HttpStatus.OK"),
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@PutMapping("/update")
public ResponseEntity<AccountResponse> updateAccount(@RequestBody AccountRequest request) {
    // API logic here
}
```

```java
@Operation(summary = "Delete Account REST API", description = "REST API to delete an account by ID.")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "HttpStatus.OK"),
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
    // API logic here
}
```

---

### Updating DTO Classes with `@Schema` Annotations

To enhance the documentation of our schema objects, we need to visit the DTO classes and annotate them with meaningful descriptions and example values.

#### Updating `CustomerDto`

- Instead of displaying the technical name, we use `@Schema(name = "Customer")` to make it more business-friendly.
- Added a description using `@Schema(description = "Schema to hold customer and account information")`.
- Annotated individual fields:
  - `name`: `@Schema(description = "Name of the customer", example = "Eazy Byte")`
  - `email`: `@Schema(description = "Email address of the customer", example = "customer@example.com")`
  - `mobile`: `@Schema(description = "Mobile number of the customer", example = "9876543210")`
  - `accounts`: `@Schema(description = "Account details of the customer")`

#### Updating `AccountsDto`

- Used `@Schema(name = "Accounts", description = "Schema to hold account information")`.
- Annotated individual fields:
  - `accountNumber`: `@Schema(description = "Account Number of Easy Bank Account")`
  - `accountType`: `@Schema(description = "Account Type of Easy Bank Account", example = "Savings")`
  - `branchAddress`: `@Schema(description = "Branch Address of the account")`

#### Updating `ResponseDto`

- Used `@Schema(name = "Response", description = "Schema to hold successful response information")`.
- Annotated individual fields:
  - `statusCode`: `@Schema(description = "Status code in the response", example = "200")`
  - `statusMessage`: `@Schema(description = "Status message in the response", example = "Request processed successfully")`

#### Updating `ErrorResponseDto`

- Used `@Schema(name = "ErrorResponse", description = "Schema to hold error response information")`.
- Annotated individual fields:
  - `errorCode`: `@Schema(description = "Error code representing the error occurred")`
  - `errorMessage`: `@Schema(description = "Error message describing the issue")`

### Displaying Schema Objects in API Responses

By default, OpenAPI documentation does not scan `GlobalExceptionHandler` logic automatically. To ensure that the `ErrorResponseDto` appears in our documentation, we explicitly define it inside the `@ApiResponse` annotation.

#### Defining Schema in `@ApiResponse`

```java
@ApiResponse(
    responseCode = "500",
    description = "Internal Server Error",
    content = @Content(
        schema = @Schema(implementation = ErrorResponseDto.class)
    )
)
```

By adding this, our API documentation now clearly displays error response structures in Swagger UI.

Example with update API:-

```java
@Operation(
    summary = "Update Account REST API",
    description = "REST API to update existing Customer & Account"
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 - OK"),
    @ApiResponse(responseCode = "417", description = "HTTP Status 417 - Expectation Failed"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Status 500 - Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
})
@PutMapping("/update")
public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
    boolean isUpdated = accountService.updateAccount(customerDTO);
    
    return isUpdated 
        ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200))
        : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
            .body(new ResponseDTO(
                AccountConstants.STATUS_500, 
                AccountConstants.MESSAGE_417_UPDATE
            ));
}
```
---
## Essential Spring Boot Annotations for Building REST APIs

Now that we have completed the development of the **Accounts Microservice**, let's quickly revisit the key annotations and components we used in building our REST APIs. Having a solid understanding of these annotations will be useful for interviews and when developing RESTful services in the future.

### 1. **@RestController vs. @Controller**
- `@RestController` is used to build REST APIs by exposing Java methods as endpoints.
- If a class needs to support both REST APIs and traditional Spring MVC views, use `@Controller` instead, along with `@ResponseBody` on REST methods.

### 2. **@ResponseBody**
- Used when `@Controller` is in place to ensure responses are returned in JSON format instead of an HTML view.
- Not needed when using `@RestController`, as it is included by default.

### 3. **ResponseEntity**
- A Spring class used for returning HTTP responses with headers, status codes, and body.
- Allows specifying the response type using generics.
- Example:
  ```java
  return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDTO("200", "Success"));
  ```

### 4. **@ControllerAdvice vs. @RestControllerAdvice**
- `@ControllerAdvice` is used for global exception handling when combined with `@ExceptionHandler`.
- `@RestControllerAdvice` is a combination of `@ControllerAdvice` and `@ResponseBody`, ensuring JSON responses.
- In our microservice, `@ControllerAdvice` worked fine, but `@RestControllerAdvice` can be used for stricter JSON-based responses.

### 5. **@ExceptionHandler**
- Used within `@ControllerAdvice` to define methods that handle specific exceptions globally.
- Example:
  ```java
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new ErrorResponse("400", ex.getMessage()));
  }
  ```

### 6. **RequestEntity vs. ResponseEntity**
- `RequestEntity` is used when both request headers and body are required as input parameters.
- `ResponseEntity` is used for sending responses with status codes, headers, and response bodies.
- We did not use `RequestEntity` since our APIs required only the request body.

### 7. **@RequestHeader and @RequestBody**
- `@RequestHeader`: Extracts HTTP headers from the request.
- `@RequestBody`: Maps the HTTP request body to a Java object.
- Example:
  ```java
  public ResponseEntity<ResponseDTO> updateAccount(
          @RequestHeader("Authorization") String token,
          @RequestBody CustomerDTO customerDTO) {
      // Business logic
  }
  ```

### **Using These Annotations in Interviews**
If you're asked how to build REST APIs with Spring Boot, follow this structured approach:
1. Create a class and annotate it with `@RestController`.
2. Define Java methods and annotate them with `@GetMapping`, `@PostMapping`, etc.
3. Use `@RequestBody`, `@RequestHeader`, and `ResponseEntity` appropriately.
4. Implement global exception handling using `@ControllerAdvice` and `@ExceptionHandler`.
5. If needed, use `RequestEntity` for handling both headers and body.

Explaining your approach as a structured flow will impress the interviewer and showcase your understanding of REST API development.

These annotations and classes are fundamental to developing **Spring Boot Microservices**. Keep them handy for quick reference!

---
## Loans Microservices

Schema:-
```sql
CREATE TABLE IF NOT EXISTS `loans` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `mobile_number` varchar(15) NOT NULL,
  `loan_number` varchar(100) NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loan_id`)
);
```

Use **`port: 8090`** for the loan microservices.

---

**Others**

How to preview a md file in VS Code?
You can also right-click on the editor Tab and select Open Preview (Ctrl+Shift+V) or use the Command Palette (Ctrl+Shift+P) to run the Markdown: Open Preview to the Side command (Ctrl+K V).
