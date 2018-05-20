DROP TABLE IF EXISTS Countries CASCADE;
DROP TABLE IF EXISTS Airports CASCADE;
DROP TABLE IF EXISTS Runways CASCADE;

CREATE TABLE Countries (
  id serial PRIMARY KEY,
  code varchar(2),
  name varchar(80),
  continent varchar(2),
  wikipedia_link varchar(80),
  keywords varchar(80)
);

CREATE TABLE Airports (
  id serial PRIMARY KEY,
  ident VARCHAR (10),
  type VARCHAR(80),
  name VARCHAR(80),
  latitude_deg FLOAT,
  longitude_deg FLOAT,
  elevation_ft FLOAT,
  continent VARCHAR(3),
  iso_country VARCHAR(10),
  iso_region VARCHAR(10),
  municipality VARCHAR(80),
  scheduled_service VARCHAR(80),
  gps_code VARCHAR(10),
  iata_code VARCHAR(10),
  local_code VARCHAR(10),
  home_link VARCHAR(180),
  wikipedia_link VARCHAR(180),
  keywords VARCHAR(200)
);

CREATE TABLE Runways (
  id serial PRIMARY KEY,
  airport_ref INT,
  airport_ident VARCHAR(10),
  length_ft FLOAT,
  width_ft FLOAT,
  surface VARCHAR(70),
  lighted INT,
  closed INT,
  le_ident VARCHAR(10),
  e_latitude_deg FLOAT,
  le_longitude_deg FLOAT,
  le_elevation_ft FLOAT,
  le_heading_degT FLOAT,
  le_displaced_threshold_ft FLOAT,
  he_ident VARCHAR(10),
  he_latitude_deg FLOAT,
  he_longitude_deg FLOAT,
  he_elevation_ft FLOAT,
  he_heading_degT FLOAT,
  he_displaced_threshold_ft FLOAT
)
