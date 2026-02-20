# clinical-form-gateway

Enterprise Healthcare Form Integration Middleware.
This repository is riddled with comments for in-depth understanding of SpringBoot to understand the architecture and working of this framework (behind-the-screens).

## Overview

clinical-form-gateway is an API-first middleware platform for rendering,
validating, transforming, and routing configurable clinical form submissions.

## Core Capabilities (Planned)

- Configurable form definitions (versioned)
- Structured submission pipeline
- Adapter-based downstream integrations
- Audit and delivery tracking
- Future FHIR compatibility layer

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- H2 (development)
- Maven

## Run

```bash
mvn spring-boot:run
```