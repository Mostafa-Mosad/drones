# Drone Application

#Introduction:
We have a fleet of 10 drones. A drone is capable of carrying devices, other than cameras, and capable of
delivering small loads. For our use case the load is medications.

## Description
This application enable you:
##1- Register a new drone with specific conditions like:
Each Drone has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

##2- Load a drone with medication items, but the sum of medication items not greater than 500gm!

##Database: 
Using H2 in-memory database with data.sql script to insert 10 drones.
Open the following link to see data: http://localhost:8080/h2-console/ , then enter the following data:
  - url: jdbc:h2:mem:test
  - username: sa
  - password: password
  - then click connect!


##Build application:
````mvn clean install````
##Run command :
```` mvn spring-boot:run ````

##Swagger link for these APIs in local environment:
http://localhost:8080/swagger-ui/index.html
