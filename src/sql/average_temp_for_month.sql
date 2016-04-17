SELECT ROUND(AVG(temperature), 1) AS `average temperarute`
FROM table_av6
WHERE date
BETWEEN '2015-01-01' AND '2016-02-31';
