SELECT MAX(wind_rush) FROM table_av6 WHERE date BETWEEN '2016-02-21' AND '2016-06-21';

# SELECT wind_direction_name, COUNT(*) as how_many FROM table_av6 WHERE date BETWEEN '2015-00-01' AND '2015-12-31'  GROUP BY wind_direction_name ORDER BY how_many DESC;