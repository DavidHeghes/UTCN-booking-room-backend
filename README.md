#  UTCN Room Booking System

> **Hi! This is my first full-stack project after completing a Spring Boot Udemy course ("Spring Boot 4: Learn Spring 7, Spring Core, Spring REST, Spring Security, JPA..." - by Chad Darby). This project was born from a real need to organize college rooms for extracurricular events, ensuring a smooth scheduling process for student organizations.**
>  **I coded the entire Spring Boot backend, security layers, and database architecture from scratch. The frontend was created entirely with AI, allowing me to dedicate all my time to mastering server-side Java engineering.** This approach helped me deliver a complete, functional product while mastering Spring Boot fundamentals.

---

##  Tech Stack
* **Backend:** Java, Spring Boot, Spring Web
* **Database & ORM:** MySQL, Spring Data JPA, Hibernate
* **Security:** BCrypt Password Encoder
* **Frontend (Separate Repo):** React.js

##  About The Project
This repository contains the **Backend API**. It handles room management, reservation scheduling, and complex validation logic to ensure no room is ever double-booked.


##  Security & Data Integrity

Security was a priority for this system. I implemented several layers of protection to ensure data safety and proper access control:

###  Authentication & Authorization
* **BCrypt Password Hashing:** User passwords are never stored in plain text. I used the **BCrypt** algorithm to hash passwords before saving them to the MySQL database, protecting users even in case of a data leak.
* **Role-Based Access Control:**
    * **Admin:** Only users with the "@admin.com" email address can add or delete college rooms. Users can't create an admin email, the admin accounts are inserted manually into the database.
    * **User:** Regular users can only browse rooms and manage their own bookings.
* **Resource Ownership:** I implemented logic to ensure that a reservation can **only be deleted by the person who created it**.

###  Data Validation & Constraints
* **Email Domain Restriction:** To ensure the system is used only for its intended purpose, I added validation to accept only **@gmail.com** addresses.
* **Input Sanitization & Normalization:** All data sent from the frontend is validated. For example, emails are automatically converted to lowercase before saving, preventing duplicate accounts caused by case sensitivity.

##  Advanced Business Logic & Error Handling
The service layer acts as the brain of the application, enforcing strict business rules, such as:

* **Smart Booking Validation:** The system strictly verifies time-range integrity (e.g., the start time must be before the end time).
* **Double-Booking Prevention:** A custom database query (`isRoomOccupied`) acts as a safeguard to ensure no two people can successfully book the same room during overlapping hours.
* **Duplicate Rooms Prevention:** Admins can't add two rooms with the same name.
* **Dynamic Availability:** The backend intelligently processes existing reservations to return only available time slots, preventing conflicts at the source.
* **Clean Exception Handling:** Instead of generic server crashes, the backend throws specific, meaningful exceptions (like `ROOM_ALREADY_OCCUPIED`, `INVALID_TIME_RANGE`, or `UNAUTHORIZED`), ensuring a smooth experience for the frontend client.

## UI Screenshots (Frontend Integration)
<br></br>
On the authentication page, all fields are mandatory. Additionally, the backend verifies if the provided email is already registered to prevent duplicate accounts.
<br></br>
<img width="1902" height="974" alt="image" src="https://github.com/user-attachments/assets/8de2ff2e-d1d6-4259-9fca-9ab978816a13" />
<img width="1887" height="1022" alt="image" src="https://github.com/user-attachments/assets/4b7e025c-6858-4e0b-a391-88eecb8da0f6" />
<br></br>
<br></br>
Users logging in with an @admin.com email address gain access to exclusive management features. Administrators can add new rooms (with backend validation preventing duplicate names) or delete existing ones.
<br></br>
<img width="1893" height="1025" alt="image" src="https://github.com/user-attachments/assets/a476c470-eb05-4d6c-9fdb-8257ce04d68f" />
<br></br>
<br></br>
The application provides personalized toast notifications based on the user's state. Returning users are greeted with a 'Welcome back, {name}!' popup, whereas newly registered users see an 'Account created, welcome {name}!' message.
<br></br>
<img width="1897" height="1028" alt="image" src="https://github.com/user-attachments/assets/50ad81c5-d7d7-46eb-9ee6-c4aa2e0a9d0b" />

<img width="1896" height="1030" alt="image" src="https://github.com/user-attachments/assets/d5deb886-a4bd-48d5-8624-9b24173f9caa" />
<br></br>
<br></br>
To prevent scheduling errors, the booking interface dynamically disables invalid time slots. Users cannot select past dates or hours, and the system automatically grays out intervals that have already been booked by others.
<br></br>
<img width="1890" height="1018" alt="image" src="https://github.com/user-attachments/assets/4166aff6-76d6-47b2-b783-f2a78f05fdbb" />
<br></br>
<br></br>
Users can view their own reservations and easily cancel them if necessary.
<br></br>
<img width="1891" height="1028" alt="image" src="https://github.com/user-attachments/assets/44ada0a7-37db-447c-9df3-cfc90282e29d" />

---

##  What I Learned
Building this project from scratch was the perfect bridge between theoretical course knowledge and real-world application architecture. Throughout development, I significantly improved my understanding of:

* Structuring a scalable Spring Boot application (Controllers, Services, Repositories, Entities).
* Managing relational data and complex queries with Spring Data JPA & Hibernate.
* Designing clean, REST APIs and handling custom exceptions to provide meaningful HTTP responses to the client.







  
