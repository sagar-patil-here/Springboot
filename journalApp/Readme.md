# Journal Application

A robust, production-ready RESTful Web API built using the **Spring Boot** framework and **MongoDB** for persistence. The application enables a complete multi-user journaling system where users can create accounts and securely maintain their personal journal logs using structured CRUD operations.

---

## ⭐️ Key Features

* **Multi-User Management:** Built-in endpoints for registering, retrieving, and updating user profiles.
* **Journal Entities Integration:** Leverages MongoDB's `@DBRef` mechanics to link individual journal entries seamlessly to a specific user.
* **Dual Mode Storage Control:** Features historical controllers for localized, in-memory Map storage testing alongside production MongoDB controller variants.
* **Automated Index Lifecycle:** Configuration enables automated MongoDB index structures (e.g., ensuring strict username uniqueness constraints).
* **Health Checks:** Exposes direct service health metrics.

---

## 🛠️ Technology Stack

* **Backend:** Java 17, Spring Boot 3.5.0
* **Web Layer:** Spring Web MVC RESTful controllers
* **Data Tier:** Spring Data MongoDB
* **Utilities:** Project Lombok (Developer productivity & boilerplate reduction)
* **Build Tool:** Maven (Wrapper included)

---

## 📂 Architecture & Directory Breakdown

The codebase strictly adheres to standard corporate Spring Boot package layer architecture:

```text
dev.sagar.journalApp
├── JournalAppApplication.java         # Core Application Bootstrapper
├── controllers
│   ├── HealthCheck.java               # Basic App Health Monitor Up/Down Check
│   ├── JournalEntryController.java    # Legacy In-Memory Testing Controller Layer
│   ├── JournalEntryController_Mongo.java # Live DB Journal Entry REST Endpoints
│   └── UserController.java            # Multi-User Lifecycle Management Endpoints
├── entity
│   ├── JouranalEntry.java             # MongoDB Core Journal Entity Structure
│   └── User.java                      # MongoDB Authenticated User Data Model
├── MongoConnRepo
│   ├── JouneralRepo.java              # Journal Entry Interface (MongoRepository Wrapper)
│   └── UserRepo.java                  # User Repository Mapping Layer
└── Services
    ├── JEServices.java                # Business Logic layer for handling Journal Actions
    └── UserServices.java              # Business Logic layer for handling User Actions
⚙️ Configuration & Prerequisites
Before firing up the application, verify that your localized system configuration matches the target environment.

🍃 MongoDB Configuration
Ensure you have an active instances of MongoDB running locally. The configurations are managed inside src/main/resources/application.properties:

Properties
spring.application.name=journalApp
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=journalDB
spring.data.mongodb.auto-index-creation=true
📡 API Endpoints Spec Sheet
🩺 Health Checks
GET /health — Returns status response OK.

👥 User Endpoints (/user)
GET /user — Fetch all system users.

GET /user/{username} — Query an individual user by profile handle.

POST /user — Provision and insert a new user profile.

PUT /user/{username} — Update core properties (username/password) safely for a matching user handle.

📝 MongoDB Active Journal Endpoints (/journal)
GET /journal — Returns the landing welcome message.

POST /journal/submit/{userName} — Instantiates a fresh journal block linked to an existing user.

GET /journal/show-entries/{userName} — Fetches the complete collection of journal entries assigned strictly to the targeted user.

PUT /journal/update — Persists modifications to an altered journal entry back to the active cluster.

DELETE /journal/delete — Purges and clears specific journal item records.

🏃 Building and Running the Application
📦 Compilation & Dependency Fetching
Execute the following to clear historical target builds and resolve all necessary structural libraries:

Bash
# On Mac/Linux
./mvnw clean compile

# On Windows Command Line or PowerShell
mvnw.cmd clean compile
🎯 Launching the Service
To spin up the service layer on your localhost context (Default Spring port: 8080):

Bash
# On Mac/Linux
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
🧪 Executing Test Suites
To fire the isolated JUnit lifecycle verification environments:

Bash
./mvnw test