DROP TABLE IF EXISTS phone_number CASCADE;
DROP TABLE IF EXISTS system_user CASCADE;

DROP SEQUENCE IF EXISTS hibernate_sequence; -- in order for @GeneratedValue(strategy = GenerationType.SEQUENCE) to work


CREATE TABLE phone_number 
(
  id SERIAL PRIMARY KEY,
  phone VARCHAR(25),
  created_date date,
  updated_date date,
  active_flag VARCHAR(1),
  version_number INTEGER
);

CREATE TABLE system_user 
(
  id SERIAL PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(50),
  created_date date,
  updated_date date,
  active_flag VARCHAR(1),
  version_number INTEGER
);


CREATE SEQUENCE hibernate_sequence START 10; -- in order for @GeneratedValue(strategy = GenerationType.SEQUENCE) to work