
CREATE TABLE employees (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100),
  document VARCHAR(11),
  birthdate DATE,
  postal_code VARCHAR(50),
  address VARCHAR(255),
  address_number VARCHAR(10),
  city VARCHAR(50),
  state VARCHAR(50),
  country VARCHAR(50)
);

CREATE TABLE customers (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100),
  document VARCHAR(11),
  postal_code VARCHAR(50),
  address VARCHAR(255),
  address_number VARCHAR(10),
  city VARCHAR(50),
  state VARCHAR(50),
  country VARCHAR(50)
);