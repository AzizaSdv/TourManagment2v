# TourManagment2v
#  Tour Management System

The Tour Management System is a Java-based console application that allows admins to manage tours and customers to book and review tours. The system includes authentication, role-based menus, and a PostgreSQL database for data storage.

---

#  Features
#  Admin Panel
- Add new tours 
- View all tours 
- Delete tours 
- Update tour details 

#  Customer Panel
- View available tours 
- Book tours 
- Leave reviews and ratings 

#  Authentication
- Secure login system
- Role-based access control (Admin/Customer)

---

# Tech Stack
- Java 17+
- PostgreSQL 17(pgAdmin 4)
- JDBC
- Maven
- IntelliJ IDEA 2024.3

---

# How to Set Up and Run the Project
# Clone the Repository
```sh
git clone https://github.com/AzizaSdv/TourManagment2v.git
cd TourManagment2v
```

# Set Up the Database
1. Start PostgreSQL and open PgAdmin 4
2. Create a new database:
   ```sql
   CREATE DATABASE MoneyTour;
   ```
3. Open *SQL Query Editor* and create the required tables:
   ```sql
   CREATE TABLE IF NOT EXISTS users (
       id SERIAL PRIMARY KEY,
       name TEXT NOT NULL,
       email TEXT UNIQUE NOT NULL,
       password TEXT NOT NULL,
       role TEXT CHECK (role IN ('admin', 'customer')) NOT NULL
   );

   CREATE TABLE IF NOT EXISTS tours (
       id SERIAL PRIMARY KEY,
       name TEXT NOT NULL,
       location TEXT NOT NULL,
       price DOUBLE PRECISION NOT NULL,
       date TEXT NOT NULL
   );

   CREATE TABLE IF NOT EXISTS bookings (
       id SERIAL PRIMARY KEY,
       user_id INT REFERENCES users(id),
       tour_id INT REFERENCES tours(id)
   );

   CREATE TABLE IF NOT EXISTS reviews (
       id SERIAL PRIMARY KEY,
       user_id INT REFERENCES users(id),
       tour_id INT REFERENCES tours(id),
       rating INT CHECK (rating BETWEEN 1 AND 5),
       comment TEXT
   );
   ```
4. Insert sample users:
   ```sql
   INSERT INTO users (name, email, password, role) VALUES
   ('Aziza Sadiouva', 'azisdv@mail.ru', 'Lasto4ka22', 'admin'),
   ('Saya Bezwifi', 'saya@mail.ru', 'sayabaltabai', 'customer'),
   ('Diana Aibekovna', 'dina@list.ru', 'bezzubov', 'customer');
   ```

# Configure Database Connection
1. Open `DatabaseHandler.java`.
2. Ensure the database connection string is correct:
   ```java
   connection = DriverManager.getConnection(
       "jdbc:postgresql://localhost:5432/MoneyTour",
       "postgres",
       "YOUR_PASSWORD"
   );
   ```
   Replace `"YOUR_PASSWORD"` with your actual PostgreSQL password.

# Build and Run the Project
# Using Maven
```sh
mvn clean install
mvn exec:java
```
# Without Maven
1. Open IntelliJ IDEA.
2. Open `TourManagementSystem.java`.
3. Click Run .

---

#Project Structure
 `src/main/java/tourmanagement`
-  `TourManagementSystem.java` – Main application (entry point)
-  `DatabaseHandler.java` – Database connection
-  `Admin.java` – Admin functionalities
-  `Customer.java` – Customer functionalities
-  `Tour.java` – Tour model
-  `Booking.java` – Booking model
-  `Review.java` – Reviews model
-  `User.java` – User authentication

---

# *How the System Works*
1. The system prompts the user to log in.
2. If the email and password match a record in the `users` table:
   - Admins access the Admin Panel.
   - Customers access the Customer Panel.
3. Admins can manage tours, while customerscan book and review them.

---

# Troubleshooting
1. Login Not Working?
   - Check if the email and password exist in the `users` table:
     ```sql
     SELECT * FROM users WHERE email = 'your_email' AND password = 'your_password';
     ```
   - Ensure DatabaseHandler.java has the correct credentials.
   
2. Database Connection Issues?
   - Verify PostgreSQL is running.
   - Check if the database `MoneyTour` exists.

3. Maven Not Working?
   - Ensure Maven is installed:
     ```sh
     mvn -version
     ```
   - If not installed, set Maven Home Path in IntelliJ IDEA.

---

# Contact
Author: Aziza Sadiouva  
Email: azisdv@mail.ru  

