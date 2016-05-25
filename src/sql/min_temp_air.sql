SELECT date, min_temp_air
FROM table_av6_extended
WHERE min_temp_air=(SELECT MIN(min_temp_air)
                    FROM table_av6_extended
                    WHERE date BETWEEN '2015-01-01' AND '2015-02-01');