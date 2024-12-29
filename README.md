# 🏠 Overview of PropertyWise API

Welcome to the documentation for PropertyWise, a Kotlin-based web application developed to simplify property rentals and sales. This comprehensive platform serves as a one-stop solution for managing real estate, from apartments to plots, enhancing the experience for buyers, renters, and agents.

---

## 🎯 Purpose

The PropertyWise API is built to:

- **Streamline Property Management:** Simplify property listing, searching, and tracking.
- **Provide Financial Tools:** Offer loan calculators, rental yield analysis, and deposit computations.
- **Enhance Communication:** Enable seamless interactions with integrated email services.
- **Deliver Insights:** Analyze real estate trends and prices for informed decision-making.
- **Ensure Security:** Leverage robust authentication and role-based access control.

---

## 💡 Features

The PropertyWise API provides a wide range of capabilities to enhance property management and decision-making:

- **Property Management:** CRUD Operations: Add, view, update, and delete properties with detailed attributes such as title, location, price, type, and size. || Search and Filter: Discover properties using advanced filters for location, price range, area, property type, and room count. || Favorites List: Save and access preferred properties conveniently.
- **Financial Calculations:** Loan Calculator: Compute monthly installments, total loan amounts, and eligibility based on user inputs. || Rental Yield Analysis: Evaluate the return on investment for rental properties. || Shared Rental Costs: Divide rental costs proportionally based on the space occupied. || Deposit Contribution: Calculate upfront payments required for property mortgages.
- **Communication:** Integrated Email Services: Send professional emails directly to buyers, renters, or agents.
- **Send Professional Emails:** Communicate effectively with employers directly from the platform.
- **Notifications:** Customizable Alerts: Set thresholds based on price and area for tailored property updates. || Scheduled Updates: Receive daily notifications about new listings matching user preferences.
- **Analytics and Insights:** Price Trends: Monitor changes in real estate prices over time in specific locations. || Average Price per Square Meter: Analyze regional pricing to support informed decision-making.
- **Security:** Authentication: Ensure secure login and registration using JWT tokens. || Role-Based Access Control: Protect sensitive operations with user roles, such as Admin and User.

---  

## 🛠️ Technologies Used

- **Kotlin**: Core language for building the API.
- **Spring Boot**: Framework for efficient and scalable application development.
- **JWT (JSON Web Token)**: Secure authentication and authorization.
- **Spring Security**: Implements role-based access control for user safety.
- **Spring Data JPA**: Simplifies database operations with repository and ORM support.
- **MySQL**: Relational database for storing user and application data.
- **Swagger**: Generates interactive API documentation for better usability.
- **Maven**: Dependency and project build management.
- **Automation**: Scheduled Jobs
- **Email Integration**: Facilitates sending job applications and professional emails directly from the platform, enhancing communication efficiency. 

---

## 🚀 Running the Project
### 1. Prerequisites:
- **JDK 17:** Install JDK 17.
- **MySQL:** Install and run MySQL server locally.
- **Maven:** Install Maven.
- **Google Account:** Needed for email configuration (if you want to use this feature).
- **Postman:** You'll need Postman to test the API of the application. If you don't have it, download and install Postman.

### 2. Configure MySQL Database:
- Ensure that you have MySQL installed and running on port 3306.
- Create a database. You can name it anything, but for this example, we are using the name cvenjoyer. If you prefer a different name, modify the configuration in the application.properties file accordingly.

- To create the database, run the following query in MySQL:
- **CREATE DATABASE cvenjoyer;**
- If you decide to use a different database name, remember to update the spring.datasource.url value in the application.properties file to reflect the correct database name:
- **spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name**

### 3. Configure application.properties File:
- **In the application.properties file (found in src/main/resources), input the following configuration values:**

| Variable Name                                      | Value                                                        | Description                                                                                     |
|----------------------------------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| spring.datasource.url                              | jdbc:mysql://localhost:3306/propertieskotlin                 | The URL for connecting to the MySQL database (change `propertieskotlin` to your database name if needed).|
| spring.datasource.username                         | root                                                         | The username for your MySQL database.                                                          |
| spring.datasource.password                         | root                                                         | The password for your MySQL database.                                                          |
| spring.datasource.driver-class-name                | com.mysql.cj.jdbc.Driver                                     | The JDBC driver class used to connect to MySQL.                                                |
| spring.jpa.hibernate.ddl-auto                      | update                                                       | Hibernate configuration for automatic schema updates (can be set to `none`, `create`, `create-drop`).|
| spring.jpa.show-sql                                | true                                                         | Enables the display of SQL queries in the logs.                                                |
| jwt.expiration                                     | 300000000                                                    | The expiration time for the JWT token in milliseconds.                                         |
| jwt.secret                                         | DefinitelyNotASecretDefinitelyNotASecretDefinitelyNotASecret | The secret key used to sign and verify the JWT token.                                          |
| spring.mail.host                                   | smtp.gmail.com                                               | The SMTP server used for email sending (in this case, Gmail).                                  |
| spring.mail.port                                   | 587                                                          | The port used for SMTP communication (587 is the default for Gmail).                           |
| spring.mail.username                               | fill it                                                      | Your email address used for sending emails.                                                    |
| spring.mail.password                               | fill it                                                      | The application-specific password generated for Gmail (not your regular Gmail password).       |
| spring.mail.protocol                               | smtp                                                         | The protocol used for sending emails (SMTP in this case).                                      |
| spring.mail.properties.mail.smtp.auth              | true                                                         | Enables SMTP authentication.                                                                   |
| spring.mail.properties.mail.smtp.starttls.enable   | true                                                         | Enables STARTTLS for secure communication.                                                     |
| spring.mail.properties.mail.debug                  | true                                                         | Enables debugging for email sending (useful for troubleshooting).                              |

### 4. Generating an Application-Specific Password for Gmail:
- If you are using Gmail for email sending, you must generate an application-specific password in your Google account. Here’s how:
- Log in to your Google account.
- Open the Google Account Management page.
- In the address bar of your browser, enter the following URL:
- **https://myaccount.google.com/apppasswords**
- Log in again if prompted.
- Select the application (e.g., "Mail") and device (e.g., "Windows computer") and click Generate.
- Copy the generated application password.
- Paste this password into the application.properties file in the spring.mail.password field.

### 5. Build and Run the Application:
- Once all the files are configured and values are set, go to the project’s root directory and build the application by running the following command in your terminal:
- **mvn clean package**

- After the build process is complete, run the application with the following command:
- **mvn spring-boot:run**
- The application should now be running locally on port 8080 (by default). To begin testing its features:
- In the Browser: Go to http://localhost:8080/api to check if the application is working properly.
- In Postman: Open Postman and test the various API endpoints. Remember, some endpoints require authentication, so you’ll need to add the JWT token in the request header:
- In Postman, go to the Authorization tab, choose Bearer Token, and paste your JWT token in the Token field.
- Now you can begin testing the application in Postman by making requests to the available endpoints.

### 6. Testing User Features:
- **Register a New User:**

If you don't have an account yet, you must first register a new user. After registration, you will be able to log in and get your JWT Bearer token.

User Registration Example
![User Registration](https://imgur.com/gykgK06)
*Screenshot showing the user registration process.*

- **Log In:**

After registering, log in with your user credentials. You will receive a JWT Bearer token.

User Login Example
![User Login](https://imgur.com/ObtgHOU)
*Screenshot showing the user login process.*

- **Include the Bearer Token:**

Important: Remember to include the Bearer token in the Authorization header of your requests where required to access the features of the application.

---

## 📊 Project Structure

**Controllers**
- **AuthenticationController:** Handles user login, registration, and authentication requests.
- **PropertyController**: Oversees property-related operations, including creating, updating, deleting, and retrieving properties. Supports advanced search filters for attributes like price, area, type, and room count.
- **CalculatorController**: Handles financial calculations related to real estate, such as loan eligibility, rental yields, deposit contributions, and proportional rental costs.
- **EmailController**: Provides endpoints for sending emails related to property notifications and user interactions, with email tracking features.
- **UserController**: Manages user-specific settings, such as configuring notification thresholds for price and area. Also supports user management operations like deletion (admin-only).


**DTOs (Data Transfer Objects)**
- **All DTOs, such as PropertyDto, EmailDto, UserDto, etc., used for transferring data between controllers and services.**

**Mappers - (Extension Functions in Kotlin)**
- **PropertyMapper:** Handles the conversion between Property entities and PropertyDto. It maps complex fields, including location, technical details, additional features, media, status, and financial details, to their respective DTOs.
- **LocationMapper:** Converts Location entities to LocationDto and vice versa, facilitating the transformation of location-related data such as address, city, province, postal code, and neighborhood.
- **TechnicalDetailsMapper:** Maps TechnicalDetails entities to TechnicalDetailsDto and vice versa, supporting fields like number of rooms, floor details, construction materials, and property condition.
- **AdditionalFeaturesMapper:** Converts AdditionalFeatures entities to AdditionalFeaturesDto and vice versa. It handles fields related to property amenities such as balconies, gardens, garages, elevators, and furnishings.
- **MediaMapper:** Maps Media entities to MediaDto, covering attributes like photos and video links associated with a property.
- **OfferStatusMapper:** Handles mapping between OfferStatus entities and OfferStatusDto, including fields like status, creation date, and last update date.
- **FinancialDetailsMapper:** Converts FinancialDetails entities to FinancialDetailsDto and vice versa. It manages financial data, including monthly rent, additional costs, and deposits.
- **CreatePropertyRequestMapper:** Maps CreatePropertyRequestDto to Property entities. It handles nested structures, converting related DTOs like location, technical details, additional features, media, status, and financial details to their corresponding entities.
- **UserMapper:** Converts User entities to UserDto or UserResponseDto, facilitating the transformation of user-related data such as email, name, and notification thresholds.
- **EmailMapper:** Maps Email entities to EmailDto, ensuring seamless conversion of email details like sender, recipient, subject, content, and sent date.

**Services**
- **AuthenticationService:** Handles login, registration, and authentication logic.
- **CalculatorService:** Handles financial calculations, including loan calculations, deposit contributions, rental yield analysis, and proportional rental costs. Provides insights into financial feasibility and investment potential.
- **EmailService:** Manages sending and receiving emails.
- **PropertyService:** Handles operations related to real estate properties, such as creating, updating, and retrieving property details. Supports advanced queries like finding properties by location, price range, and other attributes. Manages property notifications and calculates trends like average price per square meter and price evolution.
- **UserService:** Manages user-related operations, including user registration, setting notification thresholds for area and price, and role-based user management. Provides tools for account updates and user-specific property tracking.

**Repositories**
- **AdditionalFeaturesRepository:** Manages additional features data for properties.
- **EmailRepository:** Handles email-related database operations.
- **FinancialDetailsRepository:** Manages financial details for properties.
- **LocationRepository:** Handles location-related database operations for properties.
- **MediaRepository:** Manages media data for properties.
- **OfferStatusRepository:** Handles offer status data for properties.
- **PropertyRepository:** Manages property-related database operations.
- **RoleRepository:** Handles role-related database operations.
- **TechnicalDetailsRepository:** Manages technical details data for properties.
- **UserRepository:** Handles user-related database operations.

**Exceptions**
- **EmailSendingException:** Handles errors related to email sending failures.
- **PropertyNotFoundException:** Thrown when a property is not found.
- **RegistrationException:** Used for errors during user registration.
- **RoleNotFoundException:** Thrown when a role is not found.
- **UnauthorizedAccessException:** Handles unauthorized access errors.
- **UsernameNotFoundException:** Thrown when a username is not found.

**Security**
- **AuthenticationController:** Manages user login and registration processes.
- **CustomUserDetailsService:** A custom service for loading user details for authentication.
- **JwtAuthenticationFilter:** Filter for handling JWT-based authentication.
- **JwtUtil:** Utility for generating and validating JWT tokens.
- **SecurityConfig:** Configuration for Spring Security, including security policies and authentication mechanisms.

**Configuration**
- **application.properties:** Main application configuration file with database, server settings, etc.
- **liquibase.properties:** Configuration file for managing database changes.
- **liquibase.changelog:** Liquibase change log file containing database schema updates and migration scripts.

**Models**
- **AdditionalFeatures:** Entity for storing additional features of properties (e.g., balcony, garden, garage).
- **Email:** Entity for storing email details such as sender, recipient, subject, and content.
- **FinancialDetails:** Entity for managing financial details related to properties (e.g., rent, deposit).
- **Location:** Entity for managing location details (e.g., address, city, province).
- **Media:** Entity for storing property media (e.g., photos and videos).
- **OfferStatus:** Entity for managing the status of property offers (e.g., available, reserved, sold).
- **Property:** Entity for storing property details (e.g., title, description, price, type).
- **Role:** Entity for managing user roles (e.g., user, admin).
- **TechnicalDetails:** Entity for storing technical details of properties (e.g., number of rooms, heating).
- **User:** Entity for managing user information and authentication (e.g., email, password, roles).

**Swagger Documentation**
- **API documentation using Swagger for all controllers, providing API endpoints and descriptions.**

---

## 📸 Screenshots
Below you will find screenshots illustrating the functionalities of selected modules of the application. These are just a few examples—please refer to the full application details for a complete overview.

### User Registration:
![User Registration](https://imgur.com/gykgK06)
*Screenshot showing the user registration process.*

### User Login:
![User Login](https://imgur.com/ObtgHOU)
*Screenshot showing the user login process.*

### Property Creation:
![Property Creation](https://imgur.com/kI8xqEy)
*Screenshot showing the creation of a property.*

### Loan Calculation View:
![Loan Calculation View](https://imgur.com/iamraq7)
*Screenshot showing the results of the loan calculation.*

### Area Threshold Update View::
![Area Threshold Update View:](https://imgur.com/dlghKoe)
*Screenshot showing the update of the area threshold for notifications for the logged-in user.*

### Email Sending:
![Email Sending](https://imgur.com/BecvKGj)
![Delivered Email](https://imgur.com/uSyeK8r)
*Screenshot showing the email sending functionality in the application.*

---

## ⭐ Features Overview

### Authentication Management Endpoints
#### Available for Everybody:

🌐 POST: /api/auth/registration - registers a new user.

🌐 POST: /api/auth/login - sign in for an existing user.

### Property Management Endpoints
#### User Available:

👤 POST: /api/property - creates a new property listing.

👤 GET: /api/property/{id} - retrieves the details of a specific property by its ID.

👤 DELETE: /api/property/{id} - deletes a property by its ID.

👤 GET: /api/property - fetches all properties.

👤 PATCH: /api/property/{id} - partially updates a property by its ID.

👤 GET: /api/property/city?city={city} - fetches properties in a specified city.

👤 GET: /api/property/price?from={from}&to={to} - fetches properties within a specified price range.

👤 GET: /api/property/rooms?from={from}&to={to} - fetches properties with a specified number of rooms.

👤 GET: /api/property/area?from={from}&to={to} - fetches properties within a specified area range.

👤 GET: /api/property/type?type={type} - fetches properties of a specific type (e.g., APARTMENT, RESIDENCE, PLOT).

👤 POST: /api/property/favourites/{id} - adds a property to the user's favourites.

👤 GET: /api/property/favourites - retrieves all the user's favourite properties.

👤 GET: /api/property/filter?saleOrRent={saleOrRent} - filters properties by sale or rent status.

👤 GET: /api/property/average-price?province={province} - calculates the average price per square meter in a specified province.

👤 GET: /api/property/price-trend?startYear={startYear}&endYear={endYear}&province={province} - calculates the price trend over a time period in a specified province.

### User Management Endpoints
#### User Available:

👤 PUT: /api/user/area - sets the area threshold for notifications to receive property alerts based on area.

👤 PUT: /api/user/price - sets the price threshold for notifications to receive property alerts based on price.

#### Admin Available:

👤 DELETE: /api/user/{id} - deletes a user by their ID (admin access only).

### Loan Calculation Endpoints
#### User Available:

👤 POST: /api/calculator/loan-calculation - calculates the loan amount and monthly installment based on user input.

👤 POST: /api/calculator/loan-eligibility - calculates the maximum loan installment a user can afford based on their income and financial obligations.

👤 POST: /api/calculator/deposit-contribution - calculates the required deposit contribution (e.g., 20% of the property value) for a mortgage.

👤 POST: /api/calculator/calculate-rental-yield - calculates the rental yield based on the provided monthly rental income and property purchase price.

👤 POST: /api/calculator/proportional-rental-cost - calculates the rental cost per person based on shared space and total rent.

### Email Management Endpoints
#### User Available:

👤 POST: /api/email - allows an authenticated user to send an email with recipient, subject, and text.

---

## 🚨 Troubleshooting Email Sending Endpoint
- **If you encounter issues with the email sending endpoint, it is possible that your antivirus or firewall is blocking the connection to the Gmail SMTP server. In that case, try temporarily disabling your antivirus software or firewall and test again.**

- **Note: Some security software may block outgoing SMTP traffic, which can prevent email functionality from working properly. Disabling the antivirus or firewall can help resolve this issue during testing.**
