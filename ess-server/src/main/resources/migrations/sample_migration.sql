CREATE TABLE sample_migration
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);