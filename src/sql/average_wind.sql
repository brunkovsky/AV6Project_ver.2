SELECT ROUND(AVG(wind_speed)) AS `average wind speed`
FROM table_av6
WHERE date
BETWEEN '2015-01-01' AND '2016-01-01';
