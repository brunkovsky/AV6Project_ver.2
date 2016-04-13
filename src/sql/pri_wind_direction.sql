SELECT wind_direction_name,
  COUNT(wind_direction_name)
    AS count
FROM table_av6
WHERE date BETWEEN '2015-01-01'
AND '2016-02-29'
GROUP BY wind_direction_name
ORDER BY count DESC;
