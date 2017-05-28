CREAtE DATABASE store;
USE store;
CREATE TABLE clients (id INT AUTO_INCREMENT, name VARCHAR(60), surname VARCHAR(60), PRIMARY KEY(id));
INSERT INTO clients (name, surname) VALUES ('Ольга', 'Иванова');