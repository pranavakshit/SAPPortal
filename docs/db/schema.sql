#DROP DATABASE records;

CREATE DATABASE IF NOT EXISTS records;

USE records;

CREATE TABLE IF NOT EXISTS student (
    name VARCHAR(255),
    sap INT PRIMARY KEY,
    contact VARCHAR(15)
);
#DROP TABLE student;
#TRUNCATE TABLE student;
