# Sales Management System
<p align="center">
</p>

---
### (Not completed)
<li> Welcome to Sales Management System, here user can create, update and delete actions and search them based on specific search criteria</li>

##  📝Table of content

---
- [Built Using](#built).
- [Features](#features).
- [Requirements](#requirements).
- [Installation Steps](#installation).
- [Api documentation](#api).
- [concepts and patterns used](#concepts).
- [Acknowledgements](#acknowledgements).


## ⛏️ Built Using <a name = "built"></a>

---
- [MySQL](https://www.mysql.com/) - Database
- [Java](https://docs.oracle.com/en/java/) - Programming Language
- [Spring boot](https://spring.io/projects/spring-boot) - Web Framework
- [spring security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT](https://jwt.io/)
- [Junit](https://junit.org/junit5/) - testing framework
- [Mockito](https://site.mockito.org/) - mocking framework
- [H2](https://www.h2database.com/html/main.html) - lightweight in memory database for testing

## 🧐Features <a name = "features"></a>

---
- Login and Registration with JWT
- Endpoint for retrieving all actions and search them using some request parameters(username, be, application, traceId, param, paramTypeEn)
- You can also paginate them using (pageNo, sort(ASC or DESC), sortByColumn(column within the actionRequest) )
- 4 endpoints to retrieve, create, update and delete actions
- 2 endpoints for logout and refreshToken
- Unit tests for ActionRepository, ActionService and ApplicationService
- Endpoints to get all, create, update and delete applications, business entities, param types and Action types
- Input validation 
- Exception handling

## 🔧Requirements <a name = "requirements"></a>

---
- Java => 17
- Spring boot => 3.2.3
- Maven
- MySQL

## 🚀 Installation Steps <a name = "installation"></a>

---
First clone this repository, install the dependencies, and set up your .env file.

````
git clone https://github.com/VlixAli/Sales-Management-System.git
mvn clean install
cd src/main/resources
cp .env.example .env
````

then run the following commands to run the project

````
cd ../../../
mvn spring-boot:run
````

- the application will be available at [http://localhost:8080](http://localhost:8080)
- for login use [http://localhost:8080/api/v1/auth/login](http://localhost:8080/api/v1/auth/login) with the following credentials : 
- email : jerry@gmail.com
- password : password

note : you can use the user 2 generated users emails with the same password


## ✍️ Api documentation <a name = "api"></a>

---
For how to use the application checkout this api docs [Sales Management System documentation](https://documenter.getpostman.com/view/23171948/2sA3Bt2VCk)


## 🎈 concepts and patterns used <a name = "concepts"></a>
- DTO design pattern
- Service design pattern
- Repository design pattern
- Dependency injection
- JWT Authentication
- Spring security
- Spring data jpa
- Input validation using spring validation
- Filtering using spring specification
- Pagination using spring pageable
- Logout and refresh token
- Seeding the database
- Global exception Handling using rest advice controller
- Unit tests using Junit and mockito
- Custom exception
- Custom Validation annotations
