# Ticket App
<a name="readme-top"></a>

[![MIT License][license-shield]][license-url]
[![Java Platform](https://img.shields.io/badge/platform-Java-blue.svg)](https://docs.oracle.com/en/java/)
[![REST Architecture](https://img.shields.io/badge/architecture-REST-5DADE2.svg)](http://www.vogella.com/tutorials/REST/article.html)
[![Spring Boot Framework](https://img.shields.io/badge/framework-Spring%20Boot-brightgreen.svg)](https://projects.spring.io/spring-boot/)

## Technologies
- Java JDK 17
- Spring Boot 2.7+
- Spring Data
- Spring Admin Server
- Spring Gateway
- Spring Config Server
- Spring Cloud OpenFeign
- Spring Cloud Netflix Eureka
- Maven
- Cassandra
- MySQL
- ElasticSearch
- RabbitMQ
- Resillience4j
- Docker - Docker Compose

## Functional Services
App was decomposed into three core microservices. All of them are independently deployable applications, organized around certain business domains.
Account Service, Ticket Service and Notification Service.

![Ticketapp](https://github.com/mertbesirli/ticket-app/assets/43350594/531b2d23-5ef1-4f87-8b61-febbd5220527)

### API Gateway

As you can see, there are three core services, which expose external API to client. In a real-world systems, this number can grow very quickly as well as whole system complexity. Actually, hundreds of services might be involved in rendering of one complex webpage.

In theory, a client could make requests to each of the microservices directly. But obviously, there are challenges and limitations with this option, like necessity to know all endpoints addresses, perform http request for each peace of information separately, merge the result on a client side. Another problem is non web-friendly protocols which might be used on the backend.

Usually a much better approach is to use API Gateway. It is a single entry point into the system, used to handle requests by routing them to the appropriate backend service or by invoking multiple backend services and aggregating the results.  Also, it can be used for authentication, insights, stress and canary testing, service migration, static response handling, active traffic management. Port number is 8088.

### Eureka Server

Another commonly known architecture pattern is Service discovery. It allows automatic detection of network locations for service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.

The key part of Service discovery is Registry. I use Netflix Eureka in this project. Eureka is a good example of the client-side discovery pattern, when client is responsible for determining locations of available service instances (using Registry server) and load balancing requests across them.

With Spring Boot, you can easily build Eureka Registry with spring-cloud-starter-eureka-server dependency, @EnableEurekaServer annotation and simple configuration properties.

Also, Eureka provides a simple interface, where you can track running services and a number of available instances: http://localhost:8761

### Circuit Breaker

Resilience4j is the implementation of Circuit Breaker pattern, which gives a control over latency and failure from dependencies accessed over the network. The main idea is to stop cascading failures in a distributed environment with a large number of microservices. That helps to fail fast and recover as soon as possible - important aspects of fault-tolerant systems that self-heal.

### RabbitMQ

![Ticketapp - Page 2](https://github.com/mertbesirli/ticket-app/assets/43350594/c88089e4-8365-4451-921e-b3fede15c9bf)

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.md` for more information

[license-shield]: https://img.shields.io/badge/license-MIT%20License-green.svg
[license-url]: https://github.com/mertbesirli/ticket-app/blob/main/LICENSE
