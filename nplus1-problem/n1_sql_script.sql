CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    client_id INT
);

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

INSERT INTO payments (id, amount, client_id) VALUES (1, 120.50, 1);
INSERT INTO payments (id, amount, client_id) VALUES (2, 379.99, 5);
INSERT INTO payments (id, amount, client_id) VALUES (3, 765.20, 10);
INSERT INTO payments (id, amount, client_id) VALUES (4, 410.15, 2);
INSERT INTO payments (id, amount, client_id) VALUES (5, 999.99, 6);
INSERT INTO payments (id, amount, client_id) VALUES (6, 85.67, 8);
INSERT INTO payments (id, amount, client_id) VALUES (7, 250.00, 3);
INSERT INTO payments (id, amount, client_id) VALUES (8, 561.90, 1);
INSERT INTO payments (id, amount, client_id) VALUES (9, 887.25, 5);
INSERT INTO payments (id, amount, client_id) VALUES (10, 345.60, 9);
INSERT INTO payments (id, amount, client_id) VALUES (11, 728.40, 2);
INSERT INTO payments (id, amount, client_id) VALUES (12, 101.99, 4);
INSERT INTO payments (id, amount, client_id) VALUES (13, 630.75, 7);
INSERT INTO payments (id, amount, client_id) VALUES (14, 289.90, 3);
INSERT INTO payments (id, amount, client_id) VALUES (15, 446.00, 9);


INSERT INTO clients (id, name) VALUES (1, 'James');
INSERT INTO clients (id, name) VALUES (2, 'Alice');
INSERT INTO clients (id, name) VALUES (3, 'Ethan');
INSERT INTO clients (id, name) VALUES (4, 'Mia');
INSERT INTO clients (id, name) VALUES (5, 'Oliver');
INSERT INTO clients (id, name) VALUES (6, 'Harper');
INSERT INTO clients (id, name) VALUES (7, 'Liam');
INSERT INTO clients (id, name) VALUES (8, 'Chloe');
INSERT INTO clients (id, name) VALUES (9, 'Noah');
INSERT INTO clients (id, name) VALUES (10, 'Ava');