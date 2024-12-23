# API Bancário

## Overview
API Bancário is a Spring Boot application designed to manage banking operations. It uses Spring Data JPA for database interactions and Springdoc OpenAPI for API documentation.

## Prerequisites
- Java 17
- Gradle
- PostgreSQL (for production)
- H2 Database (for development and testing)

## Getting Started

### Clone the repository
```sh
git clone https://github.com/miletodev/api_bancario.git
cd api_bancario
```
### Build the project
```sh
gradle build
```
### Run the project
```sh
gradle bootRun
```
### Configuration
The application can be configured using the application.properties file located in the src/main/resources directory.

### Dependencies

Spring Boot 3.3.4
Spring Data JPA
Spring Web
Springdoc OpenAPI
H2 Database
PostgreSQL

## API Documentation
The API documentation is available at http://localhost:8080/swagger-ui.html

## License
This project is licensed under the MIT License.

