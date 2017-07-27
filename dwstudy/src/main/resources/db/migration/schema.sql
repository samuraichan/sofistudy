DROP TABLE IF EXISTS risk_header CASCADE;
DROP TABLE IF EXISTS status CASCADE;
DROP TABLE IF EXISTS risk_header_m CASCADE;
DROP TABLE IF EXISTS system_revision CASCADE;
DROP TABLE IF EXISTS automatic_risk_header CASCADE;
DROP SEQUENCE IF EXISTS hibernate_sequence; -- in order for @GeneratedValue(strategy = GenerationType.SEQUENCE) to work

CREATE TABLE risk_header 
(
  id SERIAL PRIMARY KEY,
  named_insured VARCHAR(100),
  status_id INTEGER,
  created_date date,
  active_flag VARCHAR(1),
  version_number INTEGER
);

CREATE TABLE risk_header_m
(
  risk_header_id SERIAL PRIMARY KEY, -- required by hibernate
  id INTEGER,
  named_insured VARCHAR(100),
  status_id INTEGER,
  created_date date,
  active_flag VARCHAR(1),
  version_number INTEGER,
  rev INTEGER, -- required by hibernate
  revtype SMALLINT -- required by hibernate
);

CREATE TABLE status 
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(25),
  description VARCHAR(50),
  active_flag VARCHAR(1),
  version_number INTEGER
);

CREATE TABLE automatic_risk_header
(
  id SERIAL PRIMARY KEY,
  risk_header_id INTEGER,
  automatic_xml XML,
  active_flag VARCHAR(1),
  version_number INTEGER
);


-- see StarterRevisioinEntity.java (hibernate provides a default REVINFO tale if 
-- @RevisionEntity is not provided by the app
CREATE TABLE system_revision
(
  id INTEGER PRIMARY KEY,
  timestamp BIGINT
);


CREATE SEQUENCE hibernate_sequence START 2000; -- in order for @GeneratedValue(strategy = GenerationType.SEQUENCE) to work