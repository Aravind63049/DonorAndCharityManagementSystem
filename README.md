Donor & Charity Management System

A full-stack web application designed to manage donors, donations, fundraising campaigns, fund allocation, and user accounts. The system helps organizations track charity activities in a structured and efficient way.

CharityProject img
⭐ Key Features :

User Management – secure login and logout
Donor Module – add, update, view, and delete donors
Donation Module – record and track donations
Campaign Module – manage charity campaigns
Fund Allocation – allocate donation amounts to campaigns
Dashboard – clean and responsive UI with quick navigation
REST API – backend services exposed using Spring Boot
🧰 Technology Stack : Backend: Spring Boot, Java, Spring Security, JPA/Hibernate, MySQL Frontend: HTML, CSS, JavaScript Build Tool: Maven IDE: IntelliJ IDEA

📂 Project Structure backend/

controller
service
repository
models
dto
exception
security
frontend/

index.html
css/
js/
⚙️ How to Run 1️⃣ Database Setup CREATE DATABASE donor_management;

2️⃣ Configure Backend

Edit application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/donor_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
3️⃣ Start Backend mvn spring-boot:run

4️⃣ Start Frontend Open index.html in your browser.

📘 Sample API Endpoints

POST /users
GETBYID /donors/1
GRTALL /donations
PUT /campaigns/2
DELETE /fund-allocations/3
