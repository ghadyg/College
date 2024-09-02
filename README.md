# Spring Boot React College Project

## Database Diagram

![Database Diagram](https://github.com/ghadyg/College/blob/main/Database%20diagram.png)

The database used is SQL SERVER and it is designed to manage user information for students and teachers, courses, and enrollments. 

It contains the following tables:

- **users**: Stores information about users such as their email, name, password, and role. The role field determines if the user is a student or teacher.

- **course_entity**: Holds the details of the courses, including name and description.

- **enrollment_entity:**: This table represents the enrollment records, linking students and teachers to specific courses. It includes fields like grade, course, student, and teacher.

- **teacher_courses:**: This field defines the relationship between teachers and courses. It includes the course_entity_id and teacher fields to track which teacher is assigned to which course.

## Backend using Spring Framework
This repository contains the source code for a Spring Boot application that provides the following features:

### Key Features

- **Signup and Login**: Secure user authentication and registration with role-based assignments (Student and Teacher).
  
- **Teacher's Functionality**:
  - Ability to choose a course to teach.
  - Ability to drop out of a course it is teaching
  - Grade students for their respective courses.

- **Student's Functionality**:
  - Ability to view all available courses and their corresponding teachers.
  - Ability to register and drop courses.
  - Ability to view grades for the registered courses.
  - Access to a dedicated API for course registration and viewing grades.

### Role-Based Access Control

Each API endpoint is secured using Spring Security to ensure that only users with the appropriate roles can access specific resources:

- **Student Role**:
  - Can access endpoints for viewing courses, registering for courses, and viewing grades.
  - Cannot access grading or course management endpoints meant for teachers.

- **Teacher Role**:
  - Can access endpoints for grading students and managing courses.
  - Cannot access student-specific endpoints like course registration.

### Security and Authorization

The application utilizes Spring Security to provide robust authorization and authentication mechanisms:

- JWT (JSON Web Tokens) are used for secure API access.
- Passwords are securely hashed using bcrypt.

### Getting Started
To get started with this Spring Boot application, follow these steps:

1. **Clone the Repository**:

   Clone this repository to your local machine using the following command:

   ```bash
   git clone https://github.com/ghadyg/College.git

2. **Navigate to the Backend Directory**:

   Navigate to the Backend Directory using the following command:

   ```bash
   cd College/backend
   
3. **Configure the Database**:

   a.Create a Database College.

   b.Open the application.properties file located in the src/main/resources directory and add your Microsoft SQL Server credentials (username and password):
      -spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=College
      -spring.datasource.username=your_username
      -spring.datasource.password=your_password

4. **Build and Run the Application**:

   Use Maven to build and run the application:

   ```bash
   mvn spring-boot:run


## Frontend using ReactJs Framework

## Description

### Components

- **Login/Login.jsx:** Handles user login functionality.
- **Signup/Signup.jsx:** Manages user registration.

### Context

- **AuthContext.jsx:** Provides authentication context for managing user sessions.

### Teacher

- **ManageCourses.jsx:** Allows teachers to view all available courses and choose/add courses they want to teach.
- **ManageGrade.jsx:** Enables teachers to view their students along with their enrolled courses and assign grades.

### Student

- **StudentManageCourses.jsx:**  Allows students to view all available courses, register for courses with a selected teacher, or drop a registered course.
- **StudentManageGrade.jsx:** Enables students to view their registered courses along with their respective teacher and their grades.


### Getting Started
To get started with this ReactJS Application, follow these steps:

1. **Clone the Repository**:

   Clone this repository to your local machine using the following command:

   ```bash
   git clone https://github.com/ghadyg/College.git

2. **Navigate to the Frontend Directory**:

   Navigate to the Backend Directory using the following command:

   ```bash
   cd College/frontend
   
3. **Install Dependencies:**:
   
   Install the necessary dependencies by running::

   ```bash
   npm install

5. **Start the React Application:**:

   Run the following command to start the ReactJS application:

   ```bash
   npm start

   This will start the development server, and you can access the application in your web browser at http://localhost:3000






