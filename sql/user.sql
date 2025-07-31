drop table if exists users CASCADE;
CREATE TABLE users (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) not null unique,
    password varchar(255) not null,
    email varchar(255) not null,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);