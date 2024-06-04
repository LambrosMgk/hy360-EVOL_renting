-- Create Database
CREATE DATABASE IF NOT EXISTS EVOL_Services
-- Create tables
CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    license_id INTEGER NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    birthdate DATE NOT NULL,
    address VARCHAR(30) NOT NULL,
    card_num VARCHAR(16) NOT NULL,
    card_CSV VARCHAR(3) NOT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE IF NOT EXISTS rental_forms (
    form_id INTEGER not NULL AUTO_INCREMENT,
	vehicle_id VARCHAR(7) not null,
    vehicle_type VARCHAR(15) not null,
    rent_duration DATE not null,
    total_cost FLOAT not null,
    payment_amount FLOAT not null,
    rental_date DATE not null,
    client_license_id INTEGER not null,
    client_fname VARCHAR(30) not null,
    client_lname VARCHAR(30) not null,
PRIMARY KEY (form_id)
);
CREATE TABLE IF NOT EXISTS cars (
    vehicle_id VARCHAR(7) not NULL unique,
    rent_cost FLOAT not null,
    vehicle_range FLOAT not null,
    insurance_cost FLOAT not null,
    color VARCHAR(16) not null,
    brand VARCHAR(16) not null,
    model VARCHAR(16) not null,
    rent_counter INTEGER not null,
    passanger_number INTEGER not null,
    car_type VARCHAR(16) not null,
PRIMARY KEY (vehicle_id)
);
CREATE TABLE IF NOT EXISTS motorcycles (
    vehicle_id VARCHAR(7) not NULL unique,
    rent_cost FLOAT not null,
    vehicle_range FLOAT not null,
    insurance_cost FLOAT not null,
    color VARCHAR(15) not null,
    brand VARCHAR(15) not null,
    model VARCHAR(15) not null,
    rent_counter INTEGER not null,
PRIMARY KEY (vehicle_id)
);
CREATE TABLE IF NOT EXISTS scooters (
    vehicle_id VARCHAR(7) not NULL unique,
    rent_cost FLOAT not null,
    vehicle_range FLOAT not null,
    insurance_cost FLOAT not null,
    color VARCHAR(15) not null,
    brand VARCHAR(15) not null,
    model VARCHAR(15) not null,
    rent_counter INTEGER not null,
PRIMARY KEY (vehicle_id)
);
CREATE TABLE IF NOT EXISTS bicycles (
    vehicle_id INTEGER not NULL AUTO_INCREMENT,
    rent_cost FLOAT not null,
    vehicle_range FLOAT not null,
    insurance_cost FLOAT not null,
    color VARCHAR(15) not null,
    brand VARCHAR(15) not null,
    model VARCHAR(15) not null,
    rent_counter INTEGER not null,
PRIMARY KEY (vehicle_id)
);

-- Insert 5 examples into each table
INSERT INTO users (firstname, lastname, license_id, password, birthdate, address, card_num, card_CSV)
VALUES ('user1', 'test1', 1, 'pass1', '1990-01-15', '123 Main St', '1234567890123456', '123');
INSERT INTO users (firstname, lastname, license_id, password, birthdate, address, card_num, card_CSV)
VALUES ('user2', 'test2', 2, 'pass2', '1990-01-15', '123 Main St', '1234567890123456', '123');
INSERT INTO users (firstname, lastname, license_id, password, birthdate, address, card_num, card_CSV)
VALUES ('user3', 'test3', 3, 'pass3', '1990-01-15', '123 Main St', '1234567890123456', '123');
INSERT INTO users (firstname, lastname, license_id, password, birthdate, address, card_num, card_CSV)
VALUES ('user4', 'test4', 4, 'pass4', '1990-01-15', '123 Main St', '1234567890123456', '123');
INSERT INTO users (firstname, lastname, license_id, password, birthdate, address, card_num, card_CSV)
VALUES ('user5', 'test5', 5, 'pass5', '1990-01-15', '123 Main St', '1234567890123456', '123');

INSERT INTO rental_forms (form_id,vehicle_id,vehicle_type,rent_duration,total_cost,payment_amount,rental_date,client_license_id,client_fname,client_lname)
VALUES (1, 'TZI4000', 'Car', '2024-2-1', 1000, 150.50, '2024-1-1', 1, 'User1', 'Test1');
INSERT INTO rental_forms (form_id,vehicle_id,vehicle_type,rent_duration,total_cost,payment_amount,rental_date,client_license_id,client_fname,client_lname)
VALUES (2, 'PIZ4000', 'Motorcycle', '2024-2-1', 1500, 165.50, '2024-1-1', 2, 'User2', 'Test2');
INSERT INTO rental_forms (form_id,vehicle_id,vehicle_type,rent_duration,total_cost,payment_amount,rental_date,client_license_id,client_fname,client_lname)
VALUES (3, 'PPI4187', 'Car', '2024-2-1', 2000, 273.45, '2024-1-1', 3, 'User3', 'Test3');
INSERT INTO rental_forms (form_id,vehicle_id,vehicle_type,rent_duration,total_cost,payment_amount,rental_date,client_license_id,client_fname,client_lname)
VALUES (4, 1, 'Scooter', '2024-2-1', 201, 201, '2024-1-1', 1, 'User1', 'Test1');
INSERT INTO rental_forms (form_id,vehicle_id,vehicle_type,rent_duration,total_cost,payment_amount,rental_date,client_license_id,client_fname,client_lname)
VALUES (5, 2, 'Bicycle', '2024-2-1', 120, 120, '2024-1-1', 2, 'User2', 'Test2');

INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type)
VALUES ('PPI4187', 213.5, 15000.5, 76.3, 'red', 'audi', 'q2', 4, 4, 'SUV');
INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type)
VALUES ('TZI4000', 313.5, 16000.5, 86.3, 'green', 'opel', 'astra', 2, 5, 'SUV');
INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type)
VALUES ('PTI4000', 413.5, 17000.5, 96.3, 'blue', 'fiat', 'pundo', 3, 4, 'MINI');
INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type)
VALUES ('ABC1234', 50.00, 400.0, 20.00, 'Blue', 'Toyota', 'Camry', 10, 5, 'FAMILY');
INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type)
VALUES ('XYZ5678', 60.00, 350.0, 25.00, 'Red', 'Honda', 'Civic', 8, 4, 'SPORT');

INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('PTT4187', 150.50, 10000, 200, 'black', 'Kawazaki', 'ninja', 4);
INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('PIZ4000', 155.50, 10000, 300, 'red', 'Hayabusa', 'XS', 3);
INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('PYS4000', 160.50, 10000, 150, 'silver', 'Kawazaki', 'ninja', 2);
INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('MOT1234', 50.00, 300.0, 20.00, 'Blue', 'Yamaha', 'R1', 5);
INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('MOT5678', 45.00, 280.0, 18.00, 'Black', 'Harley-Davidson', 'Softail', 3);

INSERT INTO scooters (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('1', 100.50, 5000, 100, 'black', 'Avanti', 'model1', 4);
INSERT INTO scooters (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('2', 105.50, 5000, 200, 'red', 'Biomega', 'model2', 3);
INSERT INTO scooters (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('3', 110.50, 5000, 50, 'silver', 'Dynacraft', 'model3', 2);
INSERT INTO scooters (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('4', 40.00, 200.0, 18.00, 'Green', 'Segway', 'Ninebot', 5);
INSERT INTO scooters (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES ('5', 30.00, 150.0, 15.00, 'Red', 'Honda', 'Activa', 8);

INSERT INTO bicycles (rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES (60, 100, 20, 'red', 'Avanti', 'BX', 1);
INSERT INTO bicycles (rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES (70, 100, 20, 'green', 'Biomega', 'mountain', 3);
INSERT INTO bicycles (rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES (55, 100, 20, 'blue', 'Dynacraft', 'city', 6);
INSERT INTO bicycles (rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES (10.00, 50.0, 5.00, 'blue', 'Schwinn', 'Explorer', 15);
INSERT INTO bicycles (rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter)
VALUES (15.00, 60.0, 7.50, 'red', 'Trek', 'X-Caliber', 10);