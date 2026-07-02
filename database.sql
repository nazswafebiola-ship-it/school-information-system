CREATE DATABASE IF NOT EXISTS school_system;
USE school_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL
);

INSERT INTO users (username, password, role) VALUES 
('admin1', 'admin123', 'ADMIN'),
('guru1', 'guru123', 'TEACHER'),
('siswa1', 'siswa123', 'STUDENT');