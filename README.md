# SpringRestAPi+Angular5+IHM

### Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 3.3.9+ 
- Node 6.0 or above,  
- npm 5 or above,   
- Angular-cli 1.6.3

### Technology Stack
Component         | Technology
---               | ---
Frontend          | [Angular 5](https://github.com/angular/angular)
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox)
In Memory DB      | H2 
Persistence       | JPA (Using Spring Data)
Client Build Tools| [angular-cli](https://github.com/angular/angular-cli), npm
Server Build Tools| Maven (Java)
Tests Tools        | Junit / Mockito / Cucumber / Selenium 


### Build Frontend 
http://localhost:4200
```
# Front dev to src/main/ressources/public/
npm install
npm start
```

### Build Backend (SpringBoot Java)
http://localhost:8080
```
# Backend Dev to /
mvn clean install
mvn spring-boot:run
```

#### Links 

- Swagger : http://localhost:8080/swagger-ui.html
