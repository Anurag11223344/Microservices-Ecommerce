# Microservices-Ecommerce


  

# E-Commerce System Using Microservices

# Tech Stack: Spring Boot, RabbitMQ, Docker, DockerCompose, maven

## Introduction

This project is a modular, distributed e-commerce platform built using the Microservices Architecture pattern. It showcases how different business functionalities (like order handling, inventory checks, and payment processing) can operate as independent services, each communicating via event-driven messaging using RabbitMQ.

  

---

  

## System Components
The architecture comprises three main microservices, each dedicated to a specific responsibility. These services are independently deployable and communicate asynchronously.
  

### 1. Order-Service 
# Port: 8081

Acts as the entry point for the application. It accepts user requests to place, view, or delete orders and coordinates with the inventory and payment services to complete the order lifecycle.

# Responsibilities:

- Accepting new order requests

- Interacting with inventory to validate product availability

- Sending payment initiation events

- Updating order status (e.g., PENDING, CONFIRMED, FAILED)

  

#### Endpoints:

- Method      | Endpoint               | Description
- POST        | /api/order/place       | Initiates a new order request
- GET         | /api/order/all         | Fetches a list of all orders
- GET         | /api/order/status/{id} | Returns the status of a specific order
- DELETE      | /api/order/delete/{id} | Removes an order from the database

  

### 2. Inventory-Service 
# Port: 8082

Manages product stock and inventory details. Validates stock availability during order requests and updates the inventory accordingly after an order is confirmed.

# Responsibilities:
- Storing product data and stock information

- Providing stock availability checks

- Adjusting inventory when products are purchased

- Deleting outdated or unavailable products

#### API Endpoints:

- Method      | Endpoint                                 | Description
- POST        | /api/inventory/add                       | Adds a new product to inventory
- DELETE      | /api/inventory/delete/{productName}      | Deletes a product from stock
- GET         | /api/inventory/all                       | Lists all products in the inventory5

  

### 3. Payment-Service 
# Port: 8083

Processes payments once inventory confirms product availability. Sends final order status back to the Order Service, completing the transaction lifecycle.

# Responsibilities:
- Simulating payment success/failure

- Handling business logic related to payment confirmation

- Communicating payment result back to the Order Service
  

#### API Endpoints:

- Method        | Endpoint                     | Description
- GET           | /api/payment/all             | Lists all payment transactions
- GET           | /api/payment/status/{item}   | Retrieves payment result for a specific product

  

---

  

## Communication Flow Between Services

  
User places order

  ↓

Order-Service sends message → order-queue (RabbitMQ)

  ↓

Inventory-Service receives message → checks/reduces stock

  ↓

Inventory-Service sends confirmation → payment-queue

  ↓

Payment-Service processes payment

  ↓

Payment-Service sends result → order-queue

  ↓

Order-Service receives result → updates order status

  

---

  

## Running the Project

#  Pre-requisites:
- Docker installed and running

- Java + Maven installed

### Spin Up Docker Containers

- docker-compose up --build

---

  

## RabbitMQ Dashboard

```

http://localhost:15672

Username: guest

Password: guest

```

  
