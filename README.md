# Rev Password Manager

**Rev Password Manager** is a Command Line Interface (CLI) based password manager application written in Java 24. It provides a secure and centralized location to store, manage, and generate passwords for your online accounts.

---

## 🚀 Features

*   **User Management**
    *   **Secure Registration**: Create a new account with your name, email, and master password.
    *   **Secure Login**: Authenticate with your master password (which is securely hashed).
*   **Password Vault**
    *   **Add Passwords**: Store credentials with Account Name, Username, and Password (encrypted using AES).
    *   **View Vault**: List all stored accounts and decrypted passwords to quickly retrieve them.
    *   **Search**: Search for specific account credentials by name.
*   **Security Features**
    *   **Sensitive Action Barrier**: Prompt for Master Password re-verification before performing sensitive dashboard actions (Add Password, View Vault, Generate Password).
    *   **Strong Password Validator**: Enforces password requirements to prevent weak master passwords.
*   **Password Generator**
    *   Generate strong, random passwords of any length you choose.

---

## 🛠️ Tech Stack

*   **Language**: [Java 24](https://www.oracle.com/java/technologies/)
*   **Database**: [MySQL](https://www.mysql.com/)
*   **Logging**: [Log4j 2](https://logging.apache.org/log4j/2.x/)
*   **Testing**: [JUnit 5](https://junit.org/junit5/)
*   **Security**: AES Encryption, SHA-based hashing (via custom utils).
*   **Build Tool**: Maven

---

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

1.  **Java 24** or higher.
2.  **MySQL Server** (running locally or remotely).
3.  **Maven** (for managing dependencies and building).

---

## 🗄️ Database Setup

To run the application, you need to create the database and tables that match the application logic.

### 1. Create the Database

Log in to your MySQL terminal and run:

```sql
CREATE DATABASE revpm;
```

### 2. Create Tables

Execute the following script to create the required tables:

```sql
USE revpm;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    master_password VARCHAR(255) NOT NULL
);

CREATE TABLE password_entries (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    encrypted_password TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Optional/Extended tables for OTP and Security Questions
CREATE TABLE otp_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    otp_code VARCHAR(10),
    expiry_time TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE security_questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    question VARCHAR(255),
    encrypted_answer VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

---

## ⚙️ Configuration

Check the database connection details in the source code file:
`src/main/java/com/revpm/config/DBConnection.java`

Configure your application to connect to:
*   **URL**: `jdbc:mysql://localhost:3306/revpm`
*   **Username**: `YOUR_USERNAME`
*   **Password**: `YOUR_PASSWORD`

You may need to update these values to match your local MySQL configuration.

---

## 🏃‍♂️ How to Run

1.  **Clone or Open the Project**:
    Ensure you are in the project root directory (`RevPasswordManager`).

2.  **Build the Project**:
    Compile the Java classes using Maven:
    ```bash
    mvn clean compile
    ```

3.  **Run the Application**:
    Execute the main class using the `exec-maven-plugin`:
    ```bash
    mvn exec:java -Dexec.mainClass="com.revpm.Main"
    ```

---

## 📁 Project Structure

```text
RevPasswordManager
├── src/main/java/com/revpm
│   ├── Main.java               # Application Entry Point
│   ├── config/DBConnection     # Database Connection Logic
│   ├── dao                     # Data Access Objects (User, Password, etc.)
│   └── util                    # Utility Classes (AES, Encryption, Validation, etc.)
├── src/main/resources
│   └── log4j2.xml              # Logger Configuration
└── pom.xml                     # Maven Dependencies & Plugins
```
