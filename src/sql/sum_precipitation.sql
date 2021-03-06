SELECT (COALESCE(SUM(precipitation_00), 0) +
        COALESCE(SUM(precipitation_06), 0) +
        COALESCE(SUM(precipitation_12), 0) +
        COALESCE(SUM(precipitation_18), 0))
  AS sum_precipitation
FROM table_av6_extended
WHERE date BETWEEN '2015-01-01' AND '2016-07-03';
