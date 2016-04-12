SELECT date, rush, visibility, cloudiness FROM table_av6 WHERE rush >= 15 AND (date BETWEEN '2015-10-01' AND '2016-02-29')
UNION
SELECT date, rush, visibility, cloudiness FROM table_av6 WHERE visibility < 1000 AND (date BETWEEN '2015-10-01' AND '2016-02-29')
UNION
SELECT date, rush, visibility, cloudiness FROM table_av6 WHERE cloudiness < 200 AND (date BETWEEN '2015-10-01' AND '2016-02-29') ORDER BY date;