create database cognizant;
use cognizant;
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);
INSERT INTO Users (user_id,full_name, email, city, registration_date) VALUES
(1,'Alice Johnson', 'alice.smith@example.com', 'New York', '2024-12-01'),
(2,'Bob smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
(3,'Charlie Brown', 'charlie.b@example.com', 'New York', '2024-12-10'),
(4,'Diana Prince', 'diana.p@example.com', 'Chicago', '2025-01-15'), 
(5,'Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');
select *from Users;

