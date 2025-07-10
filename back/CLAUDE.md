# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4.2 application with Java 21 that implements an online document processing system ("tramite online"). The application features OAuth2 authentication (Google, GitHub), JWT token management, and form processing capabilities.

## Common Development Commands

### Build and Run
```bash
# Build the project
./mvnw clean compile

# Run the application
./mvnw spring-boot:run

# Build and run tests
./mvnw clean test

# Package the application
./mvnw clean package

# Run specific test class
./mvnw test -Dtest=SectionServiceTest

# Run specific test method
./mvnw test -Dtest=SectionServiceTest#testMethodName
```

### Docker Development
```bash
# Start PostgreSQL and Redis services
docker-compose up -d

# Stop services
docker-compose down

# Build Docker image
./mvnw spring-boot:build-image

# Run with Docker
docker run -p 8080:8080 tramite-online-back
```

### Database Operations
```bash
# Start only PostgreSQL
docker-compose up -d postgres

# The application uses Flyway for database migrations
# Migrations are located in src/main/resources/db/migration/V1/
```

## Architecture Overview

### Core Domain Structure
- **Domain Layer**: Entities (`User`, `WorkSpace`, `FormUser`, `Section`, `Question`) with JPA relationships
- **Service Layer**: Business logic with validation (`ValidatorFormUser`)
- **Controller Layer**: REST endpoints with proper HTTP status codes
- **Security Layer**: OAuth2 + JWT authentication with custom providers

### Key Components

#### Authentication & Security
- **OAuth2 Integration**: Google and GitHub providers with custom user info extractors
- **JWT Token Management**: Custom token provider with refresh token support
- **Security Configuration**: Custom authentication handlers and request resolvers
- **User Management**: Custom user details service with principal mapping

#### Data Layer
- **JPA Entities**: Auditable base class with creation/modification tracking
- **Repository Pattern**: Spring Data JPA repositories for all entities
- **Database**: PostgreSQL with Flyway migrations
- **Caching**: Redis integration configured (currently commented out)

#### Application Configuration
- **Properties**: Environment-based configuration with OAuth2 client credentials
- **Profiles**: Development and test profiles configured
- **Testcontainers**: Integration testing with PostgreSQL containers

### Package Structure
```
com.tramite.online/
├── audit/           # Auditing entities and configuration
├── config/          # Application configuration classes
│   └── security/    # Security-related configurations
├── constants/       # Application constants
├── controller/      # REST controllers
├── domain/          # Domain entities, DTOs, and models
├── exception/       # Custom exceptions and error handling
├── mapper/          # Entity-DTO mapping utilities
├── repository/      # Data access layer
└── service/         # Business logic layer
```

## Environment Configuration

### Required Environment Variables
```bash
# Database
DB_URL=jdbc:postgresql://localhost:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=postgres

# OAuth2 Providers
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret

# Application
PORT=8080
```

### OAuth2 Configuration
- Authorized redirect URIs are configured for localhost:4200 and localhost:3000
- JWT secret and expiration times are configurable via application.yaml

## Testing Strategy

### Test Structure
- **Unit Tests**: Service layer testing with Instancio for test data generation
- **Integration Tests**: Controller tests with TestContainers
- **Repository Tests**: JPA repository testing with H2 in-memory database
- **Abstract IT**: Base class for integration tests with common setup

### Test Database
- Uses H2 for unit tests and PostgreSQL TestContainers for integration tests
- Test profile configured in `application-test.properties`

## API Documentation

The application includes SpringDoc OpenAPI integration available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Development Notes

### Security Considerations
- JWT tokens are used for stateless authentication
- OAuth2 integration supports multiple providers with extensible factory pattern
- CSRF protection is configured appropriately for the authentication flow

### Form Processing
- The application centers around form processing with questions and sections
- Forms are associated with users through the `FormUser` entity
- Questions support different types through the `QuestionType` enum

### Workspace Management
- Users can have multiple workspaces for organizing forms
- Workspaces have relationships with both users and forms