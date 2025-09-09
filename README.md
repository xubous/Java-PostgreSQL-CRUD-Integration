# Java-PostgreSQL-CRUD-Integration
A Java application demonstrating basic CRUD (Create, Read, Update, Delete) operations integrated with a PostgreSQL database.

📋 Prerequisites

Before running this project, ensure you have installed:

    Java JDK 8 or higher

    PostgreSQL

    Eclipse IDE (optional, but recommended)

    Git

🚀 Getting Started
1. Clone the Repository
bash

git clone https://github.com/your-username/java-postgresql-crud.git
cd java-postgresql-crud

2. Database Setup

    Create a PostgreSQL database named Comidas

    Update the database connection settings in the Main.java file with your credentials

3. Configure Database Connection

Edit the database configuration in Main.java with your PostgreSQL credentials:
java

private static String user = "your_username";
private static String password = "your_password";
private static String url = "jdbc:postgresql://localhost:5432/Comidas";

4. Database Schema

Run the following SQL command to create the required table:
sql

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

5. Run the Application

Compile and run the application using your preferred method:

    Through Eclipse: Import as Java project and run Main.java

    Command line:
    bash

javac -cp .:postgresql-{version}.jar src/*.java
java -cp .:postgresql-{version}.jar src.Main

📁 Project Structure
text

src/
├── Main.java                 # Main application class
├── DAO/
│   └── Dao.java             # Data Access Object for CRUD operations
└── Product/
    └── Comida.java          # Product model class

🛠️ Features

    ✅ Create new food products

    ✅ Read/retrieve existing products

    ✅ Update existing product information

    ✅ Delete products

    ✅ List all products with formatted output

    ✅ Database connection management

    ✅ Exception handling

🔧 Technologies Used

    Java

    PostgreSQL

    JDBC (Java Database Connectivity)

📊 Entity Model

The Comida (Food) class represents products with the following attributes:

    id: Unique identifier

    quantidade: Quantity available

    valor: Product value/price

🎯 Usage Examples

The application demonstrates:

    Creating a new food product

    Updating an existing product

    Deleting a product

    Retrieving and displaying all products

🆕 New JavaSpark Framework Integration

The application now includes a web server component built with JavaSpark, a lightweight web framework that allows you to create web applications and REST APIs with minimal setup.
