# Cards and Loans Microservices

## 1. 🔥 Your Next Challenge: Build Cards and Loans Microservices with the Same Standards!

Congrats! 🎉 We’ve completed the **Accounts Microservice** business logic along with all the key standards we discussed. Now, it's your turn to **build the Cards and Loans Microservices** following the *same* guidelines and best practices. Let me walk you through the process so you can get started confidently! 🚀

### Step 1: Create Your Spring Boot Applications

Head over to [start.spring.io](https://start.spring.io) and generate two new Spring Boot projects for **Cards** and **Loans** microservices with the required dependencies like **Spring Web**, **Spring Data JPA**, and **H2 Database**.

### Step 2: Implement the Core Standards

Once you have your basic projects, it’s time to implement everything we covered in Accounts:

* Configure **H2 database** and connection settings
* Define **entities** and **JPA repositories**
* Use the **DTO pattern** for clean API data transfer
* Build your **CRUD REST APIs** (Create, Read, Update, Delete)
* Implement **global exception handling**
* Add **auditing functionality** for tracking entity changes
* Document your APIs using **OpenAPI / Swagger**

These standards ensure consistency, maintainability, and quality across all your microservices. 💡

### Step 3: Don’t Stress Over Details — Use the GitHub Repo as Your Guide!

You might be wondering: *“What should the table names be? What about field names, class names, method signatures?”* No worries! 😌

Visit the GitHub repo:
**github.com/easybites/microservice**

Inside the `section2` folder, you’ll find:

* **Accounts**, **Cards**, and **Loans** microservices source code
* `schema.sql` files showing table structures, like the `loans` table with fields such as:

```sql
CREATE TABLE loans (
  loan_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mobile_number VARCHAR(20),
  loan_number VARCHAR(50),
  loan_type VARCHAR(50),
  total_loan DECIMAL,
  amount_paid DECIMAL,
  outstanding_amount DECIMAL
);
```

* `application.yml` with database and port configs:

  * Loans service runs on port **8090**
  * Cards service runs on port **9000**

Refer to the code and configs there for naming conventions and structure so your microservices align perfectly with the standards.

### Step 4: Build & Learn by Doing!

Feel free to:

* **Write your own code** from scratch using the repo as a reference
* Or **carefully reuse and adapt** code from the repo

The goal is for you to *understand the process and standards* deeply by practicing yourself. 💪

### Bonus: Postman Collections & Useful Links

Inside the GitHub repo, you’ll also find a **Postman collection JSON** file. Import it into Postman to test your APIs easily. 🛠️

Also, I’ve included important links for your reference:

* Spring Boot official docs
* Start.spring.io for quick project setup
* DTO pattern blog
* OpenAPI and SpringDoc documentation for API docs

Everything you need to succeed is there! 📚

I hope this gives you a clear path forward for your assignment. Take your time, explore the repo, and build those Cards and Loans microservices with confidence. I’ll be explaining the code and deeper concepts for these microservices in upcoming lectures. Until then, happy coding! 👋✨

---

## 2. ## 🚀 Building and Testing the Loans Microservice: A Quick Walkthrough

I hope you’ve had success building the **Loans** and **Cards** microservices! If not, no worries — I’m here to walk you through everything quickly. Make sure to **download the code from the GitHub repo** and set it up in your IDE so you can test the microservices using Postman by sending requests and validating responses. 🛠️

### Project Setup & Dependencies 📦

In the `pom.xml`, I followed consistent naming conventions:

```xml
<groupId>com.easybytes</groupId>
<artifactId>loans</artifactId>
<version>1.0.0</version>
<name>Loans Microservice</name>
<java.version>17</java.version>
```

Dependencies include Spring Boot Starter Web, Data JPA, Validation, Actuator, H2 Database, Lombok, SpringDoc OpenAPI, and DevTools — pretty much the same as Accounts Microservice to keep standards consistent.

### Configuration Highlights ⚙️

In `application.yaml`, the Loans service runs on port **8090** (please keep this consistent to avoid config chaos later):

```yaml
server:
  port: 8090
spring:
  datasource:
    url: jdbc:h2:mem:loansdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Database Schema & JPA Entities 🗄️

The database has a **single `loans` table**, with these key columns:

* `loan_id` (Primary Key, auto-generated)
* `mobile_number` (links to customer in Accounts Microservice)
* `loan_number`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`
* Metadata columns for auditing

The entity extends a common **BaseEntity** with auditing fields and uses annotations like:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long loanId;

@Column(nullable = false)
private String mobileNumber;

private String loanNumber;
private String loanType;
private BigDecimal totalLoan;
private BigDecimal amountPaid;
private BigDecimal outstandingAmount;
```

### DTO & Validation 🎯

Using the DTO pattern hides technical details (like `loanId`) from clients and focuses on business data:

```java
public class LoanDTO {
  @NotEmpty @Pattern(...) private String mobileNumber;
  private String loanNumber;
  private String loanType;
  @Positive private BigDecimal totalLoan;
  @PositiveOrZero private BigDecimal amountPaid;
  @PositiveOrZero private BigDecimal outstandingAmount;
}
```

Validation annotations like `@Positive` and `@PositiveOrZero` ensure only valid values get accepted. OpenAPI annotations are used to generate API docs automatically.

### REST Controller & APIs 🔌

The controller exposes endpoints:

* **POST** `/api/create?mobileNumber=` — Create a new loan
* **GET** `/api/fetch?mobileNumber=` — Fetch loan details
* **PUT** `/api/update` — Update loan details (request body with LoanDTO)
* **DELETE** `/api/delete?mobileNumber=` — Delete loan by mobile number

Example snippet for create:

```java
@PostMapping("/create")
public ResponseEntity<String> createLoan(@RequestParam String mobileNumber) {
  loanService.createLoan(mobileNumber);
  return new ResponseEntity<>("Loan created successfully", HttpStatus.CREATED);
}
```

### Service Layer & Business Logic 💼

The `LoanServiceImpl` implements logic similar to Accounts:

* Check if loan exists by mobile number, throw exception if yes
* Generate random 2-digit loan number, set default loan type & amounts
* Save loan entity and return confirmation
* Fetch loans by mobile number with custom repository methods
* Update loans by loan number using DTO mapper
* Delete loans by mobile number

Example creating loan with underscores in numeric literals for readability (Java 7+ feature):

```java
BigDecimal totalLoan = new BigDecimal("100_000");  // equals 100000
```

### Exception Handling & Validation Feedback ⚠️

All API endpoints return clear validation errors and handle exceptions gracefully:

* Invalid input like negative numbers or invalid mobile numbers triggers validation errors
* Lookup failures return `ResourceNotFoundException` with meaningful messages
* Postman tests confirm 201, 200, 417, and 404 responses work as expected

### Testing with Postman 🧪

Import the Postman collection from the GitHub repo. For example, to create a loan:

```
POST http://localhost:8090/api/create?mobileNumber=9999999999
```

Then fetch, update, and delete loans using the same mobile number. Always use the same mobile number across Accounts, Loans, and Cards services for smooth integration.

By repeatedly building and testing these microservices, you’ll internalize best practices, coding standards, and architecture patterns. Next up: **Cards Microservice**, which follows a very similar structure and logic. Stay tuned! 🙌

If you want, you can skip the next lecture if you're confident — but repetition helps solidify these concepts deeply. Thanks for following along, and see you in the next session! 👋

---

## 3. ## 🚀 Quick Guide to Setting Up and Understanding the Cards Microservice

In this section, we’ll rapidly walk through the **Cards Microservice** setup and code explanation. Since we’ve covered similar steps for the Accounts and Loans microservices, this will be a streamlined overview focusing on the essentials. Let’s dive in! ⚡

### 1. Validate the `pom.xml` 📝

Make sure your `pom.xml` file is correctly configured:

* **Artifact ID** and **name** should both be set to `cards`.
* Verify that all necessary dependencies are included, especially the **H2 database** dependency for in-memory testing.

```xml
<artifactId>cards</artifactId>
<name>cards</name>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 2. Configure `application.yaml` 🔧

In the `resources` folder, open `application.yaml` and set the server port to **9000** for the Cards microservice:

```yaml
server:
  port: 9000
spring:
  datasource:
    url: jdbc:h2:mem:cardsdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
```

**Port recap:**

* Accounts: 8080
* Loans: 8090
* Cards: 9000

### 3. Define Database Schema (`schema.sql`) 📊

Create a single table `cards` with the following structure:

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

This table tracks card details with `card_id` as the primary key auto-generated by Spring Data JPA.

### 4. Create Entities & DTOs 🧩

* **BaseEntity:** Common fields like `created_date` and `updated_date`.
* **CardsEntity:** Represents the `cards` table with JPA annotations for columns and primary key generation.

Use DTO classes to implement the **DTO pattern** for cleaner data transfer. Example validation annotations:

```java
public class CardsDTO {
  @NotNull
  @Size(min = 10, max = 15)
  private String mobileNumber;

  @NotNull
  private String cardType;

  @PositiveOrZero
  private BigDecimal totalLimit;

  // getters and setters
}
```

### 5. Repository Layer 🗃️

The `CardsRepository` extends `JpaRepository` and includes custom methods:

```java
Optional<CardsEntity> findByMobileNumber(String mobileNumber);
Optional<CardsEntity> findByCardNumber(String cardNumber);
```

### 6. Controller Layer 🎛️

`CardsController` exposes 4 APIs mapped under `/api`:

* **POST /create:** Create a new card by mobile number.
* **GET /fetch:** Retrieve card details by mobile number.
* **PUT /update:** Update card details using `CardsDTO`.
* **DELETE /delete:** Delete a card by mobile number.

Basic snippet:

```java
@RestController
@RequestMapping("/api")
public class CardsController {

  @Autowired
  private CardService cardService;

  @PostMapping("/create")
  public ResponseEntity<?> createCard(@RequestParam String mobileNumber) {
    cardService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/fetch")
  public ResponseEntity<CardsDTO> fetchCard(@RequestParam String mobileNumber) {
    return ResponseEntity.ok(cardService.fetchCardByMobileNumber(mobileNumber));
  }

  // update and delete methods similarly implemented
}
```

### 7. Service Layer 💼

`CardServiceImpl` implements business logic:

* **Create Card:** Check if a card exists for the mobile number. If yes, throw an exception; if no, generate a new 12-digit random card number, set default credit limit (`100,000`), and save.
* **Fetch Card:** Retrieve card by mobile number or throw ResourceNotFoundException.
* **Update Card:** Fetch by card number, update fields from DTO, save the entity.
* **Delete Card:** Fetch by mobile number and delete by card ID.

Example for card creation:

```java
public CardsDTO createCard(String mobileNumber) {
  if (repository.findByMobileNumber(mobileNumber).isPresent()) {
    throw new CardAlreadyExistsException("Card already exists for this mobile number");
  }
  CardsEntity card = new CardsEntity();
  card.setCardNumber(generateRandomCardNumber(12));
  card.setMobileNumber(mobileNumber);
  card.setCardType("CREDIT");
  card.setTotalLimit(BigDecimal.valueOf(100000));
  card.setAmountUsed(BigDecimal.ZERO);
  card.setAvailableAmount(card.getTotalLimit());
  repository.save(card);
  return mapper.toDTO(card);
}
```

### 8. Exception Handling & Utilities ⚠️

Custom exceptions like `CardAlreadyExistsException` and `ResourceNotFoundException` are globally handled with a dedicated exception handler. Also, a mapper class helps convert between entities and DTOs.

### 9. Testing APIs with Postman 🧪

* **Create Card:** POST `/api/create` with `mobileNumber`. Response: `201 Created`.
* **Fetch Card:** GET `/api/fetch?mobileNumber=xxx`. Response: Card details JSON.
* **Update Card:** PUT `/api/update` with updated DTO in the body. Response: `200 OK`.
* **Delete Card:** DELETE `/api/delete?mobileNumber=xxx`. Response: `200 OK` or `404 Not Found` if no card.

You can test validation errors by sending invalid data (e.g., wrong card number length or invalid mobile number) and see the validation messages.

### 10. Final Steps ✔️

* Build and run your application in debug mode on port **9000**.
* Access Swagger UI at `http://localhost:9000/swagger-ui/index.html` to explore the Cards APIs documentation.

🎉 With this, your **Cards Microservice** is complete and aligned with the standards established for the course! Take your time to explore each microservice, test all endpoints thoroughly, and understand the codebase deeply. This foundation will prepare you for more advanced concepts ahead. Enjoy the learning journey — and yes, don’t forget to take a well-deserved break! ☕️✨

---
