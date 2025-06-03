# Account Microservice Development Task Document

**Project Overview:**

Develop a robust Spring Boot microservice named `Accounts Microservice`. This service will support full CRUD operations on `Customer` and `Account` data, integrated with input validation, global exception handling, auditing, and OpenAPI documentation.

---

## Phase 1: Project Initialization

### Task 1.1: Set Up Project Using Spring Initializr

* Visit: [https://start.spring.io](https://start.spring.io)
* Project settings:

  * Language: Java
  * Build Tool: Maven
  * Spring Boot Version: 3.5.0
  * Group: `com.knowprogram`
  * Artifact: `accounts`
  * Name: `accounts`
  * Description: `Microservice for accounts`
  * Package Name: `com.knowprogram.accounts`
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

### Expected Outcome

* Project should build successfully with `mvn clean install`
* Openable in IntelliJ IDEA or preferred IDE with Maven integration.

---

## Phase 2: Basic Configuration and Hello World API

### Task 2.1: Create Basic REST Endpoint

* Create a controller named `AccountsController`.
* Add a simple GET endpoint `/sayHello` that returns a static welcome message.

### Task 2.2: Configure application.yml

* Convert the default `application.properties` to `application.yml`.
* Define configurations for server port, datasource (H2), JPA settings, and enable the H2 console.
* Include a `schema.sql` file to auto-generate tables when the H2 database starts.

```sql
CREATE TABLE IF NOT EXISTS customer (
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  email VARCHAR(50),
  mobile_number VARCHAR(20),
  created_at TIMESTAMP,
  created_by VARCHAR(50),
  updated_at TIMESTAMP,
  updated_by VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS accounts (
  account_number VARCHAR(20) PRIMARY KEY,
  customer_id INT,
  account_type VARCHAR(20),
  branch_address VARCHAR(100),
  created_at TIMESTAMP,
  created_by VARCHAR(50),
  updated_at TIMESTAMP,
  updated_by VARCHAR(50),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
```

### Expected Outcome

* Accessing `/sayHello` should return the expected message.
* The H2 console should be accessible at `/h2-console`.

---

## Phase 3: Database Schema and Entities

### Task 3.1: Define schema.sql

* This step is now integrated into Task 2.2 as part of H2 setup.
* Ensure the SQL file is named `schema.sql` and placed under `src/main/resources`.

### Task 3.2: Create Entity Classes

#### BaseEntity

* Abstract class with metadata fields:

  * createdAt
  * createdBy
  * updatedAt
  * updatedBy
* Should include JPA auditing annotations.

#### Customer

* Entity representing the `customer` table.
* Fields should include:

  * customerId (primary key)
  * name
  * email
  * mobileNumber
* Should extend `BaseEntity`.

#### Accounts

* Entity representing the `accounts` table.
* Fields should include:

  * accountNumber (primary key)
  * customerId (foreign key)
  * accountType
  * branchAddress
* Should extend `BaseEntity`.

### Task 3.3: Define Repositories

* Create `CustomerRepository` and `AccountsRepository`.
* Extend Spring Data JPA's `JpaRepository` interface.
* Add custom finder methods as needed.

### Expected Outcome

* Entities should map correctly to the H2 schema.
* Data inspection via H2 console should be functional.

---

## Phase 4: DTO Layer

### Task 4.1: Create DTO Classes

* Package: `com.knowprogram.accounts.dto`

#### CustomerDto

* Fields:

  * name
  * email
  * mobileNumber
  * accountsDto (nested DTO)

#### AccountsDto

* Fields:

  * accountNumber
  * accountType
  * branchAddress

#### ResponseDto / ErrorResponseDto

* Use to standardize success and error response formats.

### Expected Outcome

* DTOs should abstract away entity details.
* Internal database fields like IDs should be excluded where unnecessary.

---

## Phase 5: Business Logic and Controllers

### Task 5.1: Create Controller Class `AccountsController`

* Base route should be `/api`.
* Define endpoints for:

  * Create: `POST /create`
  * Read: `GET /fetch` with mobile number query param
  * Update: `PUT /update`
  * Delete: `DELETE /delete` with mobile number query param

### Task 5.2: Implement Service Layer

* Define interface `IAccountsService`.
* Implement `AccountsServiceImpl` with methods for each operation.

### Task 5.3: Create Mapper Classes

* `AccountsMapper` and `CustomerMapper` should convert between entities and DTOs.

### Expected Outcome

* Each endpoint should be testable using Postman or Swagger UI.
* Service and controller layers should follow clean architecture.

---

## Phase 6: Exception Handling

### Task 6.1: Define Custom Exceptions

* Define `CustomerAlreadyExistsException` for duplicates.
* Define `ResourceNotFoundException` for missing records.

### Task 6.2: Global Exception Handler

* Implement `GlobalExceptionHandler` to handle all known and unknown exceptions.

### Expected Outcome

* Consistent, user-friendly error messages.
* Proper HTTP status codes in responses.

---

## Phase 7: Input Validation

### Task 7.1: Apply Field-Level Validations

* Annotate DTO fields with constraints like `@NotEmpty`, `@Email`, and `@Pattern`.

### Task 7.2: Enable Validation in Controller

* Use `@Valid` for request body and query parameters.
* Use `@Validated` at controller level.

### Expected Outcome

* Invalid requests return detailed validation error messages.
* API should reject malformed inputs gracefully.

---

## Phase 8: Auditing Support

### Task 8.1: Enable JPA Auditing

* Enable auditing in the main application class.
* Implement an auditor provider returning a fixed service name.
* Annotate `BaseEntity` fields accordingly.

### Expected Outcome

* Metadata fields are auto-populated on create and update.
* Manual population should be removed from service layer.

---

## Phase 9: API Documentation

### Task 9.1: Integrate SpringDoc OpenAPI

* Add SpringDoc OpenAPI dependency.
* Enable Swagger UI generation.

### Task 9.2: Enhance Documentation with Annotations

* Add schema and example annotations to DTO fields.

### Expected Outcome

* Interactive Swagger UI available at `/swagger-ui/index.html`
* All endpoints documented with input/output structure and validations.

---

## Final Deliverables:

* Fully functional Spring Boot microservice with CRUD support.
* H2 database integration.
* Clean architecture with DTO abstraction.
* Centralized exception handling and validation.
* Auditing and API documentation completed.

Ensure all implementations align with clean code practices and production-readiness standards. Developers should follow the document sequentially and verify each step against expected outcomes.

---