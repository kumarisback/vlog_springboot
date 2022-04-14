

DROP TABLE IF EXISTS Profile;

CREATE TABLE Profile (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);


INSERT INTO Profile ( user, password,role, email)
VALUES ( 'Arun', '123456','ROLE_USER', 'arun@gmail.com');
INSERT INTO Profile ( user, password,role, email)
VALUES ( 'Avtar', '123456','ROLE_ADMIN', 'avtar@gmail.com');
