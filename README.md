BackendCoursework
---
## Technologies Used

- Java
- Docker
- MySQL

## Setup

### Docker Compose

The project includes a Docker Compose configuration for easy setup of the application and database services.

#### Prerequisites

- Docker
- Docker Compose

#### Steps

1. Clone this repository.
2. Navigate to the root directory.
3. Run `docker-compose up`.
4. Access the API at `http://localhost:8080`.

## Running the Application Locally

To run the application locally:

1. Navigate to the `Application` directory.
2. Build and run the application using Java.

## Overview

The project is a comprehensive API system designed to manage various aspects of an airline ticketing system. 
It includes endpoints for handling flight information, user orders, authentication, and reviews. 
The API allows users to:

- **Flights:**
  - Retrieve a list of available flights.
  - Access specific flight details using route codes.

- **Orders:**
  - Create new orders for flights.
  - Pay for existing orders.
  - Check order status.
  - Retrieve a list of all orders for an authorized user.

- **Authentication:**
  - Register new users.
  - Authenticate users and manage access tokens.
  - Refresh authentication tokens.

The project utilizes Java for the application logic, Docker for containerization, and MySQL for the database. It provides a secure environment through JWT (Bearer Token) authentication for most endpoints.




## API Documentation

This project provides an API that allows interaction with several endpoints for managing orders, flights, authentication, and reviews.

### Endpoints

#### Ping

- **Description:** Ping endpoint for testing connectivity.
- **Endpoint:** `/api/v1/ping`
- **Method:** GET
- **Security:** Requires JWT authentication (Bearer Token)
- **Responses:**
  - `200`: Success
  - `403`: Unauthorized / Invalid Token

#### Flights

- **Description:** Manage flight information.
- **Endpoints:**
  - `/api/v1/flights`: Retrieve a list of available flights.
  - `/api/v1/flights/{code}`: Retrieve a specific flight by code.
- **Methods:**
  - GET (for both endpoints)
- **Security:** Requires JWT authentication (Bearer Token)
- **Responses:**
  - `200`: Successful retrieval of flights
  - `500`: Internal Server Error
  - `403`: Not authorized

#### Orders

- **Description:** Manage order-related operations.
- **Endpoints:**
  - `/api/v1/order/pay`: Endpoint for paying an order.
  - `/api/v1/order/create`: Endpoint for creating an order.
  - `/api/v1/order/status`: Get order status.
  - `/api/v1/order/all`: Get all orders of an authorized user.
- **Methods:**
  - POST (`/api/v1/order/pay`, `/api/v1/order/create`)
  - GET (`/api/v1/order/status`, `/api/v1/order/all`)
- **Security:** Requires JWT authentication (Bearer Token)
- **Responses:**
  - `200`: Successful operation
  - `403`: Not authorized/Internal Server Error

#### Authentication

- **Description:** Handles user authentication and token management.
- **Endpoints:**
  - `/api/v1/auth/register`: User registration.
  - `/api/v1/auth/refresh-token`: Token refresh.
  - `/api/v1/auth/authenticate`: User authentication.
- **Methods:**
  - POST (for all endpoints)
- **Responses:**
  - `200`: OK
  - `403`: Unauthorized/Internal Server Error

### Security

The API endpoints are secured using JWT (Bearer Token).


### License: MIT
## TODO:
- [x] dockerize
- [ ] finish java code
  - [x] create dtos
  - [x] write unit tests (user accessible logic)
  - [x] finish booking logic
  - [ ] clean up code
- [x] write postman tests half
- [ ] .docx
- [ ] .pptx
- [ ] deploy to vps or cloud
