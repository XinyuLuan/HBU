# HBU Backend Project Starter

## Getting Started
Dependencies:
* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) (or Ultimate) recommended 
* [Java SE Development Kit 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL Server 8](https://dev.mysql.com/downloads/mysql/) (or another standalone SQL instance)
* [Postman](https://www.getpostman.com/downloads/)

Part of this project involves configuring a Spring application to connect to an external data source. Before beginning this project, you must install a database to connect to. Here are [instructions for installing MySQL 8](https://dev.mysql.com/doc/refman/8.0/en/installing.html).

### Installation

1. Clone or download this repository.
2. Open IntelliJ IDEA.
3. In IDEA, select `File` -> `Open` and navigate to the `backend` directory within this repository. Select that directory to open.
4. The project should open in IDEA. In the project structure, navigate to `src/main/java/com.hbu.backend`. 
5. Within that directory, click on CritterApplication.java and select `Run` -> `Debug BackendApplication`. 
6. Open a browser and navigate to the url: [http://localhost:8082/demo/home](http://localhost:8082/demo/home)

You should see the message "HBU Starter installed successfully" in your browser.

### Database setup
run the following query in 
```
CREATE SCHEMA `hbuDatabase` ; -- Create the plant database

CREATE USER 'hbuAdmin'@'localhost' IDENTIFIED BY 'hbu12345'; -- Create the user if you haven’t yet
GRANT ALL ON hbuDatabase.* TO 'hbuAdmin'@'localhost';
```

### Database Connection Testing
* Open terminal
* run `curl localhost:8080/demo/add -d name=First -d email=someemail@someemailprovider.com'`
* The reply should be *Saved*
* run `curl 'localhost:8080/demo/all'`
* The reply should be as follows: *[{"id":1,"name":"First","email":"someemail@someemailprovider.com"}]*
