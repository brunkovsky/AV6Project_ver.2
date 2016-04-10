package DBPackage;

// todo: bad example: open connection in constructor, use in one method, close in other

import MainPackage.Model;
import MainPackage.ModelExtended;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBWorker {
    private Connection connection;

    public DBWorker(String userNameBd, String passwordBd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AMSG?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8", userNameBd, passwordBd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillDB(List<Model> modelList) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS `table_av6`");
            statement.execute("CREATE TABLE `table_av6` (id INT(11) AUTO_INCREMENT PRIMARY KEY, date DATETIME, wind_direction_name VARCHAR(32), wind_speed INT, wind_rush INT, visibility INT, octants_numerator INT, octants_denominator INT, cloudForm VARCHAR(32), cloudiness INT, temperature DOUBLE, dew_point_temperature DOUBLE, relativity_humidity INT, absolute_humidity DOUBLE, atmosphere_pressure DOUBLE, barometric_trend DOUBLE, qnh_gpa DOUBLE, qnh_mm DOUBLE, qfe DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement pStatement;
        for (int i = 0; i < modelList.size(); i++) {
            String SOLFillQuery = "INSERT INTO table_av6(date, wind_direction_name, wind_speed, wind_rush, visibility, octants_numerator, octants_denominator, cloudForm, cloudiness, temperature, dew_point_temperature, relativity_humidity, absolute_humidity, atmosphere_pressure, barometric_trend, qnh_gpa, qnh_mm, qfe) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pStatement = connection.prepareStatement(SOLFillQuery);
                pStatement.setObject(1, new Timestamp(modelList.get(i).getDate().getTime()));
                pStatement.setString(2, modelList.get(i).getWindDirectionName());

                if(modelList.get(i).getWindSpeed() == null) {
                    pStatement.setNull(3, Types.INTEGER);
                } else {
                    pStatement.setInt(3, modelList.get(i).getWindSpeed());
                }

                if(modelList.get(i).getWindRush() == null) {
                    pStatement.setNull(4, Types.INTEGER);
                } else {
                    pStatement.setInt(4, modelList.get(i).getWindRush());
                }

                if(modelList.get(i).getVisibility() == null) {
                    pStatement.setNull(5, Types.INTEGER);
                } else {
                    pStatement.setInt(5, modelList.get(i).getVisibility());
                }

                if(modelList.get(i).getOctantsNumerator() == null) {
                    pStatement.setNull(6, Types.INTEGER);
                } else {
                    pStatement.setInt(6, modelList.get(i).getOctantsNumerator());
                }

                if(modelList.get(i).getOctantsDenominator() == null) {
                    pStatement.setNull(7, Types.INTEGER);
                } else {
                    pStatement.setInt(7, modelList.get(i).getOctantsDenominator());
                }

                pStatement.setString(8, modelList.get(i).getCloudForm());
                if(modelList.get(i).getCloudiness() == null) {
                    pStatement.setNull(9, Types.INTEGER);
                } else {
                    pStatement.setInt(9, modelList.get(i).getCloudiness());
                }

                if(modelList.get(i).getTemperature() == null) {
                    pStatement.setNull(10, Types.DOUBLE);
                } else {
                    pStatement.setDouble(10, modelList.get(i).getTemperature());
                }

                if(modelList.get(i).getDewPointTemperature() == null) {
                    pStatement.setNull(11, Types.DOUBLE);
                } else {
                    pStatement.setDouble(11, modelList.get(i).getDewPointTemperature());
                }

                if(modelList.get(i).getRelativityHumidity() == null) {
                    pStatement.setNull(12, Types.INTEGER);
                } else {
                    pStatement.setInt(12, modelList.get(i).getRelativityHumidity());
                }

                if(modelList.get(i).getAbsoluteHumidity() == null) {
                    pStatement.setNull(13, Types.DOUBLE);
                } else {
                    pStatement.setDouble(13, modelList.get(i).getAbsoluteHumidity());
                }

                if(modelList.get(i).getAtmospherePressure() == null) {
                    pStatement.setNull(14, Types.DOUBLE);
                } else {
                    pStatement.setDouble(14, modelList.get(i).getAtmospherePressure());
                }

                if(modelList.get(i).getBarometricTrend() == null) {
                    pStatement.setNull(15, Types.DOUBLE);
                } else {
                    pStatement.setDouble(15, modelList.get(i).getBarometricTrend());
                }

                if(modelList.get(i).getQnhGpa() == null) {
                    pStatement.setNull(16, Types.DOUBLE);
                } else {
                    pStatement.setDouble(16, modelList.get(i).getQnhGpa());
                }

                if(modelList.get(i).getQnhMm() == null) {
                    pStatement.setNull(17, Types.DOUBLE);
                } else {
                    pStatement.setDouble(17, modelList.get(i).getQnhMm());
                }

                if(modelList.get(i).getQfe() == null) {
                    pStatement.setNull(18, Types.DOUBLE);
                } else {
                    pStatement.setDouble(18, modelList.get(i).getQfe());
                }

                pStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void fillDBExtended(List<ModelExtended> modelExtendedList) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS `table_av6_extended`");
            String SQLCreateTable = "CREATE TABLE `table_av6_extended` (id INT(11) AUTO_INCREMENT PRIMARY KEY, date DATE, min_temp_air DOUBLE, min_temp_soil DOUBLE, max_temp_air DOUBLE, average_temp DOUBLE, min_2cm DOUBLE, precipitation_00 DOUBLE, precipitation_06 DOUBLE, precipitation_12 DOUBLE, precipitation_18 DOUBLE)";
            statement.execute(SQLCreateTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement pStatement;
        for (int i = 0; i < modelExtendedList.size(); i++) {
            String SOLFillQuery = "INSERT INTO table_av6_extended(date, min_temp_air, min_temp_soil, max_temp_air, average_temp, min_2cm, precipitation_00, precipitation_06, precipitation_12, precipitation_18) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
                pStatement = connection.prepareStatement(SOLFillQuery);
                Date date = modelExtendedList.get(i).getDate();
                pStatement.setDate(1, (new java.sql.Date(date.getTime())));

                if(modelExtendedList.get(i).getMinTempAir() == null) {
                    pStatement.setNull(2, Types.DOUBLE);
                } else {
                    pStatement.setDouble(2, modelExtendedList.get(i).getMinTempAir());
                }

                if(modelExtendedList.get(i).getMinTempSoil() == null) {
                    pStatement.setNull(3, Types.DOUBLE);
                } else {
                    pStatement.setDouble(3, modelExtendedList.get(i).getMinTempSoil());
                }

                if(modelExtendedList.get(i).getMaxTempAir() == null) {
                    pStatement.setNull(4, Types.DOUBLE);
                } else {
                    pStatement.setDouble(4, modelExtendedList.get(i).getMaxTempAir());
                }

                if(modelExtendedList.get(i).getAverageTemp() == null) {
                    pStatement.setNull(5, Types.DOUBLE);
                } else {
                    pStatement.setDouble(5, modelExtendedList.get(i).getAverageTemp());
                }

                if(modelExtendedList.get(i).getMin2cm() == null) {
                    pStatement.setNull(6, Types.DOUBLE);
                } else {
                    pStatement.setDouble(6, modelExtendedList.get(i).getMin2cm());
                }

                if(modelExtendedList.get(i).getPrecipitation00() == null) {
                    pStatement.setNull(7, Types.DOUBLE);
                } else {
                    pStatement.setDouble(7, modelExtendedList.get(i).getPrecipitation00());
                }

                if(modelExtendedList.get(i).getPrecipitation06() == null) {
                    pStatement.setNull(8, Types.DOUBLE);
                } else {
                    pStatement.setDouble(8, modelExtendedList.get(i).getPrecipitation06());
                }

                if(modelExtendedList.get(i).getPrecipitation12() == null) {
                    pStatement.setNull(9, Types.DOUBLE);
                } else {
                    pStatement.setDouble(9, modelExtendedList.get(i).getPrecipitation12());
                }

                if(modelExtendedList.get(i).getPrecipitation18() == null) {
                    pStatement.setNull(10, Types.DOUBLE);
                } else {
                    pStatement.setDouble(10, modelExtendedList.get(i).getPrecipitation18());
                }

                pStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
