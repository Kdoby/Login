CREATE TABLE users (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(50) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);