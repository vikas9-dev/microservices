# Building Microservices using Spring Boot

## 1. 🚀 The First Challenge in Building Microservices (And How to Solve It) 🧱➡️☁️

In the previous lectures, we explored **what microservices are** and how they differ from **monolithic** and **SOA** architectures 🏗️🆚🧩. By now, it's clear that microservices offer a lot of advantages — flexibility, scalability, independent deployment, and more 🔄⚙️💡.

But here comes the next big question: **How do we actually build microservices in real-world projects?** 🤔 At the end of the day, some brilliant developer (like you! 👨‍💻👩‍💻) has to write the code, usually using a backend language like **Java**.

Throughout this course, we’ll introduce several real-world challenges that developers often face while building microservices — and more importantly, I’ll show you the best practices and solutions to overcome them 🛠️✅.

Let’s begin with **Challenge #1: How do we efficiently build and deploy microservices?**

In traditional web applications (like monoliths), we usually:

* Write all our code in Java 🧾
* Package it as a **WAR** or **EAR** file 📦
* Deploy it manually to a web server like **Tomcat** 🔁

This process is **time-consuming** and doesn’t scale well. Imagine doing this for **hundreds or even thousands of microservices** — it would be chaotic and nearly impossible to manage! 😵‍💫

So, what’s the solution? Let’s count to five while you guess... 1️⃣ 2️⃣ 3️⃣ 4️⃣ 5️⃣ 🎯

### 🪄 The Answer is: **Spring Boot**

**Spring Boot** is the framework that solves this exact problem. With Spring Boot, we can:

* Rapidly develop microservices using minimal configuration 🏃‍♂️💻
* Skip the manual packaging and server deployment ❌📦
* Use embedded servers like **Tomcat** for faster deployment ⚡
* Seamlessly integrate with CI/CD pipelines for **automated deployment** 🚀

💡 Throughout this course, we’ll use **Spring Boot** to build all our microservices. Don’t worry — I’ll explain every concept clearly, from creating REST endpoints to managing configurations.

But here’s a small heads-up: If you're completely new to **Spring Framework** and concepts like *Beans*, *Autowiring*, *Spring MVC*, or *Spring Security*, I highly recommend brushing up on the basics first 📚.

Of course, you can take **any course** you like — what matters is that you’re **confident with Spring** before diving deep into microservices 🔍.

Once you’re comfortable with the basics, you’re all set to join us in the exciting journey of building real-world microservices with Spring Boot 🎉💼

---

## 2. 🌱 Introduction to Spring Boot: The Ultimate Framework for Java Microservices 🚀

We talked about one of the key challenges developers face when building microservices — and we also saw how **Spring Boot** is the best solution to overcome that 🧱➡️⚙️. So now you might be wondering: **What exactly is Spring Boot, and why is it the top choice for building Java-based microservices?** Let’s dive in! 🏊‍♂️

Spring Boot is a powerful framework built on top of the **Spring Framework**. It simplifies the process of developing and deploying Java web applications — especially microservices — by reducing the need for complex configurations and boilerplate code 🎯📉. With Spring Boot, you can focus on your **business logic** while the framework takes care of the heavy lifting — like packaging, running, and deploying your app inside an **embedded server** like Tomcat 🚀📦.

This means every microservice you build becomes a **self-contained, executable JAR** (also known as a **fat jar** or **uber jar**), bundled with everything it needs to run — no need for separate servers like Tomcat, Jetty, or JBoss! 🗃️🔥

Let’s break down the key advantages of using Spring Boot for microservices:

🔧 **Auto Configuration & Dependency Injection**
Spring Boot provides out-of-the-box support for common configurations. As soon as you start building a web app, it automatically sets up things like embedded Tomcat on port 8080 — saving you a ton of setup time! If the default settings don't suit your needs, you can easily override them using properties 🛠️🧩.

🔥 **Embedded Web Servers**
Forget installing and managing external servers. Spring Boot apps run with embedded servers like Tomcat, Jetty, or Undertow. This makes your services portable, lightweight, and cloud-ready ☁️📦.

📊 **Production-Ready Features**
Spring Boot comes with **Actuator**, a built-in toolkit that exposes application health checks, metrics, and configurations with zero effort. Monitoring, debugging, and managing your services becomes effortless 👀📈.

⚡ **Quick Project Bootstrap with Starters**
Gone are the days of manually adding and configuring dozens of dependencies. With Spring Boot **starter projects**, you just declare what you need — like MySQL, Kafka, or Redis — and it handles the rest 🔌📦. This drastically improves development speed and consistency across projects.

☁️ **Cloud Native & DevOps Friendly**
Spring Boot apps are **cloud-ready** by design. You can easily **containerize** them using Docker 🐳, deploy them to Kubernetes ☸️, or host them on cloud platforms like AWS, Azure, or GCP 🌍. Later in the course, you’ll learn how to build containers and deploy your microservices to a real Kubernetes cluster.

🛠️ **Simplified Architecture**
Let’s visualize the shift:
In traditional monolithic apps, you needed:

* Java Runtime (JVM) 🖥️
* External web servers (Tomcat, Jetty, etc.) ⚙️
* WAR/EAR packaging and manual deployments 📂🛠️

With Spring Boot, you only need:

* Java Runtime (JVM)
* A **fat jar** that includes the embedded server and all dependencies
  Just run the jar, and your microservice is live! 🏁✨

This simplification doesn’t just make life easier for developers — it also reduces the operational burden on **DevOps** and **platform teams**, who no longer need to manage server environments for each service 🧑‍💼🤝👨‍💻.

To sum it up: **Spring Boot is modern, efficient, cloud-friendly, and perfect for building scalable microservices**. As we progress through this course, you'll see these advantages in action through hands-on demos and real-world examples 💡🧪.

Don’t worry if you’re still new to Spring Boot — I’ll walk you through everything step-by-step. By the end of the course, you'll be fully confident in using it to build powerful microservices. See you in the next lecture! 👋😊

---

## 3. 🔗 Understanding REST Services: The Backbone of Microservices Communication 🧩

When we talk about building microservices using frameworks like Spring Boot, we're essentially talking about building **RESTful services** behind the scenes 🌐. After all, what *is* a microservice? It’s a small, independent service that exposes its business functionality via APIs — typically through **REST APIs** — to other services or frontend applications 💬➡️🖥️.

REST services enable **synchronous communication** between systems — meaning when one application calls a microservice, it waits for the response before proceeding ⏳➡️✅. While asynchronous communication using tools like Kafka or RabbitMQ is also common (and we'll explore that later in the course), REST is the most widely used pattern — and it's our starting point 🚦📬.

### 🧾 What is a REST Service?

REST (Representational State Transfer) is an architectural style that works over the **HTTP protocol**. Compared to older SOAP-based services, REST is **lightweight**, primarily because it uses **JSON** for data exchange instead of verbose XML ⚖️📉. This makes it ideal for modern web and mobile applications.

When building a REST service, you expose **endpoints** that can be invoked by clients (like a browser, mobile app, or another backend service). These endpoints map to various operations — for example, creating or retrieving data — and are defined using standard HTTP methods 📡💻.

REST APIs are everywhere:

* A frontend UI (React, Angular, etc.) talks to your backend via REST.
* Two microservices talk to each other through REST.
* Even third-party apps can consume your service through RESTful endpoints 🔄🌍.

### 🔁 CRUD Operations and HTTP Methods

At the heart of REST lies **CRUD** — Create, Read, Update, and Delete. Each of these operations maps to a specific HTTP method:

🆕 **POST** — Used to **create** new data
📖 **GET** — Used to **read** or retrieve data
✏️ **PUT**/**PATCH** — Used to **update** existing data
🗑️ **DELETE** — Used to **remove** data

The difference between **PUT** and **PATCH** is that PUT updates the **entire resource**, while PATCH updates **specific fields** 🔍.

Using these methods correctly ensures your APIs follow widely accepted web standards, which improves readability, usability, and interoperability 🧭.

### 🛡️ Input Validation is a Must

Don’t rely on the client to validate inputs — that’s risky 😬. REST APIs should perform **server-side input validation** to protect against invalid or malicious requests. Whether the client is a browser, mobile app, or another server, your backend must verify every input carefully ✅🔐.

### ⚠️ Handle Exceptions Gracefully

REST APIs must also provide **meaningful error responses**. If something goes wrong — whether it's a business logic issue or a system error — always send clear, well-structured messages back to the client 📛📬. This saves time, improves debugging, and avoids painful support calls 😵📞.

### 📝 Document Your APIs (Seriously!)

When your system has **hundreds or even thousands of REST endpoints**, documentation is critical 📚. Standards like **OpenAPI** and **Swagger** help you:

* Define request/response formats
* List all available endpoints
* Share validation rules
* Make life easier for your consumers (both internal and external) 🗺️

Without good documentation, client teams will constantly reach out with basic questions — draining your time and increasing onboarding friction. Well-documented APIs = happier developers and smoother collaboration 🤝✨.

To summarize, building RESTful services is the foundation of microservices architecture — and doing it right involves following some important standards:

* ✅ Correct usage of HTTP methods
* 🛡️ Strong input validation
* ⚠️ Proper exception handling
* 📘 Thorough documentation

In the upcoming lectures, 👋 I’ll walk you through building REST services with **Spring Boot** while implementing all of these best practices. Get ready to build robust, production-ready APIs! 💪🚀

---

## 4. 🚀 Getting Started with Spring Boot Microservices: Hands-On Guide

Finally, it’s time to get our hands dirty by creating microservices using the powerful **Spring Boot framework**! Building RESTful web applications or microservices with Spring Boot is super easy and fast. Let me walk you through the first step.

Head over to [Spring Initializer - https://start.spring.io](https://start.spring.io), the official Spring Boot project initializer. Here, you configure your project settings like language, build tool, Spring Boot version, and dependencies.

### Step 1: Choose Your Project Setup

* **Language:** Java
* **Build Tool:** Maven
* **Spring Boot Version:** 3.5.0 (always pick the latest stable version)
* **Group:** `com.knowprogram`
* **Artifact:** `accounts` (since we’re starting with the Accounts microservice for our People Bank example)
* **Name:** `accounts`
* **Description:** `Microservice for accounts`
* **Package Name:** auto-generated as `com.knowprogram.accounts`
* **Packaging:** Jar (ideal for Spring Boot microservices and containerization)
* **Java Version:** 17 (minimum requirement for Spring Boot 3+; do not use 8 or 11)

```plaintext
Group: com.knowprogram  
Artifact: accounts  
Packaging: Jar  
Java Version: 17  
```

### Step 2: Select Dependencies

Add these essential dependencies for your microservice:

* **Spring Web:** Provides all you need to build REST APIs with embedded Tomcat server.
* **H2 Database:** In-memory database, easy to start without external installations. (Later, we will switch to MySQL with Docker.)
* **Spring Data JPA:** Simplifies database operations and ORM support.
* **Spring Boot Actuator:** For monitoring and managing your microservice (health checks, metrics).
* **Spring Boot DevTools:** Enhances developer productivity with automatic restarts and live reloads during development (only works in dev mode).
* **Lombok:** Reduces boilerplate code like getters/setters with simple annotations.
* **Validation:** Enables standard request validation on incoming data.

### Why follow the exact group, artifact, and package naming?

Many learners face issues when their project structure or naming diverges from the tutorial. To help you troubleshoot faster and compare code easily, please **stick to the exact naming conventions and package structures** I use in this course.

### Step 3: Generate and Import the Project

Click **Generate** on start.spring.io, and a ZIP file (e.g., `accounts.zip`) will download. Extract it into your workspace folder, for example:

```bash
/workspace/microservices/section2/accounts
```

Open the project in your favorite IDE (I recommend **IntelliJ IDEA Community Edition** — free and widely used):

* Open IntelliJ IDEA → File → Open → Navigate to your extracted folder → Open
* When prompted with “Maven build script found,” click **Load** to enable Maven support
* Verify the Maven projects tab shows your project correctly.

Go inside the project and run `mvn clean install` to build the project.

### Step 4: Explore the Project Structure

Inside `src/main/java/com/knowprogram/accounts`, locate the main class:

```java
@SpringBootApplication
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
```

The `@SpringBootApplication` annotation is a shortcut for enabling auto-configuration, component scanning, and configuration support.

### What’s Next?

Currently, the microservice has no REST APIs. In the next lecture, we will create a simple **Hello World** REST endpoint to test your setup.

---

## 5. 🚀 Creating Your First REST API in Spring Boot: Hello World Example 🌍

In the previous lecture, we built a basic Spring Boot web application. Now, let's enhance it by adding a simple REST API that responds with **"Hello World"**. If you’re already comfortable with Spring Boot basics, feel free to fast-forward this section to keep the pace exciting. But if you’re new to this, I recommend following along carefully to grasp the essentials.

### Step 1: Create the Controller Package & Class 📦

Inside your project, create a new package named:

```plaintext
com.knowprogram.accounts.controller
```

Now, add a class named `AccountsController`. This class will contain all REST endpoints related to the accounts microservice.

### Step 2: Add the `@RestController` Annotation 🎯

Annotate your class with `@RestController` to tell Spring Boot that this class will handle REST API requests.

```java
package com.knowprogram.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello World";
    }
}
```

* `@GetMapping("/sayHello")` means this method will respond to HTTP GET requests on the `/sayHello` path.
* The method returns a simple `"Hello World"` string.

### Step 3: Build and Run Your Application 🏃‍♂️

* Make sure to **enable annotation processing** in your IDE because we are using Lombok later (your IDE will prompt you for this).
* Build your project.
* Run your main Spring Boot application class (`AccountsApplication`).

Spring Boot will auto-configure your application and start the embedded Tomcat server on **port 8080** by default.

### Step 4: Test Your API 🎉

Open your browser and navigate to:

```
http://localhost:8080/sayHello
```

You should see:

```
Hello World
```

This confirms your first REST API is working! The GET request hits your controller method and returns the greeting.

### Behind the Scenes: Magic of Spring Boot 🧙‍♂️

Spring Boot auto-configures many things like:

* Server port (default 8080)
* Embedded Tomcat server
* H2 in-memory database (if present)
* Actuator endpoints for monitoring

You don’t need to provide manual configurations to get started. Later, we’ll learn how to customize these defaults.

### Productivity Boost with Spring Boot DevTools ⚡

With the **Spring Boot DevTools** dependency, you get super fast restarts on code changes.

Try changing the return string:

```java
return "Hi World";
```

Save and rebuild — your app restarts in milliseconds automatically! No need for manual restarts. Refresh your browser, and the new message appears instantly.

This is a huge productivity boost compared to traditional Java applications.

### Pro Tip: Customize IntelliJ IDEA for a Better Developer Experience 🎨

If you want your IDE to look like mine with a dark theme and nice fonts:

* Go to **Preferences > Plugins**.
* Search and install themes like **Dark Purple Theme** or **One Dark Theme**.
* After installation, go to **Appearance & Behavior > Appearance** and select your theme.
* Also, enable **annotation processing** by searching “annotation” in preferences and ticking **Enable annotation processing**.

This helps Lombok annotations work smoothly and gives your IDE a sleek look.

That's it for this section! 🎉 You now have a working Spring Boot microservice skeleton with your first REST endpoint. In the next lecture, we’ll explore more APIs and business logic.

---

## 6. 🚀 Setting Up Database Configuration with YAML & H2 Console in Spring Boot

In the previous lecture, we built a simple REST API that says **Hello World** 🌍. Now, before we create more REST APIs for CRUD operations, we must ensure our database setup is ready to interact with data. Currently, we’re using the in-memory **H2 database**, and to configure it properly, we define settings inside the Spring Boot configuration file.

### From `application.properties` to `application.yml` 📁

By default, Spring Boot uses a `application.properties` file for configuration (key-value pairs like `server.port=8080`). However, YAML (`.yml`) format is far more intuitive and widely used in modern DevOps tools like Docker, Kubernetes, and cloud providers (AWS, Azure, GCP). YAML uses **indentation** to represent hierarchical data, making it cleaner and easier to read.

So, we rename `application.properties` to `application.yml` and define configurations like this:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

### How YAML Works 📝

* Use indentation (spaces or tabs) to create hierarchy — no repeated prefixes needed like in `.properties`.
* Each level is denoted by a colon `:` after the key.
* Values are separated by a space after the colon.
* Correct indentation is **critical**; otherwise, Spring Boot won’t parse the file correctly.

For example:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

is represented in YAML as:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: ''
```

### Why Use YAML? 🤔

* Cleaner and easier to maintain.
* Common standard in cloud-native environments.
* Supports complex configurations without repetition.
* Expected to become the default in Spring Boot.

### Setting Up the H2 Database 🗄️

We enable the H2 console for easy access and debugging with:

```yaml
spring:
  h2:
    console:
      enabled: true
```

When you run the app, Spring Boot auto-configures the database connection and web server, exposing the H2 console at:

```
http://localhost:8080/h2-console
```

Use the default JDBC URL, username (`sa`), and an empty password to connect.

### Automating Table Creation with `schema.sql` 🛠️

To avoid manually creating tables every time the app restarts (since H2 is in-memory), we create a `schema.sql` file under `src/main/resources` that contains SQL DDL statements for tables:

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
  account_number INT AUTO_INCREMENT PRIMARY KEY,
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

This ensures tables are automatically created when the app starts.

### Viewing the Tables 🕵️‍♂️

Once your app is running, open the H2 console (`localhost:8080/h2-console`), connect with the default credentials, and explore your tables and data. This is crucial for development and debugging.

### Summary 🧑‍💻

* Switch from `.properties` to `.yml` for better readability and cloud compatibility.
* Configure H2 datasource, port, and JPA settings in `application.yml`.
* Enable the H2 console for easy database inspection.
* Use `schema.sql` for automatic table creation on startup.
* Spring Boot handles all heavy lifting behind the scenes — no boilerplate code needed!

In the next lecture, we’ll explore how to use Spring Data JPA repositories to interact with these tables for CRUD operations. Stay tuned! 🎉

---

## 7. 👉 LOMBOK ISSUE IN INTELLIJ IDEA

Starting with the next lecture, we'll be using Lombok annotations in our code. However, some students using the latest version of IntelliJ IDEA might encounter compilation issues related to these annotations. To ensure a smooth experience, please follow the steps below.

If you’re not facing any issues, feel free to skip these instructions.

### 🛠️ Step 1: Enable Annotation Processing in IntelliJ IDEA

Open IntelliJ IDEA, navigate to: Settings --> Build, Execution, Deployment --> Compiler --> Annotation Processors and ensure that the checkbox **Enable annotation processing** is checked.

### 🛠️ Step 2: Update `pom.xml` Build Configuration

Open your `pom.xml` file and replace the existing build configuration with the following snippet:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>${lombok.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

This snippet ensures that the Spring Boot Maven plugin is included, while also configuring Lombok's annotation processing for your project.

---

## 8. 🚀 Creating Entity Classes & Repositories with Spring Data JPA

In the previous lesson, we successfully created two tables—`accounts` and `customer`—inside our in-memory H2 database. 🔧 As we progress, we'll use the same structure even when migrating to a MySQL database in future sections. But for now, it's time to write the **Java code to interact with these tables**, allowing us to **create, read, update, and delete** (CRUD) data.

### 🛠️ Spring Data JPA Setup

We've already added the required **Spring Data JPA** dependency in our `pom.xml`. This powerful library offers interfaces and classes to simplify database interaction.

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 🧱 Step 1: Create the `BaseEntity` Class

First, create a new package named `entity`. Inside it, add a class called `BaseEntity`. This class holds four **metadata columns** common to all entities:

* `createdAt` ⏰
* `createdBy` 👤
* `updatedAt` 🔁
* `updatedBy` 👤

```java
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column(updatable = false)
  private String createdBy;

  @Column(insertable = false)
  private LocalDateTime updatedAt;

  @Column(insertable = false)
  private String updatedBy;
}
```

This `BaseEntity` class is a **mapped superclass** meant to provide **common auditing fields** for entity classes in a JPA/Hibernate-based application.

### 📄 Explanation

* **`@MappedSuperclass`**: This tells JPA that this class is **not a table**, but its fields should be inherited by child entity classes.
* **Fields**:
  * `createdAt`, `createdBy`: Set when the entity is first created.
    * Marked as `updatable = false` → won't change on update.
  * `updatedAt`, `updatedBy`: Set when the entity is updated.
    * Marked as `insertable = false` → ignored on insert, only used on updates.
* **Lombok annotations**:
  * `@Getter`, `@Setter`, `@ToString`: Automatically generate getter/setter methods and a `toString()` method.

**✅ Purpose**: To **avoid repetition** of common audit fields (like `createdAt`, `updatedBy`, etc.) in every entity and ensure consistent handling of metadata.

### 🧑‍💼 Step 2: Create the `Customer` Entity

Now, let’s model our `customer` table using a class named `Customer`.

```java
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;
  private String name;
  private String email;
  private String mobileNumber;
}
```

* `@Entity`: Marks the class as a JPA entity.
* `@Id` and `@GeneratedValue`: Auto-generates primary key.
* Extends `BaseEntity` to inherit metadata columns.

### 🏦 Step 3: Create the `Accounts` Entity

Next, model the `accounts` table:

```java
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {

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

Note: We’ll generate the `accountNumber` manually in the business logic to avoid generic sequence numbers.

### 📁 Step 4: Create Repositories

Create a new package: `com.knowprogram.accounts.repository`.

Now create interfaces to allow CRUD operations using **Spring Data JPA**:

#### CustomerRepository

```java
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
```

#### AccountsRepository

```java
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
```

With `JpaRepository`, we gain access to dozens of ready-to-use methods like `save()`, `findById()`, `delete()`, and more—**without writing SQL manually**. 🤯

### ✅ Summary

* ✅ Created shared `BaseEntity` for metadata fields.
* ✅ Defined `Customer` and `Accounts` as JPA entities.
* ✅ Used Lombok to reduce boilerplate code.
* ✅ Set up repositories to interact with the DB using Spring Data JPA.

Up next, we’ll implement the service layer and expose endpoints to interact with our database. Stay tuned! 🎯

---

## 9. 💡 Understanding the DTO Pattern in Web Applications 🚀

Previously, we created entity classes to represent each table in our database. These entity classes allow us to **create**, **read**, **update**, and **delete** customer and account data within our web application. But what happens when a client wants **both customer and account data in a single request**? 🤔

Can we send two separate entities in the response? ❌ Not really. A REST API can only return **one object per response**. So, what do we do?

You might think of creating a new class that holds fields from both `Customer` and `Account` entities. While technically possible, **this approach is not ideal**. Why? Because entity classes are **tightly coupled to the database schema** and should **only represent the database tables**. Reusing them across layers can lead to tight coupling, reduced flexibility, and potential maintenance nightmares.

### 🧱 Enter DTO Pattern (Data Transfer Object) 🎯

The **DTO pattern** is a design pattern that helps us **transfer data** between different layers of the application — for example, from the service layer to the presentation layer — **without exposing the internal structure** of our database entities.

#### ✅ Benefits of Using DTOs

1. **📦 Single Unified Response:**
   You can send combined data (like `Customer` and `Account`) in one object by creating a custom DTO class, e.g., `CustomerDetailsDTO`. This avoids multiple requests and streamlines communication.

2. **🔄 Mapper Logic:**
   You can write mapper code to convert entity objects into DTOs.

3. **🌐 Reduced Network Traffic:**
   Without DTOs, the client would have to make two separate API calls — one for customer data and one for account data. With DTOs, they make a **single call** and get a **combined response**, reducing overhead.

4. **📦 Encapsulation & Serialization:**
   DTOs are perfect for handling **serialization**. Whether the client wants the response in **JSON**, **XML**, or **YAML**, you can handle it in one place — the DTO class — instead of spreading logic throughout your application.

5. **🔗 Decoupling Layers:**
   DTOs help **decouple the presentation layer from the data access layer**. Changes in database entities don’t affect the frontend clients. For example, if a new column is added to a table, you **don’t have to expose it** in the DTO if the client doesn’t need it. This protects the client from unnecessary changes and keeps your app maintainable.

### 🛠️ What's Next?

Now that you understand the DTO pattern and its advantages, your next step is to **create corresponding DTO classes** for each of your DB entities. These DTOs will be used in your service responses to the client when they interact with your **REST APIs** or **microservices**.

🎉 With this setup, your application becomes more **modular**, **scalable**, and **easy to maintain**!

---

## 10. ✨ Creating DTO Classes for the Accounts Microservice 🛠️

In this section, let’s roll up our sleeves and **create the DTO classes** for the entity classes defined in our `accounts` microservice. These DTOs will serve as **clean and structured response objects** between our backend and client applications — without exposing internal database structures. 🔐

### 📁 Step 1: Create the `dto` Package

Start by creating a new package named `dto` to house all your DTO classes. Keeping DTOs organized in a dedicated package ensures clean architecture.

### 🧾 Step 2: Create `AccountsDto` Class

Create a class named `AccountsDto` and annotate it with Lombok’s `@Data` to generate all boilerplate code like getters, setters, `toString()`, and more. We're intentionally **excluding the `customerId`** because it’s specific to the database and irrelevant to client applications.

```java
package com.example.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
    private String accountNumber;
    private String accountType;
    private String branchAddress;
}
```

> ✅ **Note:** Always suffix your DTOs with `Dto` for easy identification and separation from JPA entities.

### 🧾 Step 3: Create `CustomerDto` Class

Similarly, create a `CustomerDto` class, but only include fields like `name`, `email`, and `mobileNumber`. Skip `customerId` for the same reason — clients don’t need internal DB IDs.

```java
package com.example.accounts.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}
```

### 📦 Step 4: Create `ResponseDto` Class for Success Responses ✅

When your API processes a request, you often want to send a success message or status. For this, we’ll create a `ResponseDto`.

```java
package com.example.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMsg;
}
```

> 💡 The `@AllArgsConstructor` annotation helps us quickly create full-argument constructors when returning responses.

### ⚠️ Step 5: Create `ErrorResponseDto` Class for Error Responses ❌

When something goes wrong, send a detailed error response back to the client. The `ErrorResponseDto` includes the API path, error code, message, and timestamp.

```java
package com.example.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMsg;
    private LocalDateTime errorTime;
}
```

> 🧠 This helps frontend developers and consumers of your API understand **what failed, why it failed**, and **when it happened**.

### 🧰 What’s Next? Mapper Logic Coming Soon 🧩

At this stage, we’ve successfully created our DTO classes, but we haven’t implemented the **mapper logic** that will convert JPA entities into these DTOs. That’s coming up in future lessons! You’ll write **mapping or aggregation logic** to transform database data into clean DTO responses — possibly using tools like **MapStruct** or writing custom converters.

### 📚 Bonus Insight: DTO Pattern by Martin Fowler 🧠

The **DTO pattern** was popularized by **Martin Fowler**. He describes scenarios where you might have an `Album` and `Artist` entity, but the client only wants a `title` and `artist name`. In such cases, combining relevant fields into a `AlbumDto` using assembler logic is the cleanest way to go.

### 🏁 Summary

With `AccountsDto`, `CustomerDto`, `ResponseDto`, and `ErrorResponseDto` now in place, your **Accounts Microservice is DTO-ready**. 🎉 These DTOs will streamline your data flow, shield your clients from internal changes, and help you maintain a clean separation of concerns.

---

## 11. 🚀 Building the Core Business Logic in Accounts Microservice

It's time to roll up your sleeves and dive into the heart of our **Accounts Microservice** 💡. In this phase, we're going to build the REST API endpoint that allows clients to create new customer accounts and persist the data into our in-memory **H2 database**. Along the way, we'll cover industry best practices like **DTO patterns**, **exception handling**, **response standards**, and **layered architecture**.

### 🧱 Step 1: Setting Up the Controller Layer

We'll begin by cleaning up the `AccountsController`. The old `sayHello` endpoint can be deleted—it's no longer needed. Instead, we'll prefix all API routes using `@RequestMapping("/api")` to maintain consistency.

We also specify that our API produces **JSON** by using:

```java
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
```

> ✅ **Pro tip**: Always use the `MediaType` from `org.springframework.http`.

### 🛠️ Step 2: Creating the `createAccount` Endpoint

We’ll now create a method in `AccountsController` using `@PostMapping("/create")`. This endpoint will accept `CustomerDto` as input and return a `ResponseEntity<ResponseDto>`.

```java
@PostMapping("/create")
public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(
            AccountsConstants.STATUS_201,
            AccountsConstants.MESSAGE_201
        ));
}
```

📌 This ensures a **201 Created** status is sent back, with a success message defined in a constants class.

### 📁 Step 3: Creating the Constants Class

To avoid hardcoding messages and status codes, we create a `AccountsConstants` class under a `constants` package:

```java
public class AccountsConstants {
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";

    private AccountsConstants() {} // Prevent instantiation
}
```

> 🔐 Use `static final` for immutability and define a private constructor to prevent misuse.

### 🧩 Step 4: Designing the Service Layer

Next, we move our business logic into a `service` package. Create an interface named `IAccountsService`:

```java
public interface IAccountsService {
    /**
     * Creates a new account using customer details.
     * @param customerDto The input DTO with customer data
     */
    void createAccount(CustomerDto customerDto);
}
```

Now, add an implementation class `AccountsServiceImpl` under `service.impl`:

```java
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        // Logic will go here
    }
}
```

> 💡 We use Lombok's `@AllArgsConstructor` to simplify constructor injection. If only one constructor exists, Spring Boot auto-wires it automatically.

### 🔄 Step 5: Mapping DTOs to Entities

To convert between DTO and Entity objects, create a `mapper` package and define `AccountsMapper` and `CustomerMapper` classes. Example:

```java
public class AccountMapper {
    public static AccountsDto mapToAccountDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
```

Create similar mappers for `CustomerDto` and `Customer` in the `CustomerMapper` class.

> ⚠️ While libraries like **MapStruct** or **ModelMapper** exist, we use manual mapping to retain full control and avoid introducing unapproved third-party dependencies in critical projects.

### 🧠 Why Manual Mapping?

Imagine needing to **mask mobile numbers** for security before sending them back in the response. Such custom logic is easier to implement manually:

```java
public static String maskMobile(String mobile) {
    return "XXXXXX" + mobile.substring(6);
}
```

You can incorporate this inside your mapper if needed—something not easily achievable with automated mappers.

### 🧪 Wrapping Up

At this point, we've:

✅ Defined a REST API for account creation
✅ Established controller and service layers
✅ Centralized constants for status codes
✅ Created mappers for DTO-Entity conversion
✅ Followed Spring Boot and clean code best practices

You're now well-equipped to build production-grade endpoints! 🎯 In the next session, we’ll write the logic that actually persists data into our **H2 database**.

---

## 12. 🚀 Implementing Customer Registration with Exception Handling in Spring Boot

Now that we've built the mapping logic for converting DTOs to entities, it's time to implement the business logic inside `AccountServiceImpl`. Let's walk through the key steps involved in saving a customer, creating a linked bank account, and handling duplicate registrations using Spring Boot and Spring Data JPA. 🧩

### 🔄 Mapping `CustomerDto` to `Customer` Entity

To begin, convert the incoming `CustomerDto` into a `Customer` entity using the `CustomerMapper`.

```java
Customer customer = customerMapper.mapToCustomer(customerDto, new Customer());
```

This transfers all fields from the DTO to a new `Customer` object, preparing it for persistence.

### 💾 Persisting Customer Using `CustomerRepository`

Use the `CustomerRepository` to save the entity:

```java
Customer savedCustomer = customerRepository.save(customer);
```

Spring Data JPA handles all the boilerplate—SQL preparation, DB connection, execution, commit, and close—behind the scenes. 🎯

Once saved, `savedCustomer` contains the auto-generated `customerId`.

### 🏦 Creating a New Bank Account for the Customer

With the saved customer, generate a linked bank account:

```java
Accounts newAccount = createNewAccount(savedCustomer);
accountsRepository.save(newAccount);
```

Here’s the helper method to create the account:

```java
private Accounts createNewAccount(Customer customer) {
    Accounts account = new Accounts();
    account.setCustomerId(customer.getCustomerId());
    account.setAccountNumber(generateAccountNumber()); // 10-digit random number
    account.setAccountType(AccountType.SAVINGS); // Enum or constant
    account.setBranchAddress("123 Main Street, New York");
    return account;
}
```

And the utility to generate a random 10-digit account number:

```java
private String generateAccountNumber() {
    return String.valueOf(new Random().nextInt(900000000) + 1000000000);
}
```

### ❗ Validating Unique Mobile Numbers

We don’t want multiple customers using the same mobile number. Let’s first create a derived query method in `CustomerRepository`:

```java
Optional<Customer> findByMobileNumber(String mobileNumber);
```

Spring will generate the query based on this naming convention.

In `AccountServiceImpl`, validate the mobile number before saving:

```java
Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

if (optionalCustomer.isPresent()) {
    throw new CustomerAlreadyExistsException("Customer already registered with given mobile number.");
}
```

### 🚫 Creating a Custom Exception

Create `CustomerAlreadyExistsException` inside the `exception` package:

```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
```

This will throw a 400 Bad Request response whenever invoked.

### ⚠️ Handling Exceptions Globally with `@ControllerAdvice`

Create a class `GlobalExceptionHandler`:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
        CustomerAlreadyExistsException ex, WebRequest request) {

        ErrorResponseDto error = new ErrorResponseDto(
            request.getDescription(false),
            HttpStatus.BAD_REQUEST,
            ex.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

This ensures consistent exception handling across controllers without repeating logic.

### 🧪 Testing with Postman

Use Postman to test the API `POST /api/create`. Here's a sample JSON body:

```json
{
  "name": "Rocco Jerry",
  "email": "rocco.jerry@example.com",
  "mobileNumber": "9876543210"
}
```

Ensure field names match the `CustomerDto` structure. Spring Boot uses Jackson under the hood to convert JSON into POJO and vice versa.

If fields like `createdAt` or `createdBy` are missing and non-nullable, populate them before saving:

```java
customer.setCreatedAt(LocalDateTime.now());
customer.setCreatedBy("System");
```

### 🛠️ Autowiring Services Using Constructor Injection

In `AccountsController`, use Lombok’s `@AllArgsConstructor` for constructor-based injection:

```java
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {
    private final IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
```

No need to use `@Autowired` manually when there's only one constructor. Cleaner and recommended! ✅

### ✅ Summary

* 🔄 Mapped DTO to Entity using a Mapper.
* 💾 Persisted data using Spring Data JPA.
* 🏦 Created linked `Accounts` entity.
* ⚠️ Added validation to avoid duplicate mobile numbers.
* 🚫 Threw custom exception with proper HTTP status.
* 🛡️ Handled it using global exception handler.
* 🧪 Tested everything using Postman.

With this, your account creation logic is robust and production-ready! 🚀 Next, we’ll look into handling more generic exceptions and improving error reporting even further.

---

## 13. 🔍 Building a REST API to Fetch Customer & Account Details by Mobile Number 📱

In this section, we'll implement a **REST API** that fetches **customer and bank account details** using the **mobile number** as an input. This is a typical use case in banking applications where end users or client systems want to retrieve information using a mobile number as a unique identifier.

### 🛠️ Step 1: Define the Controller Method

Start by adding a method to `AccountsController`:

```java
@GetMapping("/fetch")
public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
    CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
}
```

* `@GetMapping("/fetch")` makes it a **GET API**.
* `@RequestParam` captures the **query parameter**: `?mobileNumber=xxxx`.
* Returns a **CustomerDto** wrapped in a `ResponseEntity`.

### 🔧 Step 2: Add Method to Service Interface

In `IAccountService`, declare an abstract method:

```java
CustomerDto fetchAccount(String mobileNumber);
```

Then, implement it in `AccountServiceImpl`.

### ⚠️ Step 3: Create Custom Exception – `ResourceNotFoundException`

Create a class `ResourceNotFoundException` extending `RuntimeException` to handle cases where no record is found:

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
```

Set appropriate HTTP status:

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
```

### 🛡️ Step 4: Handle Exceptions Globally

Update your `GlobalExceptionHandler`:

```java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
```

### 📦 Step 5: Implement Business Logic in `AccountServiceImpl`

Use repositories to fetch `Customer` and `Account` entities:

```java
Customer customer = customerRepository.findByMobileNumber(mobileNumber)
    .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

Accounts account = accountRepository.findByCustomerId(customer.getCustomerId())
    .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
```

### 🔁 Step 6: Convert Entities to DTOs

Avoid exposing entity classes directly. Use mappers to convert entities to DTOs:

```java
CustomerDto customerDto = customerMapper.mapToCustomerDto(customer, new CustomerDto());
customerDto.setAccountsDto(accountsMapper.mapToAccountsDto(account, new AccountsDto()));
```

Return the `customerDto` from the service layer.

### 🧪 Step 7: Test the API

* Call: `GET http://localhost:8080/api/fetch?mobileNumber=9876543210`
* Valid number: ✅ Returns `CustomerDto` with embedded `AccountsDto`.
* Invalid number: ❌ Throws `ResourceNotFoundException`.

Sample response on success:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "mobileNumber": "9876543210",
  "accountsDto": {
    "accountNumber": "ACC123456",
    "accountType": "SAVINGS",
    "branchAddress": "New York Branch"
  }
}
```

Sample error response:

```json
{
  "errorCode": 404,
  "errorMessage": "Customer not found with mobileNumber : '0000000000'"
}
```

### 💡 Quick Tips

* If you're using H2 database, remember that data is **lost on restart**. Recreate entities via your **create API** before testing.
* Follow the DTO pattern strictly to avoid leaking sensitive entity data.
* Use meaningful HTTP status codes for better client understanding.
* Always handle edge cases—like missing records—with descriptive errors.

This API is now fully functional and aligned with REST principles. 🔁 Test locally and ensure it matches your GitHub repo code. Debugging is part of the learning process—keep exploring and happy coding! 🚀

---

## 14. ✨ Building the Update Account Details API in the Accounts Microservice 🔄

So far, our **Accounts Microservice** has REST APIs that support **creating** and **fetching** account details. The next logical step is to enable clients to **update** account details via a new REST API. 🔧

### 🚀 What's the Goal?

We want to let client applications, update the account data (except for the **account number**, which is immutable by design). Clients will:

* Fetch existing data via `GET /api/fetch?mobileNumber={mobileNumber}`
* Modify fields like `name`, `email`, `mobileNumber`, `accountType`, and `branchAddress`
* Send updated data to `PUT /api/update`

### ⚠️ Business Rule: Account Number is Immutable

🔐 Once an account number is generated, it **cannot be modified**. It serves as the primary key and is used to fetch and identify the account during updates.

### 🧪 Step-by-Step: Service Layer Changes

1. **Define the method in `IAccountService`:**

```java
boolean updateAccount(CustomerDto customerDto);
```

2. **Implement the method in `AccountService`:**

```java
@Override
public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();

    if (accountsDto != null) {
        String accountNumber = accountsDto.getAccountNumber();
        Accounts existingAccount = accountsRepository.findById(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber));

        // Map new values from DTO to entity
        Accounts updatedAccount = accountsMapper.mapToAccounts(accountsDto, existingAccount);
        accountsRepository.save(updatedAccount);

        Long customerId = updatedAccount.getCustomerId();
        Customer existingCustomer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));

        Customer updatedCustomer = customerMapper.mapToCustomer(customerDto, existingCustomer);
        customerRepository.save(updatedCustomer);

        isUpdated = true;
    }

    return isUpdated;
}
```

### 🌐 Step-by-Step: Controller Layer Changes

In `AccountsController`, add the following method:

```java
@PutMapping("/update")
public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountsService.updateAccount(customerDto);
    return isUpdated ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200))
        : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
}
```

### 🔄 Testing the Update API

1. **Create an account** using the `POST /api/create` endpoint.
2. **Fetch account data** via the `GET /api/accounts/{mobileNumber}` endpoint.
3. **Use the same payload** to update account details using:

```curl
PUT /api/update
Content-Type: application/json
Body:
{
  "name": "Rocco Jerry",
  "email": "rocco@knowprogram.com",
  "mobileNumber": "8800000000",
  "accountType": "Current",
  "branchAddress": "124 Main St",
  "accountNumber": "existing-account-number"
}
```

🔁 On success, you’ll get a 200 OK response.

4. **Verify changes** by calling the GET API again with the new `mobileNumber`.

### ❌ Negative Test Case

Try updating with a **non-existent account number**. You should get:

```json
{
  "message": "Account not found with accountNumber: XYZ123"
}
```

### 🐞 Known Bug: Auditing Fields Not Updating

Currently, fields like `updatedAt` and `updatedBy` are not being updated because we haven’t set them manually. Don’t worry! We’ll soon implement **Spring Data JPA Auditing** to handle these automatically. ⏳

🎯 With this, the **update account** feature in your Accounts Microservice is complete! Next up: implementing the **delete API**. Stay tuned! 👋

---

## 15. 🚀 Building the DELETE API in the Accounts Microservice

In this section, we’ll implement the final piece of our CRUD puzzle: the **Delete API** for the Accounts microservice 🧩. This API will allow your client application to delete customer and account records by simply providing the **mobile number**. Let's walk through the steps needed to get this up and running 💡.

### 🗂️ API Overview

The client application will call a `DELETE` endpoint and pass a **mobile number** as a query parameter. Using that mobile number, we will:

1. Fetch the `Customer` entity.
2. Extract the `customerId`.
3. Delete the associated records from **both the `accounts` and `customers` tables**, since both are linked by `customerId`.

### 🧪 Step-by-Step Implementation

#### 1. Define the Service Layer Method

Inside `IAccountService`, add a new method:

```java
boolean deleteAccount(String mobileNumber);
```

#### 2. Implement the Method in `AccountService`

Use Spring Data JPA methods to perform deletion:

```java
@Override
public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

    Long customerId = customer.getCustomerId();

    accountsRepository.deleteByCustomerId(customerId); // Custom delete method
    customerRepository.deleteById(customerId); // Spring Data JPA built-in method

    return true;
}
```

#### 3. Add Custom Delete Method in `AccountsRepository`

Since `customerId` is **not the primary key** in `Accounts`, we need a custom method:

```java
@Transactional
@Modifying
void deleteByCustomerId(Long customerId);
```

📌 Add `@Transactional` and `@Modifying` because this method modifies data. These annotations ensure proper rollback if anything fails during the operation 🔁.

#### 4. Create the DELETE API in `AccountsController`

In the controller class, add:

```java
@DeleteMapping("/delete")
public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
    boolean isDeleted = accountService.deleteAccount(mobileNumber);
    if (isDeleted) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDto(HttpStatus.OK.value(), "Request processed successfully 😄"));
    } else {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred 😢"));
    }
}
```

### ✅ Testing the API

1. **Create a new account** via the POST API.
2. **Verify the record** with the GET API.
3. **Call the DELETE API**:

```http
DELETE /api/delete?mobileNumber=1234567890
```

Expected response:

```json
{
  "statusCode": 200,
  "message": "Request processed successfully 😄"
}
```

4. **Try to fetch the deleted record** — you should get a `404 Not Found` error.
5. **Try deleting again** — you'll see the proper error response for missing data.

### ⚠️ Known Issue: Metadata Not Updating

Currently, fields like `updatedAt`, `updatedBy`, `createdAt`, and `createdBy` aren’t updating automatically during save or delete. This will be fixed when we implement **auditing using Spring Data JPA** in upcoming lessons. Stay tuned! 🛠️

🎉 Congratulations! You’ve now completed all **four CRUD operations** inside your Accounts Microservice: Create, Read, Update, and Delete. Take a well-deserved break ☕ and get ready — next up, we’ll tackle **exception handling**, **auditing**, and **API documentation** for a more production-grade system!

---

## 16. 🛡️ Handling Runtime Exceptions with Global Exception Handler in Spring Boot

Now that we’ve successfully implemented all four CRUD operations in our **Accounts microservice** 🔄, it’s time to make our APIs more robust by handling **runtime exceptions** gracefully 💥. This ensures that your client application always gets a meaningful response, even when something unexpected goes wrong.

### ⚠️ Why Handle Runtime Exceptions?

Currently, our `GlobalExceptionHandler` only handles two **custom exceptions**:

* `ResourceNotFoundException`
* `CustomerAlreadyExistsException`

But what happens when a **runtime error** like `NullPointerException`, `IllegalArgumentException`, or a DB failure occurs? Without proper handling, Spring Boot throws back stack traces or vague error responses 😬 — and that’s not what we want in a production-grade system.

### 🛠️ Add a Catch-All Exception Handler

We can easily handle all unhandled exceptions using a **generic exception handler**. Just add the following method inside your `GlobalExceptionHandler`:

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest request) {
    ErrorResponseDto errorResponse = new ErrorResponseDto(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}
```

### 🧠 How It Works

* `@ExceptionHandler(Exception.class)` will catch *any* exception that isn't already handled by a more specific method.
* `WebRequest` gives you access to request metadata (like the API path).
* We return a structured `ErrorResponseDto` with:

  * Error code `500`
  * Exception message
  * Request path
  * Timestamp

This gives your API consumers a clear picture of **what went wrong, where, and when** 🕵️‍♂️.

### 🧪 Simulate a Runtime Exception

Let’s intentionally break our controller to test this handler. Remove the `@AllArgsConstructor` from `AccountsController`:

```java
//@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountsController {
    private IAccountService accountService;
    
    // No constructor → autowiring fails → accountService remains null
}
```

Now, trigger any API like `/api/create`. You’ll get a response like:

```json
{
  "statusCode": 500,
  "message": "Cannot invoke \"IAccountService.createAccount(...)\" because \"this.accountService\" is null",
  "path": "/api/create",
  "timestamp": "2025-05-29T12:34:56"
}
```

🌟 This proves that your **global exception handler is working!** It catches unexpected exceptions and provides a clean response instead of a messy stack trace.

### 🧠 A Quick Recap on Annotations

To implement global exception handling in Spring Boot, remember these two core annotations:

* `@ControllerAdvice` → Makes a class a **global exception handler**.
* `@ExceptionHandler(Exception.class)` → Marks a method to handle a specific type (or all types) of exceptions.

That's it! You now have **centralized exception handling** that improves your API's reliability, readability, and maintainability 🔒.

Stay tuned — in upcoming sections, we’ll dive into **auditing metadata** and **documenting APIs**. Until then, take a well-earned break ☕. You're doing great!

---

## 17. ## 🚦 Enforcing Input Validations in REST APIs with Spring Boot

Handling exceptions is only half the job in building robust microservices. 🧱 A key standard in API development is **validating input data** before it hits your service layer. This ensures that your application isn't wasting resources on invalid data and gives meaningful feedback to the client. Let’s explore how we applied this in our `AccountsMicroservice` to validate input data efficiently using Spring Boot's validation framework. ✅

### 🔍 Why Input Validation Matters

Imagine someone sends:

* A mobile number with only 9 digits 📱
* An email without `@domain.com` 📧
* A name with just two letters 👤

Sure, the backend *might* handle this and return a "not found" response. But you're still triggering DB queries for invalid input, wasting time and resources. Instead, your API should **immediately reject such invalid requests** and inform the client with clear messages.

### 🛠️ Setting Up Input Validation

First, make sure the validation library is in your `pom.xml`:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

This brings in the required annotations from the `jakarta.validation.constraints` package.

### 🧾 Validating DTO Fields

In our project, we validated both `CustomerDto` and `AccountsDto`.

#### For `CustomerDto`:

```java
@NotEmpty(message = "Name cannot be null or empty")
@Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
private String name;

@NotEmpty(message = "Email address cannot be null or empty")
@Email(message = "Email address should be a valid value")
private String email;

@Pattern(regexp = "[0-9]{10}", message = "Mobile number must be 10 digits")
private String mobileNumber;
```

#### For `AccountsDto`:

```java
@NotEmpty(message = "Account number cannot be null or empty")
@Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits")
private String accountNumber;

@NotEmpty(message = "Account type cannot be null or empty")
private String accountType;

@NotEmpty(message = "Branch address cannot be null or empty")
private String branchAddress;
```

These annotations ensure that your fields are validated **before** any business logic is executed.

### 🧩 Enabling Validation in the Controller

On your `AccountsController`, add:

```java
@Validated
@RestController
@AllArgsConstructor
public class AccountsController {
    // ...
}
```

For each request body, use `@Valid`:

```java
@PostMapping("/create")
public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    // logic
}
```

For query parameters like mobile numbers:

```java
@GetMapping("/fetch")
public ResponseEntity<?> fetchAccount(@RequestParam
    @Pattern(regexp = "[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
    // logic
}
```

### 🔄 Handling Validation Errors Globally

Spring Boot does not automatically know how to package validation errors in the response. So, extend `ResponseEntityExceptionHandler` in your `GlobalExceptionHandler` and override the method below:

```java
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                              HttpHeaders headers,
                                                              HttpStatusCode status,
                                                              WebRequest request) {
    Map<String, String> validationErrors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        validationErrors.put(fieldName, message);
    });
    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
}
```

This will send all field-specific validation errors in a clear, structured JSON format. 🧾

### 🧪 Testing the Validations

Now let's test the behavior:

1. **Create API with bad data**:

   * Mobile: 9 digits
   * Email: missing `.com`
   * Name: 1 character
     You’ll receive:

   ```json
   {
     "mobileNumber": "Mobile number must be 10 digits",
     "email": "Email address should be a valid value",
     "name": "Name must be between 5 and 30 characters"
   }
   ```

2. **Update API with invalid email**:

   * You’ll get: `"Email address should be a valid value"`

3. **Fetch or delete with short mobile number**:

   * Response: `"Mobile number must be 10 digits"`

All your endpoints now respond gracefully with meaningful validation messages and avoid unnecessary processing. 🧹

### ✅ Wrap-up

Implementing input validation ensures your microservices are resilient, efficient, and user-friendly. Spring Boot makes this seamless with annotations like `@NotEmpty`, `@Email`, `@Pattern`, and more. 🎯 Combined with global exception handling, you're building REST APIs that are not just functional—but **professional-grade**.

Happy coding! 🚀

---

## 18. ## 🕵️‍♂️ Automating Metadata Auditing with Spring Data JPA in AccountsMicroservices

In our AccountsMicroservices, every table maintains four important metadata columns to track **when** a record was created or updated and **by whom**. Currently, we are manually setting `CREATED_AT` and `CREATED_BY`, but `UPDATED_AT` and `UPDATED_BY` updates are missing — causing bugs. 🐞 To fix this and automate auditing, Spring Data JPA provides a powerful feature to handle these fields automatically, saving us from writing manual SQL or update logic. 🚀

Since Spring Data JPA manages all SQL interactions for us — insert, update, delete, select — we can delegate metadata handling to it as well. Here’s how we enable this seamless auditing:

### Step 1: Annotate Metadata Fields in BaseEntity

In the common `BaseEntity` class where these columns live, add these Spring Data JPA annotations:

```java
@CreatedDate
private LocalDateTime createdAt;

@CreatedBy
private String createdBy;

@LastModifiedDate
private LocalDateTime updatedAt;

@LastModifiedBy
private String updatedBy;
```

These annotations tell Spring Data JPA to automatically populate these fields on insert (`CreatedDate`, `CreatedBy`) and update (`LastModifiedDate`, `LastModifiedBy`).

### Step 2: Provide Auditor Information

Spring can get the timestamp automatically, but it doesn’t know **who** is making the change. For that, we implement an `AuditorAware<String>` class to supply the current auditor’s identity.

Create a new package `audit` and add:

```java
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // For now, hardcode the service name
        return Optional.of("ACCOUNTS_MS");
    }
}
```

This returns a fixed string `"ACCOUNTS_MS"` as the creator/updater. Later, when we integrate Spring Security, we can fetch the actual logged-in user dynamically.

### Step 3: Enable Auditing in BaseEntity

Add the `@EntityListeners` annotation to your `BaseEntity` class to hook into Spring Data's auditing:

```java
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    // fields with @CreatedDate, @CreatedBy, etc.
}
```

This ensures Spring listens for entity lifecycle events to update audit fields accordingly.

### Step 4: Activate JPA Auditing in Main Application Class

Finally, enable auditing support by adding `@EnableJpaAuditing` to your Spring Boot application class and specify the auditor bean name:

```java
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsMicroserviceApplication.class, args);
    }
}
```

### Step 5: Remove Manual Metadata Updates

Since Spring Data JPA now handles auditing automatically, you can safely remove any manual setting of `createdAt`, `createdBy`, `updatedAt`, or `updatedBy` in your service layer:

```java
// Before: manual setting (remove these lines)
account.setCreatedAt(LocalDateTime.now());
account.setCreatedBy("ACCOUNTS_MS");
```

### Step 6: Verify in Postman & Database 🔍

* Create a new account and check the DB: `createdAt` and `createdBy` should be set automatically.
* Update an account and verify `updatedAt` and `updatedBy` are populated.
* No manual intervention needed! 🎉

**Summary:** With just a few annotations and a simple auditor provider, Spring Data JPA automates metadata auditing — making your microservices cleaner, consistent, and less error-prone. This is a best practice you should definitely adopt for all your REST APIs and microservices! 💯

---

## 19. ## 📚 Documenting REST APIs in AccountsMicroservice with Spring Doc OpenAPI 🚀

Next up, let's implement a crucial best practice in our **AccountsMicroservice** — **documenting all our REST APIs**. You might wonder, *“Why document my own APIs? I built them; I know everything!”* 🤔 While that’s true for you, consider when external teams like UI developers, mobile app teams, or even QA need to consume or test your APIs. They’ll have tons of questions: *What’s the request format? What response do I get? What validations are enforced?* Without proper docs, you’d be stuck answering these repeatedly — a tedious and error-prone process.

To solve this elegantly, we follow an industry standard called **OpenAPI Specification** 🌐. OpenAPI is an open-source standard that defines how to describe HTTP APIs (like REST) so others can easily understand, consume, and even auto-generate client/server code or test cases.

### Why OpenAPI? Benefits 🎯

* Clear, standardized API docs for internal/external teams
* Automated client code generation
* Easier API testing & validation
* Reduced repetitive communication overhead

### Spring Doc OpenAPI: Making Documentation Easy 🧙‍♂️

You don’t need to write docs manually. Thanks to **Spring Doc OpenAPI**, documenting your Spring Boot REST APIs is nearly effortless. Just add one Maven dependency and it auto-generates Swagger UI for your APIs!

**Add this Maven dependency** in your `pom.xml` (for Spring Boot 3.x+):

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.1.0</version>
</dependency>
```

> ⚠️ For Spring Boot 2.x or lower, use `springdoc-openapi-ui` version 1.7.0.

After adding the dependency, reload Maven and rebuild your app. Then start your service (usually on port 8080).

### Explore the Auto-generated Swagger UI 📖

Open your browser and visit:
`http://localhost:8080/swagger-ui/index.html`

You’ll see a beautiful, interactive UI showing all your REST endpoints — paths, HTTP methods, request and response schemas, and validation rules — all generated from your code annotations automatically!

For example, the **PUT** API will show:

* Request body format (e.g. JSON with `AccountsDto`)
* Field validations like `@NotNull`, `@Size(min, max)`, and patterns from your DTO classes
* Response codes and message structure (e.g. 200 OK with status info)

You can even **try out** the APIs directly from this UI by sending sample requests!

### What’s Documented? 🔍

* Request formats & validations
* Response formats
* HTTP methods and URLs
* API status codes and descriptions
* DTO schema with field constraints

### Why is this Important? 🤩

* Saves tons of time explaining your APIs to others
* Makes onboarding new developers faster
* Enables automated testing and client SDK generation
* Helps maintain clean and professional API standards

### Going Beyond Basics: Pro Tips 💡

The default docs show technical names like `AccountsDto` and lack example values. To make your docs truly professional and client-friendly, Spring Doc OpenAPI offers annotations like `@Schema`, `@ExampleObject`, and `@Parameter` to add descriptions, examples, and hide internal details.

Example to add description and example to a DTO field:

```java
@Schema(description = "User's email address", example = "user@example.com")
private String email;
```

We’ll explore these advanced annotations in the next lecture to polish your API documentation to production-ready quality! 🎯

**Summary:** Simply by adding Spring Doc OpenAPI to your project, you transform your REST APIs into well-documented, self-explanatory endpoints that anyone can understand and consume with ease. This is a must-have standard for any professional microservice or API you build.

Thank you for following along — stay tuned for the next session where we enhance this documentation further! 👋

---

## 20. ## 🚀 Enhancing REST API Documentation with OpenAPI Annotations in Spring Boot

To level up our REST API documentation, let's focus on enriching the **top-level metadata** section that describes what our APIs are all about. Currently, this section lacks crucial info like the purpose, summary, contact, and licensing details—which are super helpful for consumers of your API. Imagine if someone new wants to understand your API, or needs to reach out for help—having this info upfront in your docs makes life way easier! ✨

### How to Add API Metadata Using OpenAPI Annotations

Navigate to your Spring Boot main class — in our case, `AccountsApplication`. Here, we add the `@OpenAPIDefinition` annotation which allows us to provide rich metadata via the nested `@Info` annotation:

```java
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "Accounts Microservice REST API Documentation",
        description = "EasyBank Accounts Microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "John Doe",
            email = "support@knowprogram.com",
            url = "https://knowprogram.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    )
)
@SpringBootApplication
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
```

### What We Just Added 🎉

* **Title** and **Description** — to clearly state the API's purpose.
* **Versioning** — helps track changes across API releases (e.g., `v1`, `v2`).
* **Contact Info** — so developers know whom to reach out to for questions or issues.
* **License Info** — communicates usage rights (e.g., Apache 2.0).

### Adding External Documentation

You can also add external docs links to guide users to more in-depth info about your business or APIs:

```java
@OpenAPIDefinition(
    info = @Info(...),
    externalDocs = @ExternalDocumentation(
        description = "Learn more about EasyBank Accounts API",
        url = "https://knowprogram.com/docs/accounts-api"
    )
)
```

### Why This Matters

When you refresh your Swagger UI (usually at `http://localhost:8080/swagger-ui/index.html`), you'll see this detailed info displayed at the top, making your API docs **professional, clear, and user-friendly**. This avoids confusion and reduces repetitive questions about your APIs.

### Pro Tip: Package Structure & Component Scanning

Make sure your main Spring Boot class (`AccountsApplication`) resides in a **parent package** (e.g., `com.knowprogram.accounts`) with all controllers, services, and repositories inside sub-packages. This way, Spring Boot auto-detects your beans. If your packages are scattered elsewhere, you must explicitly specify component scanning:

```java
@ComponentScan(basePackages = {"com.knowprogram.accounts.controller", "com.knowprogram.accounts.service"})
@EnableJpaRepositories("com.knowprogram.accounts.repository")
@EntityScan("com.knowprogram.accounts.entity")
@SpringBootApplication
public class AccountsApplication {
    ...
}
```

Keeping a clean package structure is best practice and helps avoid config headaches! 🧹

With these enhancements, your API docs become a powerful communication tool for your team and external consumers alike. Next up, we'll explore how to add **example data** and **customize your API docs** even further for a truly polished, production-ready API experience. 🚀

Thank you for following along — see you in the next lecture! 👋

---

## 21. ## ✨ Enhancing REST API Documentation with @Tag, @Operation & @ApiResponse Annotations

In this lecture, let's boost our REST API docs to be more **clear and professional**! Currently, the Swagger UI just shows technical controller names like `AccountsController` with no description, which isn’t very user-friendly. When you click the controller, it toggles all APIs inside, but without meaningful context, clients might get confused. So, let's fix that by adding helpful descriptions and summaries! 🛠️

### Adding Controller-Level Info with `@Tag`

Head over to your `AccountsController` class and add the `@Tag` annotation from **springdoc-openapi**. This annotation lets you provide a **name** and **description** for the entire controller, explaining what all APIs here do.

```java
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Accounts Controller",
    description = "CRUD REST APIs for accounts in EasyBank"
)
@RestController
@RequestMapping("/accounts")
public class AccountsController {
    // your APIs here
}
```

This changes the UI from a bland technical name to a **friendly title with a clear summary**. After saving and rebuilding, refreshing Swagger UI will display this info neatly at the controller level. 🎉

### Documenting Each API with `@Operation`

Next, let’s describe individual REST endpoints inside the controller using `@Operation`. This annotation allows you to add a **summary** and **detailed description** per API method.

For example, for a POST API creating accounts:

```java
import io.swagger.v3.oas.annotations.Operation;

@Operation(
    summary = "Create Account REST API",
    description = "REST API to create new Customer and Account inside EasyBank"
)
@PostMapping("/create")
public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
    // implementation
}
```

Now, your Swagger UI will show meaningful titles and descriptions instead of just the HTTP method and path.

### Specifying Response Details with `@ApiResponse`

By default, Swagger shows a generic `200 OK` response, but your API might return specific status codes like `201 Created`. To accurately document this, use the `@ApiResponse` annotation:

```java
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Operation(summary = "Create Account REST API", description = "REST API to create new Customer and Account")
@ApiResponse(responseCode = "201", description = "HttpStatus.CREATED - Account successfully created")
@PostMapping("/create")
public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
    // implementation
}
```

This updates the Swagger response section to reflect `201 Created` with your custom description — much clearer for API consumers!

### Handling Multiple Responses with `@ApiResponses`

For APIs that can return multiple status codes, like update or delete operations, wrap multiple `@ApiResponse` annotations inside `@ApiResponses`:

```java
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Operation(summary = "Update Account Details REST API", description = "Update account info in EasyBank")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Account updated successfully"),
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@PutMapping("/update")
public ResponseEntity<UpdateAccountResponse> updateAccount(@RequestBody UpdateAccountRequest request) {
    // implementation
}
```

Now Swagger clearly shows all possible response codes and what they mean — helping developers handle API responses properly. ✅

### The Result: Clear, Professional, and Developer-Friendly Docs!

Refresh Swagger UI and see your enhanced documentation — each controller with a meaningful name and description, each API with detailed summaries, and accurate response codes! This clarity saves time and frustration for everyone consuming your APIs.

---

### Next Steps: Adding Example Data to Schema Objects

Right now, Swagger shows technical names and generic types like `string` for inputs. Adding **example values** makes the API contract crystal clear to clients. For instance:

```java
@Schema(description = "Customer name", example = "John Doe")
private String customerName;
```

We'll cover enriching schema examples in the next lecture to make your docs even more intuitive! 👏

Thanks for tuning in — see you next time! 👋

---

## 22. ## 📝 Enhancing Schema Object Documentation with @Schema Annotation for Clearer APIs

In this lecture, let's level up our API documentation by enriching the **schema objects** (DTOs) using the powerful `@Schema` annotation from **OpenAPI**! Just like we improved the controller and method docs earlier, now we’ll add meaningful names, descriptions, and examples to our DTO classes and their fields — making the API docs **business-friendly and super clear** for consumers. 🚀

### Step 1: Rename and Describe DTO Classes with `@Schema`

Start by opening your DTO classes like `CustomerDto`. Instead of showing boring technical class names, use `@Schema` to provide a **friendly name** and a **description**:

```java
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Customer", description = "Schema holding customer and account information")
public class CustomerDto {
    // fields...
}
```

This tells Swagger UI to display "Customer" instead of `CustomerDto`, along with a helpful summary of the schema’s purpose.

### Step 2: Add Field-Level Details and Examples

Next, annotate each field with `@Schema` to describe its role and provide example values for better clarity:

```java
@Schema(description = "Name of the customer", example = "Eazy Byte")
private String name;

@Schema(description = "Email address of the customer", example = "customer@example.com")
private String email;

@Schema(description = "Mobile number of the customer", example = "9876543210")
private String mobileNumber;
```

These examples show clients what data format and values are expected, making the API contract crystal clear.

### Step 3: Document Nested DTOs Similarly

For nested objects like `AccountsDto`, repeat the process:

```java
@Schema(name = "Accounts", description = "Account information of the customer")
public class AccountsDto {

    @Schema(description = "Account Number of Easy Bank Account", example = "1234567890")
    private String accountNumber;

    @Schema(description = "Account Type of Easy Bank Account", example = "savings")
    private String accountType;

    @Schema(description = "Branch address of the account")
    private String branchAddress;
}
```

You can choose to provide examples for each field or just descriptions depending on what makes sense.

### Step 4: Enhance Response DTOs

Similarly, update response classes like `ResponseDto` and `ErrorResponseDto`:

```java
@Schema(name = "Response", description = "Schema to hold successful response information")
public class ResponseDto {

    @Schema(description = "Status code in the response", example = "200")
    private int statusCode;

    @Schema(description = "Status message in the response", example = "Request processed successfully")
    private String statusMessage;
}

@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDto {

    @Schema(description = "API path where error occurred")
    private String path;

    @Schema(description = "Error code representing the error")
    private String errorCode;

    @Schema(description = "Detailed error message")
    private String errorMessage;
}
```

### Step 5: Reflecting Error Responses in API Docs

Since error DTOs are returned from global exception handlers and not directly visible to SpringDoc scanning, you need to explicitly specify error response schemas in your API methods using `@ApiResponse`, `@Content`, and `@Schema`:

```java
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Operation(summary = "Update Account Details REST API", description = "Update account info in EasyBank")
@ApiResponse(
    responseCode = "500",
    description = "Internal Server Error",
    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
)
@PutMapping("/update")
public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountRequest request) {
    // implementation
}
```

This way, Swagger UI shows the detailed schema for error responses under the 500 status code — improving transparency for clients. ⚠️

### Final Result: Professional, Intuitive API Documentation

After rebuilding and refreshing Swagger UI, you'll see:

* Schema names replaced with **business-friendly terms** like *Customer* and *Accounts*
* Each field documented with **clear descriptions and realistic example values**
* Error response schemas properly linked to relevant API operations
* Overall, the API documentation looks polished and is easy to understand for developers, testers, and clients alike! 🎉

With these enhancements, your microservices follow best practices in **REST API documentation** using OpenAPI annotations. This creates a **robust developer experience** and minimizes confusion.

---

## 23. ## 🔧 Handling Distinct Error Codes for Clearer API Responses

In the previous lecture, we saw that the same **HTTP status code 500** (Internal Server Error) was used for all `RuntimeException`s inside the `GlobalExceptionHandler` as well as for failures in both update and delete operations. While this works, it can cause confusion for API clients since different failure scenarios share the same error code. 🤔

To fix this and make error handling clearer, I decided to introduce a new status code **417 (Expectation Failed)** specifically for update and delete operation failures, keeping **500** only for unexpected runtime exceptions. Here’s how I improved it behind the scenes:

### Updating Status Codes in Controller Methods

Instead of sending `500` when update or delete operations fail (indicated by a boolean flag), I now return `417` to better express the nature of these failures:

```java
if (!updateSuccessful) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(417, "Update operation failed. Please try again or contact dev team."));
}

if (!deleteSuccessful) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(417, "Delete operation failed. Please try again or contact dev team."));
}
```

I also created constants for the new status code and messages to keep things clean and reusable.

### Keeping 500 for Runtime Exceptions Only

The `GlobalExceptionHandler` still returns **500** for unexpected exceptions like `RuntimeException`, so clients can distinguish between general server errors and business logic failures like update/delete issues.

### Documenting Multiple API Responses with Swagger

All APIs have an `@ApiResponse` annotated with the `ErrorResponseDto` schema for 500 errors, since any endpoint might throw a runtime exception:

```java
@ApiResponse(responseCode = "500", description = "Internal Server Error",
             content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
```

For **update** and **delete** operations, I added an extra `@ApiResponse` for status code 417 with a description like "Exception failed":

```java
@ApiResponse(responseCode = "417", description = "Exception failed")
```

Thus, the update and delete endpoints have **three API responses** documented:

* 204 (No Content) for success
* 417 for expected business exceptions
* 500 for unexpected server errors

### Fixing Confusing Example Values in Documentation

Originally, the example values for `statusCode` and `statusMessage` in `ResponseDto` were hardcoded to `200` and "Request processed successfully." This caused confusion because even for 417 errors, Swagger UI showed the success example, misleading clients.

To fix this, I **removed example values** from `ResponseDto` fields:

```java
@Schema(description = "Status code in the response")
private int statusCode;

@Schema(description = "Status message in the response")
private String statusMessage;
```

After rebuilding and refreshing Swagger UI, the status codes now correctly reflect the actual response, avoiding confusion.

### Final Outcome 🎉

* Clear separation between **500 Internal Server Error** (unexpected failures) and **417 Expectation Failed** (business logic failures)
* Multiple API responses properly documented for each operation
* Accurate and non-conflicting examples in Swagger UI
* `ErrorResponseDto` visible in schemas and linked to error responses

This makes the API much easier to understand and consume by clients! Take your time to digest this approach, and feel free to apply it in your own projects for better error handling. I'll catch you in the next lecture. 👋

---

## 24. ## 🚀 Essential Spring Boot Annotations & Classes for Building REST APIs

Now that we've completed the **Accounts Microservice** with 4 different REST APIs, let’s quickly recap the key **Spring Boot annotations and classes** we used to build these endpoints. Having these handy will be super useful—whether you’re preparing for interviews or developing your own REST services! 📚✨

### 1. `@RestController`

This is the backbone of any Spring REST API. Placing `@RestController` on top of your class tells Spring Boot to expose all your Java methods as REST endpoints automatically. It’s essentially a combination of `@Controller` + `@ResponseBody`.

```java
@RestController
public class AccountController {
    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() { ... }
}
```

> **Note:** If you want to mix REST APIs and traditional MVC views in the same controller, use `@Controller` on the class and annotate each REST method with `@ResponseBody` instead. This gives you more fine-grained control.

### 2. `@ResponseBody`

When using `@Controller`, you need to explicitly add `@ResponseBody` on methods returning JSON responses. This tells Spring you want the response in JSON format instead of a view (like HTML).

```java
@Controller
public class MixedController {
    @ResponseBody
    @GetMapping("/api/data")
    public Data getData() { ... }
}
```

In our microservice, since we used `@RestController`, we didn’t need to use `@ResponseBody` separately.

### 3. `ResponseEntity<T>`

Not an annotation but a powerful Spring class used for sending responses with **custom HTTP status, headers, and body**. Use generics to specify the type of response body.

```java
public ResponseEntity<AccountDto> getAccountById(@PathVariable String id) {
    AccountDto account = service.findById(id);
    return ResponseEntity.ok(account);
}
```

This gives you full control over your HTTP response.

### 4. `@ControllerAdvice` and `@ExceptionHandler`

For centralized global exception handling, annotate a class with `@ControllerAdvice`. Then define methods annotated with `@ExceptionHandler` to catch and process exceptions thrown in your controllers.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        // handle exception globally
    }
}
```

> You can also use `@RestControllerAdvice`, which combines `@ControllerAdvice` and `@ResponseBody`. Use it if you want your exception handlers to always return JSON responses.

### 5. `RequestEntity<T>`

Like `ResponseEntity` but for **incoming requests**. Use this when you want to access both **request headers and body** together in your controller method.

```java
@PostMapping("/process")
public ResponseEntity<String> processRequest(RequestEntity<MyRequest> request) {
    HttpHeaders headers = request.getHeaders();
    MyRequest body = request.getBody();
    // process logic
}
```

In our microservice, we didn’t use this since we only needed the request body.

### 6. `@RequestHeader` and `@RequestBody`

Use `@RequestHeader` to extract specific HTTP headers as method parameters, and `@RequestBody` to get the request payload mapped to a Java object.

```java
@PostMapping("/create")
public ResponseEntity<String> createAccount(
    @RequestHeader("Authorization") String authToken,
    @RequestBody AccountDto accountDto) {
    // use authToken and accountDto
}
```

### Quick Summary for Interviews 🎯

When asked how you build REST APIs with Spring Boot, walk through this flow:

* Create a class annotated with `@RestController` (or `@Controller` + `@ResponseBody`)
* Define Java methods with HTTP method annotations like `@GetMapping`, `@PostMapping`
* Use `@RequestBody` and `@RequestHeader` to get data from requests
* Use `ResponseEntity<T>` to craft rich HTTP responses with status and headers
* Implement global exception handling with `@ControllerAdvice` + `@ExceptionHandler`

Explaining it this way as a story will impress interviewers and also clarify your own understanding! Keep these annotations and classes at your fingertips—they’re essential tools in every Spring developer’s toolkit. 🛠️💡

Hope this recap helps! Catch you in the next lecture. 👋

---
