# SELECT date, MAX(wind_rush)
# FROM table_av6
# WHERE date BETWEEN '2016-02-01' AND '2016-02-29';

SELECT CAST(date AS DATE) AS only_date
FROM table_av6
WHERE wind_rush = (SELECT MAX(wind_rush)
       FROM table_av6
       WHERE date BETWEEN '2015-01-01' AND '2016-02-29') AND
      (date BETWEEN '2015-01-01' AND '2016-02-29')
