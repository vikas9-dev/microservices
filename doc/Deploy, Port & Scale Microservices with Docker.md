# Handle Deployment, Portability & Scalability of Microservices using Docker

## 1. Microservices Challenges & Containerization

- **Overcoming Deployment, Portability & Scalability Issues**:-
  Building microservices introduces challenges in **deployment**, **portability**, and **scalability**. Unlike monolithic applications, microservices require efficient deployment strategies, seamless movement across environments, and dynamic scaling.
- **Solution: Containerization with Docker**:- Containers package applications with all dependencies, ensuring consistency across environments. Docker enables **isolated, portable, and scalable** microservices, optimizing resource utilization without manual intervention.
- **Shipping Containers Analogy**:- Just like shipping containers streamline logistics, **Docker containers** provide isolated environments for microservices, allowing efficient deployment and scaling.

---

## 2. Virtual Machines vs. Containers: The Shift to Docker

### **The Evolution of Deployment**

Before cloud computing, organizations relied on physical servers, installing operating systems and deploying applications manually. Later, cloud providers introduced **Virtual Machines (VMs)**, allowing multiple virtual environments on a single physical server via **hypervisors**.

### **Challenges with Virtual Machines**

1. **Heavy Resource Consumption** – Each VM has its own **Guest OS**, consuming RAM and storage.
2. **Slow Scaling** – Spinning up a new VM takes time, delaying service availability.
3. **Complex Dependencies** – Deploying multiple microservices with different requirements (e.g., Java 8 vs. Java 17) in the same VM leads to conflicts.

### **Containers: A Lightweight Alternative**

Unlike VMs, **containers** share the Host OS and utilize a **Container Engine (like Docker)**, making them:

- **Faster** – Start in seconds, unlike VMs which take minutes.
- **Lightweight** – No need for a separate OS per microservice.
- **Portable** – Can be deployed across environments effortlessly.
- **Isolated** – Each container has its own dependencies and virtual network.

### **Why Containers Over VMs?**

With **Docker**, microservices can be packaged with all required dependencies, ensuring consistency across environments. Containers solve **deployment, portability, and scalability** issues, making them the preferred choice over VMs for modern microservice architectures.

---

## 3. Understanding Containers and Docker

### **What is a Container?**

A **container** is a lightweight, isolated environment that includes an application and all its dependencies, allowing it to run consistently across different computing environments. Unlike virtual machines, containers share the **host operating system kernel**, making them faster and more efficient.

### **What is Software Containerization?**

**Containerization** is an **OS-level virtualization method** that allows multiple containers to run on the same machine while remaining isolated. This eliminates compatibility issues and ensures portability across different environments.

### **What is Docker?**

Docker is an **open-source platform** that enables developers to package applications into **Docker images**, which can then be run as **Docker containers**. It simplifies **deployment, scaling, and management** of applications.

### **How Containers Differ from Virtual Machines?**

- **Virtual Machines (VMs)** require a full Guest OS for each instance.
- **Containers** share the Host OS, making them **lightweight, faster, and more scalable**.

### **Key Linux Concepts in Containerization**

In containerization, Linux features such as namespaces and cgroups play a crucial role in providing isolation and resource management.

- **Namespaces**: Linux namespaces allow for the creation of isolated environments within the operating system. Each container has its own set of namespaces, including process, network, file system/mount/storage, IPC (inter-process communication) and user namespace. These namespaces ensure that processes within a container only aware of and can interact with resources within their specific namespace, providing a level of isolation.
- **Control Groups (cgroups)**: Manage resource allocation (CPU, memory) to prevent one container from monopolizing system resources. The cgroups provide resource management and allocation capabilities for containers. They allow administrators to control and limit the resources (such as CPU, memory, disk I/O, and network bandwidth) that containers can consume. By using cgroups, container runtimes can enforce resource restrictions and prevent one container from monopolizing system resources, ensuring fair allocation among containers.

### **Running Docker on Different OS**

On **Linux**, Docker runs natively. On **Windows and macOS**, Docker installs a lightweight **Linux virtual machine** to ensure compatibility. When we opt for Docker Desktop for Mac or Windows, only the Docker client is installed on the host MAC or Windows machine. Behind the scenes, a lightweight virtual machine is configured with Linux, and the Docker server component is installed within that virtual machine.

when we utilize the Docker CLI to execute commands, we are actually interacting with a Docker server running on a separate machine, which in this case is the Linux-based virtual machine.

---

## 4. Understanding Docker Architecture and Its Key Components

### **1️⃣ Docker Client**

The **Docker Client** is the interface used to communicate with the **Docker Server**. There are two ways to issue commands:

- **Docker CLI**: The most commonly used approach for executing Docker commands in the terminal.
- **Docker Remote API**: Allows applications to interact with Docker using API calls.

### **2️⃣ Docker Server (Docker Host)**

The **Docker Server** (or **Docker Host**) runs the **Docker Daemon**, which processes commands from the client and manages:

- **Docker Images** – Packaged applications containing all dependencies.
- **Docker Containers** – Running instances of Docker Images, representing active microservices.

### **3️⃣ Docker Registry**

A **Docker Registry** stores and distributes Docker Images. Common registries include:

- **Docker Hub** (Public/Private Repositories)
- **Cloud-based Registries**: AWS Elastic Container Registry (ECR), Google Container Registry (GCR), Azure Container Registry (ACR), and GitHub Container Registry.

### **How Docker Works?**

1. The **Docker Client** sends an instruction to run a container.
2. The **Docker Server** checks if the required image exists locally; if not, it fetches it from a **Docker Registry**.
3. A **Docker Container** is created from the image and starts running the application.
4. The application is now accessible via its assigned port.

### **Why Use Docker?**

- Simplifies application deployment.
- Provides consistency across different environments.
- Enables efficient resource utilization.
- Supports fast scaling and portability.

---

## 5. Generating Docker Images for Microservices

Are you ready to generate a Docker image from our microservice? By converting our microservices into Docker images, we make them incredibly lightweight, enhancing their deployment portability and scalability.

To generate a Docker image from a web application or a Spring Boot application, there are three commonly used approaches in the industry. In this section, we will explore these three approaches, and by the end, we will select one to use for the rest of this course.

### 1️⃣ Dockerfile Approach

The first and most traditional approach is using a **Dockerfile**. This method requires writing a set of instructions inside a `Dockerfile`, which guides the Docker server in creating a Docker image.

- This approach provides full control over the image-building process.
- It requires learning Docker syntax and best practices.
- This is the approach we will use to generate a Docker image for the **Accounts Microservice**.

### 2️⃣ Buildpacks Approach

The second approach utilizes **Buildpacks**. With this method, you don't need to write a `Dockerfile` or provide manual instructions to the Docker server. Instead, you can generate a Docker image with a single Maven command, leveraging Buildpacks behind the scenes.

- Buildpacks is a project initiated by **Heroku** and **Pivotal**, incorporating best practices learned over the years.
- It simplifies the containerization process by eliminating the need for low-level Docker commands.
- This is the approach we will use to generate a Docker image for the **Loans Microservice**.

### 3️⃣ Google Jib Approach

The third approach is **Google Jib**, an open-source Java tool developed by Google. Jib provides an efficient way to create Docker images using a Maven plugin, without requiring a `Dockerfile`.

- Jib streamlines the image creation process, making it faster and easier.
- It eliminates the need for writing low-level Docker configurations.
- We will use this approach to generate a Docker image for the **Cards Microservice**.

---

## 6. Generate Docker Image of Accounts microservice with Dockerfile

### Generating a JAR File for the Accounts Microservice

There are three different Maven projects in our system: **Accounts**, **Cards**, and **Loans**. As the next step, we will focus on the **Accounts Microservice** and generate a Docker image using **Approach 1 (Dockerfile)**.

#### Configuring the `pom.xml` for Packaging

To ensure that our microservice is packaged correctly, we need to update the `pom.xml` file:

- Set the **packaging type** to `jar`.
- This tells Maven to package the Spring Boot application as a JAR file instead of a WAR file.

```
<groupId>com.knowprogram</groupId>
<artifactId>accounts</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>accounts</name>
<description>Microservice for Accounts</description>

<!-- Include this -->
<packaging>jar</packaging>
```

Since we are generating a Docker image, using JAR format is the best practice because it simplifies deployment and execution.

#### Running Maven Commands to Build the Project

Once the configuration is updated, we will follow these steps:

1. **Open the Terminal in the Accounts Microservice Folder**

   - Navigate to the `accounts` microservice directory where `pom.xml` is present.
   - Open the terminal in this directory.

2. **Verify Maven Installation**  
   Before proceeding, confirm that Maven is installed by running:

   ```sh
   mvn -version
   ```

   This should display the installed Maven version, ensuring it is set up correctly.

3. **Build the Project Using Maven**  
    Run the following Maven command to compile and package the Spring Boot application:

   ```sh
   mvn clean install
   ```

   - This command will compile the code, run unit tests, and generate a JAR file inside the `target/` folder.
   - Ensure that your `pom.xml` has the **Spring Boot Maven Plugin** configured, as it is required for packaging.

   ```
   <build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <!-- (Optional) Set your Java version -->
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
   </build>
   ```

4. **Verify the Generated JAR File**  
   After a successful build, navigate to the `target/` folder and check for a JAR file:
   ```
   accounts-0.0.1-SNAPSHOT.jar
   ```
   - This JAR file contains all dependencies and the embedded Tomcat server, except for the Java runtime.
   - The name of the JAR is derived from the `artifactId` and `version` specified in `pom.xml`.

#### Running the Application with Maven

To start the microservice using Maven, execute:

```sh
mvn spring-boot:run
```

- This command looks for the JAR file and starts the Spring Boot application.
- The service should start on **port 8080** by default.
- You can test it using **Postman** or by invoking the APIs from a browser.

#### Running the Application with Java

Instead of using Maven, you can start the application with a **Java command**, which is useful for Dockerization:

```sh
java -jar target/accounts-0.0.1-SNAPSHOT.jar
```

- This eliminates the need for Maven inside the Docker container.
- The microservice should start successfully and be accessible via APIs.

Now that we have a successfully built JAR file, we are ready to write a **Dockerfile** to containerize our microservice. Now, we will create a **Dockerfile** and use it to generate a Docker image for the **Accounts Microservice**.

---

### Writing a Dockerfile for the Accounts Microservice

Before writing a **Dockerfile**, ensure that your **Accounts Microservice** is stopped by pressing `Ctrl + C` in the terminal. Also, make sure that **Docker is running** on your system. You can verify this by checking the Docker icon in your system tray (Windows) or menu bar (Mac).

#### **Creating the Dockerfile**

1. **Navigate to the `accounts` microservice directory.**
2. **Create a new file named `Dockerfile`** (without any extensions like `.txt` or `.xml`).
3. **Open the file and add the following instructions:**

#### **Dockerfile for Accounts Microservice**

```dockerfile
# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim

# Copy the application JAR to the Docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "accounts.jar"]
```

### **Understanding the Dockerfile Instructions**

- **`FROM openjdk:17-jdk-slim`** → Uses a lightweight Java 17 image as the base.
- **`COPY target/accounts-0.0.1-SNAPSHOT.jar accounts.jar`** → Copies the built JAR file into the container.
- **`ENTRYPOINT ["java", "-jar", "accounts.jar"]`** → Specifies the command to start the Spring Boot application inside the container.

---

### Building a Docker Image for the Accounts Microservice

Now that our **Dockerfile** is ready, the next step is to use it to generate a **Docker image** for the **Accounts Microservice**.

#### **Verifying Docker Installation**

Before running the build command, ensure that Docker is installed and running by executing:

```sh
docker version
```

This command should display both the **client** and **server** versions of Docker, confirming that it is properly set up.

#### **Building the Docker Image**

Run the following command from the **Accounts Microservice** directory (where the `Dockerfile` is located):

```sh
docker build -t <your-dockerhub-username>/accounts:S4 .
```

- `docker build` → Initiates the image build process.
- `-t <your-dockerhub-username>/accounts:S4` → Assigns a **name** (`accounts`) and a **tag** (`S4`).
- `.` → Refers to the current directory, where the `Dockerfile` is located.

#### **Understanding the Build Process**

- Docker first downloads the **OpenJDK 17 base image** from Docker Hub.
- It then **copies the JAR file** from the `target/` folder into the image.
- Finally, it sets the **entry point** to run the JAR file when a container is created.

#### **Listing Docker Images**

Once the build is complete, check the available images by running:

```sh
docker images
```

This will list all images, including:

- The **Accounts Microservice image** with the tag `S4`.
- The **image size**, which depends on the dependencies and base image.

```sh
$ docker images
REPOSITORY           TAG       IMAGE ID       CREATED         SIZE
vikas9dev/accounts   s4        18cdd03adfc0   7 minutes ago   760MB
```

#### **Inspecting the Docker Image**

For more details about the generated image, use:

```sh
docker inspect <image-id>
```

This provides information such as:

- **Base image** (`OpenJDK 17`)
- **Entry point** (`java -jar accounts.jar`)
- **Operating system** (`Linux`)

---

### Running a Docker Container for the Accounts Microservice

Now that we have successfully built a **Docker image** for our **Accounts Microservice**, the next step is to run a **Docker container** from this image.

#### **1. Running the Docker Container**

Execute the following command to create and run a container from the Docker image:

```sh
docker run -p 8080:8080 <your-dockerhub-username>/accounts:S4
```

- `docker run` → Creates a new container from the image.
- `-p 8080:8080` → Maps **port 8080 of the container** to **port 8080 on the local machine**.
- `<your-dockerhub-username>/accounts:S4` → Specifies the Docker image to use.

```sh
docker run -p 8080:8080 vikas9dev/accounts:s4
```

Once executed, the **Accounts Microservice** will start inside the container on **port 8080**.

#### **2. Testing the Running Container**

- Use **Postman** or a browser to send a request to:
  ```
  http://localhost:8080
  ```
- If everything is set up correctly, you should receive a **successful response** from the microservice.

#### **3. Running the Container in Detached Mode**

By default, the container runs in **foreground mode**, preventing other commands from being executed in the terminal. To run it in **detached mode**, use:

```sh
docker run -d -p 8080:8080 <your-dockerhub-username>/accounts:S4
```

- `-d` → Runs the container in **detached mode**, allowing the terminal to remain free for other commands.

```sh
$ docker run -d -p 8080:8080 vikas9dev/accounts:s4
60be723defd82f96c95ad58ab743f71a91813acc80f58da2208758856b475e87
```

#### **4. Checking Running Containers**

To list all **active** containers, use:

```sh
docker ps
```

This will display:

- **Container ID**
- **Image name**
- **Port mappings**

```sh
$ docker ps
CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES
60be723defd8 vikas9dev/accounts:s4 "java -jar /accounts…" 24 seconds ago Up 23 seconds 0.0.0.0:8080->8080/tcp kind_knuth
```

To see the stopped containers, use:

```sh
docker ps -a
```

#### **5. Stopping and Restarting Containers**

To **stop** a running container, use:

```sh
docker stop <container-id>
```

To **restart** an existing container, use:

```sh
docker start <container-id>
```

#### **6. Running Multiple Containers**

You can start multiple instances of the microservice by mapping different ports:

```sh
docker run -d -p 8081:8080 <your-dockerhub-username>/accounts:S4
```

This will start a second container on **port 8081**, while the first one runs on **port 8080**.

#### **7. Viewing Logs and Inspecting Containers**

To view logs of a running container:

```sh
docker logs <container-id>
```

To inspect container details:

```sh
docker inspect <container-id>
```

#### **8. To see the docker image files of the running docker container**

```sh
docker exec -it <container-id> sh
```

Example:-

```sh
$ docker exec -it 0013c3848d8e sh
# ls -al
```

#### **9. To Copy Files from a Running Container to Local System**

```sh
docker cp <container-id>:<container-path> <local-path>
```

Example:-

```sh
$ docker cp my-container:/app/logs ./logs
```

#### **10. Delete a Stopped Container**

```sh
docker rm <container-id>
```

To delete multiple stopped containers at once:

```sh
docker rm <container-id1> <container-id2>
```

To delete all stopped containers:

```sh
docker rm $(docker ps -aq)  # Remove all stopped containers
```

Or,

```sh
docker container prune
```

#### **10. Force Delete a Running Container**

```sh
docker rm -f <container-id> # Force delete a running container
```

To delete all running & stopped containers:

```sh
docker rm -f $(docker ps -aq)  # Remove all stopped containers
```

### **Understanding Port Mapping**

By default, Docker containers run in an **isolated network**. To make them accessible, **port mapping** is used:

```
docker run -p <external-port>:<container-port> <image-name>
```

For example,

```sh
docker run -p 8081:8080 <your-dockerhub-username>/accounts:S4
```

- **`8081` (external port)** → The port accessible from the local system.
- **`8080` (container port)** → The port inside the container.

---

### Limitations of the Dockerfile Approach

Now that we have successfully built a **Docker image** using a **Dockerfile**, it's important to understand its **limitations**. While this method works, it comes with certain challenges that make it less ideal for developers.

#### **Challenges of Using a Dockerfile**

1. **Requires Docker Expertise** – Developers must learn Docker commands, image structures, and best practices, which can be complex.
2. **Manual Configuration** – Writing efficient Dockerfiles requires **optimization techniques** like caching, compression, and security hardening.
3. **Scalability Issues** – Each microservice needs a separate Dockerfile, making maintenance difficult for large applications.
4. **Security Concerns** – Ensuring that Docker images are **secure and free from vulnerabilities** requires extra effort.

#### **A Better Alternative: Buildpacks & Google Jib**

To overcome these challenges, **Buildpacks** and **Google Jib** were developed, allowing developers to **automate** Docker image creation **without writing a Dockerfile**. These tools simplify the process, improve efficiency, and follow best practices automatically.

---

## Generating a Docker Image Using Buildpacks

After exploring the **Dockerfile** approach, it's time to look at a more developer-friendly way to build Docker images: **Buildpacks**.

### **What is Buildpacks?**

Buildpacks is a technology developed by **Heroku** and later improved by **Pivotal** and the **Cloud Native Computing Foundation (CNCF)**. It allows developers to **convert application source code into a Docker image** without writing a **Dockerfile**.

With Buildpacks:

- You don't need to define low-level Docker instructions.
- A **single Maven command** can generate a **Docker image** effortlessly.
- The framework automatically **scans dependencies** and **optimizes** the image for **performance, security, and size**.

### **Why Use Buildpacks Instead of a Dockerfile?**

Unlike the Dockerfile approach, which requires **Docker expertise**, Buildpacks takes care of best practices **automatically**:  
✅ **Optimized Image Size** – Compresses and caches layers efficiently.  
✅ **Security Compliance** – Follows security standards during image creation.  
✅ **Multi-Language Support** – Works with Java, Python, Node.js, Go, Ruby, PHP, and more.

### **How to Generate a Docker Image Using Buildpacks**

For demonstration, we will use **Loans Microservice** and follow these steps:

1. **Configure `pom.xml`**
   - Ensure **Spring Boot Maven Plugin** is present.
   - Define the **image name** inside the `<configuration>` section:
     ```xml
     <configuration>
         <image>
             <name>${project.artifactId}</name>
         </image>
     </configuration>
     ```
   - Replace `artifactId` dynamically instead of hardcoding the service name.

Example:-

```
<plugin>
	<groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
	<configuration>
		<image>
			<name>vikas9dev/${project.artifactId}:s4</name>
		</image>
	</configuration>
</plugin>
```

2. **Run the Maven Command**  
   Execute the following command to build a Docker image:

   ```sh
   mvn spring-boot:build-image
   ```

   - This triggers Buildpacks to generate a **Docker image** automatically.
   - The first run may take a few minutes as it downloads necessary **Paketo Buildpacks**.

3. **Verify the Generated Docker Image**  
   Run:

   ```sh
   docker images
   ```

   - You should see the **Loans Microservice image** built with **Buildpacks**.
   - Typically, Buildpacks **optimizes image size**, making it smaller than a manually created Dockerfile image.

4. **Run the Container**  
   Start the container using:
   ```sh
   docker run -d -p 8090:8090 <your-dockerhub-username>/loans:S4
   ```
   - The **Loans Microservice** will now run inside a Docker container at **port 8090**.

Buildpacks provides a **simpler, automated, and production-ready** way to generate Docker images without manually handling Dockerfile complexities.

---
