SELECT CAST(date as DATE) as only_date
FROM table_av6
WHERE wind_direction_name is NULL
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
  GROUP BY only_date
ORDER BY only_date;
