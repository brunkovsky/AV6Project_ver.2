CREATE TABLE table_av6_extended (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  date DATE,
  min_temp_air DOUBLE,
  min_temp_soil DOUBLE,
  max_temp_air DOUBLE,
  average_temp DOUBLE,
  min_2cm DOUBLE,
  precipitation_00 DOUBLE,
  precipitation_06 DOUBLE,
  precipitation_12 DOUBLE,
  precipitation_18 DOUBLE
)