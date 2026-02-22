# 🔐 RevPasswordManager

A secure Java-based Password Manager built using **Java 24, MySQL, JDBC, Maven, Log4j, and JUnit**.

This project focuses on security-first design with AES encryption, OTP verification, hashed master passwords, and layered architecture.

---

## 🚀 Features

✅ User Registration & Login  
🔐 Master Password Protection  
🛡 AES Encrypted Password Vault  
🔍 Search Accounts  
✏ Update/Delete (OTP Protected)  
🎲 Strong Password Generator  
📜 Log4j Logging  
🧪 JUnit Testing  

---

## 🏗 Architecture (Layered Design)

UI Layer (Main)
↓
DAO Layer
↓
Utility Layer
↓
MySQL Database


---

## 🗄 Database Tables

### USERS
- id (PK)
- name
- email (unique)
- master_password

### PASSWORD_ENTRIES
- id (PK)
- user_id (FK)
- account_name
- username
- encrypted_password

### SECURITY_QUESTIONS
- id (PK)
- user_id (FK)
- question
- encrypted_answer

---

## ⚙ Tech Stack

| Technology | Usage |
|-----------|------|
| Java 24 | Core logic |
| MySQL | Database |
| JDBC | Connectivity |
| Maven | Build |
| Log4j | Logging |
| JUnit 5 | Testing |

---




