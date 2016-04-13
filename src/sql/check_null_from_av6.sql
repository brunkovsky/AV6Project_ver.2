SELECT CAST(date AS DATE)
  AS null_in_av6
FROM table_av6
WHERE wind_direction_name IS NULL
      AND wind_speed IS NULL
      AND wind_rush IS NULL
      AND visibility IS NULL
      AND octants_numerator IS NULL
      AND octants_denominator IS NULL
      AND cloudiness IS NULL
      AND temperature IS NULL
      AND dew_point_temperature IS NULL
      AND relativity_humidity IS NULL
      AND absolute_humidity IS NULL
      AND atmosphere_pressure IS NULL
      AND barometric_trend IS NULL
      AND qnh_gpa IS NULL
      AND qnh_mm IS NULL
      AND qfe IS NULL
  GROUP BY null_in_av6
ORDER BY null_in_av6;
