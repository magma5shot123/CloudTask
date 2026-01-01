# CloudTask API (Backend)

Backend application for managing companies and projects, built with Java 25, Spring Boot, and PostgreSQL.
The system allows creating, updating, and deleting projects, assigning them to companies and users, and managing project lifecycle via REST API.

## Tech Stack
- Java 25
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Jackson
- Jakarta Validation

## Architecture
- controller — REST controllers
- service — business logic
- repository — database access layer
- entity — JPA entities
- dto — request and response models
- enums — project status enums

## Main Features

- Create new projects
- Update existing projects
- Delete a project by ID
- Delete all projects of a company
- Assign projects to companies and users
- Project status management

## How to Run
## Clone the repository
```
git clone https://github.com/your-username/CloudTask_API_JAVA.git
cd CloudTask_API_JAVA
```

## Create PostgreSQL database
```
CREATE DATABASE cloud_task;
```

## Backend configuration
```
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cloud_task
    username: postgres
    password: password
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  jackson:
    serialization:
      indent-output: true
```

## Run Backend
```
mvn spring-boot:run
```


## Backend will be available at
```
http://localhost:8080
```

### API Examples
```
Create project
POST /projects
Content-Type: application/json

{
  "title": "Cloud Platform",
  "description": "Backend for cloud project",
  "status": "ACTIVE",
  "companyId": 1,
  "userId": 2
}
```
```
Update project
PUT /projects/{id}
Content-Type: application/json

{
  "title": "Updated Project",
  "description": "Updated description",
  "status": "IN_PROGRESS",
  "companyId": 1,
  "userId": 2
}
```
```
Delete project
DELETE /projects/{id}
```
```
Delete all projects of a company
DELETE /projects/company/{companyId}
```
```
Project Status
ACTIVE
IN_PROGRESS
COMPLETED
```

### Notes
- Business logic is implemented in the service layer
- DTOs are used to separate API models from entities
- Transactions are handled at the service level
- Clean RESTful endpoints

## Team
- Dima Polegenkii — Backend Developer (Java + Spring Boot)
- Telegram: @squizzysw
