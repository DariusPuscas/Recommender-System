# 📱🖥️ Item Recommendation Application

📋 Project Overview:
This project is a full-stack Item Recommendation Application, designed to suggest various items to a user based on their past preferences. The application is developed with a Java backend using Hibernate for database interaction and Tomcat as the server, while the frontend is built using React.js. It connects to a SQL database to store and retrieve items and user data.

# ✨ Features
Personalized Recommendations: Users receive item recommendations based on their preferences.

Database Integration: The project uses an SQL database connected through Hibernate ORM for persistent data storage.

RESTful API: Built using Java Servlets for secure communication between the frontend and backend.

Deployed on Tomcat: The backend runs on an Apache Tomcat server.

User Authentication: Secure login system using JWT.

Role-based Authorization: Only authenticated users can access certain routes.

# 🏗️ Technologies Used
Frontend:

React (Hooks, Functional Components)

Material-UI (for design)


Backend:

Java Servlets

Hibernate ORM (SQL Database)

Tomcat (server)

JSON Web Tokens (JWT) for authentication

Database:

SQL (MySQL/PostgreSQL)

# 🚀 Getting Started
Prerequisites:

Ensure you have the following installed before running the project:

Java Development Kit (JDK)

Apache Tomcat

MySQL (or any SQL database)

Node.js

npm

# 🎨 Frontend Features

1)Login page

The login page is the initial screen where users are prompted to enter their email to receive personalized item recommendations.
![Screenshot 2024-09-12 182820](https://github.com/user-attachments/assets/c49b4d74-e273-4509-9a27-bc10185bf2a0)

2)Homepage

The homepage displays recommended items to the logged-in user, with 
pagination functionality and search capabilities. 
![image](https://github.com/user-attachments/assets/d2dc7d2a-29ce-4a6e-ae01-18f87942cebb)
Pagination: Users can navigate through different pages of items.

Search Bar: Allows users to search for specific items.

# 🗄️ Backend and API Structure
API Example:
The backend communicates using RESTful APIs. Below is an example of a JSON response from the /recommendations endpoint that provides recommendations based on the user's preferences.

| ![image](https://github.com/user-attachments/assets/8466bfb8-2069-4433-9c5a-05628217388f) | ![image](https://github.com/user-attachments/assets/da3c6e4c-e95b-4575-b36f-eccda3785bc5) |
|:------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------:|

Database Integration:

The backend is connected to a SQL database using Hibernate ORM.

Data is fetched from the database based on user preferences, and item ratings are displayed.
![image](https://github.com/user-attachments/assets/232198ce-7d95-4259-827f-779b54b90003)

# 🎨 Project Improvements
Potential future improvements include:

User Registration: Add the ability for new users to register.

Admin Role: Implement a role-based system, with separate functionality for administrators.

UI/UX Improvements: Enhance the frontend to make it more visually appealing and user-friendly.
