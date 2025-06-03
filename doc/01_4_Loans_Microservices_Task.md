# Loans Microservice Development Task Document

**Project Overview:**

Develop a robust Spring Boot microservice named `Loans Microservice`. This service will support full CRUD operations on loan data, including creation, retrieval, update, and deletion of loan records, with full integration of validation, global exception handling, auditing, and OpenAPI documentation. The Loans Microservice uses an H2 in-memory database.

---

## Phase 1: Project Initialization

### Task 1.1: Set Up Project Using Spring Initializr

* Visit: [https://start.spring.io](https://start.spring.io)
* Project settings:

  * Language: Java
  * Build Tool: Maven
  * Spring Boot Version: 3.5.0
  * Group: `com.easybytes`
  * Artifact: `loans`
  * Name: `loans`
  * Description: `Loans Microservice`
  * Package Name: `com.easybytes.loans`
  * Packaging: Jar
  * Java Version: 17
* Dependencies to include:

  * Spring Web
  * Spring Data JPA
  * H2 Database
  * Spring Boot DevTools
  * Spring Boot Actuator
  * Lombok
  * Validation
  * SpringDoc OpenAPI

### Expected Outcome

* Project should build successfully with `mvn clean install`
* Open in IntelliJ IDEA or preferred IDE with Maven support

---

## Phase 2: H2 Configuration and Schema

### Task 2.1: Configure application.yml

* Configure port as 8090
* Setup H2 database URL, credentials, and console
* Configure JPA properties

### Task 2.2: Define schema.sql

* Create a file named `schema.sql` inside `src/main/resources`
* Define the `loans` table with necessary fields

```sql
CREATE TABLE IF NOT EXISTS loans (
  loan_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mobile_number VARCHAR(20),
  loan_number VARCHAR(50),
  loan_type VARCHAR(50),
  total_loan DECIMAL,
  amount_paid DECIMAL,
  outstanding_amount DECIMAL,
  created_at TIMESTAMP,
  created_by VARCHAR(50),
  updated_at TIMESTAMP,
  updated_by VARCHAR(50)
);
```

### Expected Outcome

* H2 console accessible at `/h2-console`
* Schema initialized with the `loans` table

---

## Phase 3: Entity and Repository Layer

### Task 3.1: Create BaseEntity Class

* Abstract class including:

  * createdAt
  * createdBy
  * updatedAt
  * updatedBy
* Annotated for JPA auditing

### Task 3.2: Create Loans Entity

* Fields include:

  * loanId (primary key)
  * mobileNumber
  * loanNumber
  * loanType
  * totalLoan
  * amountPaid
  * outstandingAmount
* Extend from BaseEntity

### Task 3.3: Create LoansRepository

* Extend `JpaRepository<Loans, Long>`
* Include method to find by mobile number

### Expected Outcome

* Repository accessible and functional
* Entity correctly mapped to the table

---

## Phase 4: DTO Layer

### Task 4.1: Create LoanDto

* Fields include:

  * mobileNumber
  * loanNumber
  * loanType
  * totalLoan
  * amountPaid
  * outstandingAmount
* Use validation annotations such as:

  * `@NotEmpty`, `@Pattern`, `@Positive`, `@PositiveOrZero`

### Task 4.2: Create ResponseDto and ErrorResponseDto

* Standardized response structure for success and error outputs

### Expected Outcome

* DTOs should exclude internal fields like loanId
* Should be used in controller request/response bodies

---

## Phase 5: Service and Controller Layer

### Task 5.1: Create ILoanService Interface

* Define methods for create, fetch, update, and delete operations

### Task 5.2: Implement LoanServiceImpl

* Implement business logic for all CRUD operations
* Logic should include:

  * Check for existing loans by mobile number
  * Generate loan number and defaults
  * Save and update loan entities
  * Return messages or DTOs

### Task 5.3: Create LoansController

* Base path: `/api`
* Expose endpoints:

  * `POST /create?mobileNumber=`
  * `GET /fetch?mobileNumber=`
  * `PUT /update`
  * `DELETE /delete?mobileNumber=`

### Expected Outcome

* Endpoints accessible and functional
* Validations triggered on bad input
* Use Postman to test all endpoints

---

## Phase 6: Exception Handling

### Task 6.1: Define Custom Exceptions

* `ResourceNotFoundException`
* Other custom exceptions if needed

### Task 6.2: Global Exception Handling

* Implement `GlobalExceptionHandler`
* Catch and respond with structured error messages

### Expected Outcome

* Uniform error structure across all APIs
* Correct status codes and messages

---

## Phase 7: Validation Support

### Task 7.1: Add Validation to DTO

* Use annotations like `@NotEmpty`, `@Email`, `@Pattern`, `@Positive`, `@PositiveOrZero`

### Task 7.2: Enable Validation in Controller

* Use `@Validated` at class level and `@Valid` for method parameters

### Expected Outcome

* Invalid input blocked
* Meaningful error messages returned to clients

---

## Phase 8: Auditing

### Task 8.1: Enable JPA Auditing

* Annotate main class with `@EnableJpaAuditing`
* Create `AuditorAware` bean to return fixed name (e.g., LOANS\_MS)
* Annotate BaseEntity fields

### Expected Outcome

* Audit fields auto-populated
* No manual setting of timestamps or authors in services

---

## Phase 9: API Documentation

### Task 9.1: Integrate SpringDoc OpenAPI

* Add dependency for SpringDoc

### Task 9.2: Annotate DTOs for OpenAPI

* Use `@Schema`, `@ExampleObject`, etc.

### Expected Outcome

* Swagger UI available at `/swagger-ui/index.html`
* All endpoints visible and testable

---

## Final Deliverables

* A complete, functioning Loans Microservice with:

  * Clean architecture
  * Consistent DTO usage
  * Validations and exception handling
  * JPA auditing
  * OpenAPI documentation
* Ready for integration and further testing

---
