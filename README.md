# **📝 Project Overview: Task Manager**
A standard web application for managing tasks, focusing on a server-side rendered architecture.

Core Technology Stack
- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 4.0.2
- **Database:** H2 (In-memory) with Liquibase for schema management
- **Frontend:** Thymeleaf templates and strictly HTMX

---

## **📸 System Demo - Home**

### 📌 Task Creation
![Task Create](/src/docs/gifs/1-task-create.gif)

*Task are colored and sorted by urgency. Red means OVERDUE. Orange means DUE TODAY. Yellow means DUE IN 1-3 DAYS. Else the task is colored WHITE*

![Task Highlighting](/src/docs/screenshots/1-task-highlighting.PNG)
---
### 📌 Task Editing
![Task Edit](/src/docs/gifs/2-task-edit.gif)

*Users are able to edit the details of 'active' tasks in the home page.*

### 📌 Task Deletion - Active Tasks
![Task Delete - Home](/src/docs/gifs/3-task-done.gif)

*Deletes that specific task entirely from the database.*

### 📌 Marking Task as Done
![Task Done](/src/docs/gifs/4-task-delete-home.gif)

*Tasks marked as 'done' are only shown in the history page.*

### 📌 Clearing Active Tasks
![Task Clear Home](/src/docs/gifs/7-task-clear-home.gif)

*The 'clear tasks' button in the home page specifically only clears active tasks present in the home page.*

---

## **📸 System Demo - History**

### 📌 Marking Task as Not Done
![Task Not Done](/src/docs/gifs/5-task-not-done.gif)

*Tasks marked as 'not done' return to the home page.*
### 📌 Task Deletion - Completed Tasks
![Task Delete - History](/src/docs/gifs/6-task-delete-history.gif)

*Deletes that specific task entirely from the database.*

### 📌 Clearing Finished Tasks
![Task Clear History](/src/docs/gifs/8-task-clear-history.gif)

*The 'clear tasks' button in the history page specifically only clears finished tasks present in the history page.*

---

## **🚦 Getting Started**

### Prerequisites
- JDK 21
- Maven 3.9+  

### Installation
Clone the repository:

```bash
git clone https://github.com/Brenedict/simple-todo
cd simple-todo
```
\
Run the application:

```bash
./mvnw spring-boot:run
```
\
Access the UI. Open the link via your browser

```
http://localhost:8080 
```

---

## **🧪 Testing**
To run both JUnit and Cucumber tests which were used for the acceptance test of the system. Run the following command.

```Bash
./mvnw test
```

---

## **📦 Project Dependencies**

### Framework & Core
- **Spring Boot Starter Web:** Provides the underlying web server and MVC structure for handling HTTP requests.
- **Spring Boot Starter Data JPA:** Manages the communication between Java objects and the database.
- **Thymeleaf:** The server-side template engine used to render the HTML views.

### Frontend Enhancement
- **HTMX:** Enables AJAX requests and partial page updates directly from HTML attributes, eliminating the need for custom JavaScript.

### Database Management
- **H2 Database:** An in-memory database used for development and testing environments.
- **Liquibase Core:** Handles database schema versioning and migrations (table creation, column changes).

### Testing Suite
- **Spring Boot Starter Test:** Includes JUnit 5, Mockito, and AssertJ for standard unit and integration testing.
- **Cucumber (Java & JUnit Platform Engine):** Enables Behavior-Driven Development by linking Gherkin feature files to Java test logic.
- **JUnit Platform Suite:** Required to group and run the Cucumber scenarios as a test suite.
