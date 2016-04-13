SELECT CAST(date AS DATE)
  AS null_in_av6_extended
FROM table_av6_extended
WHERE min_temp_air IS NULL
      AND min_temp_soil IS NULL
      AND max_temp_air IS NULL
      AND average_temp IS NULL
      AND min_2cm IS NULL
      AND precipitation_00 IS NULL
      AND precipitation_06 IS NULL
      AND precipitation_12 IS NULL
      AND precipitation_18 IS NULL
  GROUP BY null_in_av6_extended
ORDER BY null_in_av6_extended;
