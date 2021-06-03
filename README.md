# KotlinForFun
This service provides APIs to manage newsArticle

# Technologies
* JAVA 11
* Kotlin 1.4.32
* Spring boot 
* Swagger 3.0.0
* Hibernate 5.4.22
* Liquibase  4.3.3
* PostgreSql 13

# How to run project in local
### Precondition
* Run protgreSQL on your docker by this command
  `docker run --name media-postgres -d -p 5432:5432 -e POSTGRES_PASSWORD=test -e POSTGRES_USER=media postgres:13.2`
  OR
  * Docker Compose file => right click on "docker-compose.yml" and run "docker-compose.yml"
### IntelliJ IDEA
* import the project as a Gradle project
* Go to Run | Edit configuration
* Creat Spring Boot Runner add "de.kotlinForFun.rafael.RafaelApplication" as main class

### Without IntelliJ IDEA
./gradle build
./gradle run

# To access Swagger
http://localhost:8080/swagger-ui.html