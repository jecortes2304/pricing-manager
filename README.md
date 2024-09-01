# Pricing Manager

## Project Overview

The **Pricing Manager** project is a demonstration of a simple pricing service application. The service applies business rules to determine the correct price of a product based on various criteria, such as the date, time, brand, and priority of price offers. The application showcases the following business rules:

- **Rule 1:** When multiple prices are applicable for a product at a given time, the price with the highest priority is selected.

This project is designed using the Hexagonal Architecture (Ports and Adapters) pattern combined with Vertical Slicing. This architecture allows the application to be scalable and easily extendable to multiple contexts beyond pricing, making it suitable for larger systems with varied business logic.

## Architecture

The project follows the **Hexagonal Architecture (Ports and Adapters)** pattern, which allows clear separation of concerns:

- **Domain Layer**: Contains the core business logic and rules. It is independent of frameworks or external systems.
- **Application Layer**: Orchestrates the domain logic and interacts with external services through ports.
- **Infrastructure Layer**: Implements the adapters that communicate with external systems like databases, APIs, and user interfaces.

The architecture is organized using **Vertical Slicing**, where each feature or context is self-contained. This approach makes it easier to manage and scale individual features without affecting others, which is ideal when expanding the application to cover additional contexts beyond pricing.

## Project Structure

The project is structured as follows:

```plaintext
└───src
│    ├───main
│    │   ├───java
│    │   │   └───com
│    │   │       └───cortestudios
│    │   │           └───pricingmanager
│    │   │               ├───pricing                                # Pricing feature or context             
│    │   │               │   ├───application                        # Application layer, includes services and use cases
│    │   │               │   │   ├───port
│    │   │               │   │   │   ├───input
│    │   │               │   │   │   └───output
│    │   │               │   │   ├───service
│    │   │               │   │   └───usecase
│    │   │               │   ├───domain                             # Domain layer, includes core business logic
│    │   │               │   │   ├───exception
│    │   │               │   │   └───model
│    │   │               │   └───infrastructure                     # Infrastructure layer, includes adapters, configurations, and controllers
│    │   │               │       ├───adapter
│    │   │               │       │   ├───persistence
│    │   │               │       │   │   ├───entity
│    │   │               │       │   │   └───repository
│    │   │               │       │   └───rest
│    │   │               │       ├───config
│    │   │               │       ├───dto
│    │   │               │       │   ├───request
│    │   │               │       │   └───response
│    │   │               │       ├───exception
│    │   │               │       └───util
│    │   │               │           └───mapperimpl
│    │   │               └───shared                                 # Shared classes and utilities
│    │   └───resources
│    │           └───static                                         # Static resources like favicon and banner.txt
│    │       ├── application.yml                                    # Default application configuration
│    │       ├── application-dev.yml                                # Development environment configuration
│    │       ├── application-prod.yml                               # Production environment configuration
│    │       ├── schema.sql                                         # SQL script to initialize the H2 in-memory database
│    │       └── data.sql                                           # SQL script to populate the H2 in-memory database
│    └───test
│        ├───java
│        │   └───com
│        │       └───cortestudios
│        │           └───pricingmanager
│        │               └───pricing
│        │                   ├───application                        # Unit tests for the application layer
│        │                   │   ├───service
│        │                   │   └───usecase
│        │                   └───infrastructure                     # Integration tests for the infrastructure layer
│        │                       └───adapter
│        │                           └───rest
│        └───resources
│            ├── application.yml                                    # Test environment configuration
│            └── docs                                               # HTTP request examples and documentation
│
├── .gitignore                                                      # Git ignore file
├── Dockerfile                                                      # Docker configuration for building and running the application
├── docker-compose.yml                                              # Docker Compose configuration
└── README.md                                                       # Project documentation (this file)
```

## Tests

The project includes both unit tests and integration tests:

Unit Tests: Focus on individual components, such as services and use cases, ensuring they work as expected in isolation.
Integration Tests: Test the interaction between components, including database access and REST API functionality.

### Running Tests

You can run all tests using Maven:
```bash
mvn clean verify
```
This command runs both unit and integration tests. To skip tests and build the application, use:
```bash
mvn clean package -DskipTests
```
## .env File Configuration
To configure environment variables for the application, create a .env file in the root directory with the following structure:

```plaintext
PM_PROFILE=dev
PM_H2_URI=jdbc:h2:mem:test_db
PM_H2_USER=dev
PM_H2_PASSWORD=
PM_LOG_PATH=./logs/pricingmanager
PM_PORT=8080
```

- PM_PROFILE: The active Spring profile (e.g., dev or prod).
- PM_H2_URI: The JDBC URI for the H2 database.
- PM_H2_USER: The username for the H2 database.
- PM_H2_PASSWORD: The password for the H2 database.
- PM_LOG_PATH: The path where logs will be stored.
- PM_PORT: The port on which the application will run.

## Running the Application Locally
If you want to run the application locally without Docker, follow these steps:
- Create the .env File:
- Ensure the .env file is in the root directory with the configuration as shown above.

### Run the application using Maven:
```bash
mvn spring-boot:run
```
The application will start using the configuration from the .env file.

## Running the Application with Docker
To run the application using Docker, follow these steps:

- Build the Docker Image and Run the Application:
```bash
docker-compose up --build
```
This command will build the Docker image, start the application, and set up an H2 database.

## Accessing the Application:
Once the application is running, you can access it via your web browser at:
[http://localhost:8080](http://localhost:8080)

Replace 8080 with the appropriate port if you've configured a different one in your .env file.