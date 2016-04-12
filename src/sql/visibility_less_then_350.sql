SELECT CAST(date as DATE) as only_date FROM table_av6 WHERE cloudiness <= 350 AND (date BETWEEN '2016-02-21' AND '2016-02-28') GROUP BY only_date ORDER BY only_date;

# SELECT date, rush FROM table_av6 WHERE rush >= 15 AND (date BETWEEN '2015-10-01' AND '2016-02-29');