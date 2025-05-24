
---

## ⚙️ Prerequisites

- Java 17+
- MySQL 5.7/8+
- Git

---

## ⚡️ Configuration
You have to create a database in mysql with name metrobooking. 
Edit the `application.yml` inside the `executable/` directory to configure database and server settings:

### Example `application.yml`
```yaml
spring:
  application:
    name: metro-booking-app

  datasource:
    url: jdbc:mysql://localhost:3306/metrobooking
    username: root
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## ▶️ Launch Instructions

1. Open a terminal and navigate to the `executable/` directory where both the JAR and `application.yml` are located.

2. Run the application using this command:

```bash
java -jar metro-booking-app-1.0.jar --spring.config.location=application.yml
```
# Access to application interface
Open URL `http://localhost:8080` to access UI.
