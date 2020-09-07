DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE PRODUCT (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL,description VARCHAR(100), quantity number(200)NOT NULL, price decimal(30,2) NOT NULL, discount decimal(30,2) NOT NULL);  

INSERT INTO PRODUCT (name, description, quantity, price,discount) VALUES
('APPLETON', 'Grounding Bushing With Bronze', 1, 35.75,5.0),
('HOFFMAN AHE12X10X4', 'Pull Box With Knockout', 5, 34.50,7.0),
('Sylvania', 'Fluorescent Lamp', 3, 1500.00, 8.0),
('AJC', 'Wheelchair Battery', 40, 1000.00, 9.0),
('Hubbell HBL2741', 'Wiring Device-Kellems Twist', 80, 450.45,15.67),
('Fasteners And Fittings ', 'STEEL S.A.E. WASHERS', 800, 1.762,75.86),
('Weidmuller Inc.', 'FEED THROUGH TERMINAL,W-SERIES SGL TIER', 700, 45000.00,89.56),
('General Cable Canada Ltd', 'CAT5E 4PR UNSH ARMORED FT4', 500, 300.00, 65.79),
('STEP STAKE SS-1 2 WIRE COROSTAKE', 'Recommended for corrugated', 1000, 500.00, 76.65);