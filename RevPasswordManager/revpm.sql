-- ============================
-- DATABASE
-- ============================

CREATE DATABASE IF NOT EXISTS revpm;
USE revpm;

-- ============================
-- USERS TABLE
-- ============================

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    master_password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================
-- PASSWORD VAULT
-- ============================

CREATE TABLE password_entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_password_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- ============================
-- SECURITY QUESTIONS
-- ============================

CREATE TABLE security_questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    question VARCHAR(255) NOT NULL,
    encrypted_answer VARCHAR(255) NOT NULL,

    CONSTRAINT fk_security_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        
        ON DELETE CASCADE
);

-- ============================
-- PERFORMANCE INDEXES
-- ============================

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_password_account ON password_entries(account_name);
CREATE INDEX idx_password_user ON password_entries(user_id);
