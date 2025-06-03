# Cards Microservice Development Task Document

**Project Overview:**

Develop a Spring Boot microservice named `Cards Microservice`. This service should handle full CRUD operations for card data, while adhering to standards such as validation, exception handling, auditing, DTO pattern, and API documentation. It will utilize an in-memory H2 database and follow consistent architecture with other microservices like Accounts and Loans.

---

## Phase 1: Project Initialization

### Task 1.1: Set Up Project Using Spring Initializr

* Visit: [https://start.spring.io](https://start.spring.io)
* Project settings:

  * Language: Java
  * Build Tool: Maven
  * Spring Boot Version: 3.5.0
  * Group: `com.easybytes`
  * Artifact: `cards`
  * Name: `cards`
  * Description: `Cards Microservice`
  * Package Name: `com.easybytes.cards`
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

* Project compiles successfully with Maven
* Runs locally on port 9000

---

## Phase 2: H2 Configuration and Schema

### Task 2.1: Configure application.yml

* Set server port to 9000
* Setup H2 datasource and JPA configuration
* Enable H2 console access

### Task 2.2: Define schema.sql

* Place the file in `src/main/resources`
* Define table structure for `cards`

```sql
CREATE TABLE cards (
  card_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  card_number VARCHAR(16),
  card_type VARCHAR(50),
  total_limit DECIMAL(19,2),
  amount_used DECIMAL(19,2),
  available_amount DECIMAL(19,2),
  mobile_number VARCHAR(15),
  created_date TIMESTAMP,
  updated_date TIMESTAMP
);
```

### Expected Outcome

* H2 console available at `/h2-console`
* Table `cards` should initialize correctly at application startup

---

## Phase 3: Entity and Repository Layer

### Task 3.1: Create BaseEntity Class

* Common fields:

  * createdDate
  * updatedDate
* Include JPA auditing annotations

### Task 3.2: Create CardsEntity Class

* Fields:

  * cardId (primary key)
  * cardNumber
  * cardType
  * totalLimit
  * amountUsed
  * availableAmount
  * mobileNumber
* Extend BaseEntity

### Task 3.3: Define CardsRepository Interface

* Extend `JpaRepository<CardsEntity, Long>`
* Add methods to find by mobileNumber and cardNumber

### Expected Outcome

* Entity mapped correctly to the table
* Repository provides necessary access methods

---

## Phase 4: DTO Layer

### Task 4.1: Create CardsDTO

* Fields:

  * mobileNumber
  * cardType
  * totalLimit
  * amountUsed
  * availableAmount
* Use appropriate validation annotations like:

  * `@NotNull`, `@Size`, `@PositiveOrZero`

### Task 4.2: Create ResponseDto and ErrorResponseDto

* Standardized response formats for success and error cases

### Expected Outcome

* DTOs separate API contract from internal entities
* Internal IDs should not be exposed

---

## Phase 5: Business Logic and Controller Layer

### Task 5.1: Create CardService Interface

* Define methods for create, fetch, update, delete operations

### Task 5.2: Implement CardServiceImpl

* Implement logic:

  * Create card if not exists for mobile number
  * Generate 12-digit card number
  * Set defaults (e.g., total limit 100000)
  * Save and retrieve cards
  * Update using DTO values
  * Delete card by mobile number

### Task 5.3: Create CardsController

* Base path: `/api`
* Endpoints:

  * `POST /create?mobileNumber=`
  * `GET /fetch?mobileNumber=`
  * `PUT /update`
  * `DELETE /delete?mobileNumber=`

### Expected Outcome

* Each endpoint accessible and functional
* Validate via Postman or Swagger UI

---

## Phase 6: Exception Handling

### Task 6.1: Define Custom Exceptions

* `CardAlreadyExistsException`
* `ResourceNotFoundException`

### Task 6.2: Global Exception Handler

* Centralize all exception responses with proper codes and messages

### Expected Outcome

* Error responses are user-friendly and standardized

---

## Phase 7: Validation

### Task 7.1: Apply Field-Level Validations

* Annotate DTO fields with validation rules

### Task 7.2: Enable Validation Support

* Use `@Valid` in controller methods
* Use `@Validated` at controller level

### Expected Outcome

* Invalid data is caught and meaningful messages are returned

---

## Phase 8: Auditing Support

### Task 8.1: Enable JPA Auditing

* Add `@EnableJpaAuditing` to the main application class
* Implement `AuditorAware` bean to return service ID or static user
* Annotate BaseEntity fields

### Expected Outcome

* createdDate and updatedDate fields auto-populate

---

## Phase 9: API Documentation

### Task 9.1: Integrate SpringDoc OpenAPI

* Add SpringDoc dependencies

### Task 9.2: Annotate DTOs and Controllers

* Use `@Schema` and `@ExampleObject` for documentation

### Expected Outcome

* API documentation available at `/swagger-ui/index.html`
* Endpoints testable directly from the UI

---

## Final Deliverables

* Fully functional Cards Microservice with:

  * DTO-based architecture
  * CRUD support
  * Validations and exception handling
  * Auditing
  * OpenAPI documentation
* All features tested via Postman and/or Swagger UI

---