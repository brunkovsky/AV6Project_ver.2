SELECT CAST(date AS DATE)
  AS only_date
FROM table_av6
WHERE cloudiness <= 350
      AND (date BETWEEN '2016-02-01' AND '2016-02-28')
GROUP BY only_date
ORDER BY only_date;
