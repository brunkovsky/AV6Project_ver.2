CREATE TABLE table_av6 (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  date DATETIME,
  wind_direction_name VARCHAR(32),
  wind_speed INT,
  wind_rush INT,
  visibility INT,
  octants_numerator INT,
  octants_denominator INT,
  cloudForm VARCHAR(32),
  cloudiness INT,
  temperature DOUBLE,
  dew_point_temperature DOUBLE,
  relativity_humidity INT,
  absolute_humidity DOUBLE,
  atmosphere_pressure DOUBLE,
  barometric_trend DOUBLE,
  qnh_gpa DOUBLE,
  qnh_mm DOUBLE,
  qfe DOUBLE
)