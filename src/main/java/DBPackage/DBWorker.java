package DBPackage;

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
            Model model = modelList.get(i);
            String SOLFillQuery = "INSERT INTO table_av6(date, wind_direction_name, wind_speed, wind_rush, visibility, octants_numerator, octants_denominator, cloudForm, cloudiness, temperature, dew_point_temperature, relativity_humidity, absolute_humidity, atmosphere_pressure, barometric_trend, qnh_gpa, qnh_mm, qfe) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pStatement = connection.prepareStatement(SOLFillQuery);
                pStatement.setObject(1, new Timestamp(model.getDate().getTime()));
                pStatement.setString(2, model.getWindDirectionName());
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getWindSpeed(), 3);
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getWindRush(), 4);
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getVisibility(), 5);
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getOctantsNumerator(), 6);
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getOctantsDenominator(), 7);
                pStatement.setString(8, model.getCloudForm());
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getCloudiness(), 9);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getTemperature(), 10);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getDewPointTemperature(), 11);
                SimplifierAddNull.setIntegerOrNull(pStatement, model.getRelativityHumidity(), 12);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getAbsoluteHumidity(), 13);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getAtmospherePressure(), 14);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getBarometricTrend(), 15);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getQnhGpa(), 16);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getQnhMm(), 17);
                SimplifierAddNull.setDoubleOrNull(pStatement, model.getQfe(), 18);
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
            ModelExtended modelExtended = modelExtendedList.get(i);
            String SOLFillQuery = "INSERT INTO table_av6_extended(date, min_temp_air, min_temp_soil, max_temp_air, average_temp, min_2cm, precipitation_00, precipitation_06, precipitation_12, precipitation_18) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
                pStatement = connection.prepareStatement(SOLFillQuery);
                Date date = modelExtendedList.get(i).getDate();
                pStatement.setDate(1, (new java.sql.Date(date.getTime())));
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getMinTempAir(), 2);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getMinTempSoil(), 3);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getMaxTempAir(), 4);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getAverageTemp(), 5);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getMin2cm(), 6);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getPrecipitation00(), 7);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getPrecipitation06(), 8);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getPrecipitation12(), 9);
                SimplifierAddNull.setDoubleOrNull(pStatement, modelExtended.getPrecipitation18(), 10);
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

    private static class SimplifierAddNull {
        private static void setIntegerOrNull(PreparedStatement preparedStatement, Integer value, int n) throws SQLException {
            if(value != null) {
                preparedStatement.setInt(n, value);
            }
            else
                preparedStatement.setNull(n, Types.INTEGER);
        }

        private static void setDoubleOrNull(PreparedStatement preparedStatement, Double value, int n) throws SQLException {
            if(value != null) {
                preparedStatement.setDouble(n, value);
            }
            else
                preparedStatement.setNull(n, Types.DOUBLE);
        }
    }
}
