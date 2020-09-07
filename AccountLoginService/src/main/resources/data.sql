DROP TABLE IF EXISTS USERS;

DROP TABLE IF EXISTS Authorities;

CREATE TABLE USERS ( id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(25) NOT NULL, password VARCHAR(100) NOT NULL, enabled boolean NOT NULL, roles VARCHAR(25));  

INSERT INTO USERS (username,password,enabled,roles) VALUES
('Anuj','$2y$12$zdzZgDKO5PloEwYB4Flgke926fWmIINjgarm6mZxKP/o/Ai7UP/.2','true','Admin'),
('Ashutosh', '$2y$12$pqabslBi8cRdLqqZXHgZo.BygKh/LBeHC5iwr5hcp4nJxkob89jPa', 'true','User');


