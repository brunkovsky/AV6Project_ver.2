package MeteoUtilPackage;

// todo: lock cells in excel file. leave only these, who may be edited and comment fields

import ExcelPackage.CellWorker;
import ExcelPackage.RowWorker;
import ExcelPackage.SheetWorker;
import FilePackage.FileWorker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DataHandler {
    private static final int MAX_DEGREES = 360;
    private static final int MAX_WIND_SPEED = 50;
    private static final int MIN_WIND_RUSH = 7;
    private static final int MAX_WIND_RUSH = 50;
    private static final int MAX_WIND_VISIBILITY = 10000;
    private static final int MAX_OCTANTS = 8;
    private static final int MAX_CLOUDINESS = 6000;
    private static final double MIN_TEMPERATURE = -50;
    private static final double MAX_TEMPERATURE = 50;
    private static final double MIN_DEW_POINT_TEMPERATURE= -50;
    private static final double MAX_DEW_POINT_TEMPERATURE = 50;
    private static final int MAX_RELATIVITY_HUMIDITY = 100;
    private static final double MAX_ABSOLUTE_HUMIDITY = 103.3;
    private static final double MIN_ATMOSPHERE_PRESSURE = 700;
    private static final double MAX_ATMOSPHERE_PRESSURE = 800;
    private static final double MIN_BAROMETRIC_TREND = -10;
    private static final double MAX_BAROMETRIC_TREND = 10;
    private static final double MIN_QNH_GPA = 970;
    private static final double MAX_QNH_GPA = 1050;
    private static final double MIN_QNH_MM = 700;
    private static final double MAX_QNH_MM = 800;
    private static final double MIN_QFE = 970;
    private static final double MAX_QFE = 1050;
    private static final double MAX_PRECIPITATION = 100;

    // Model's methods
    public static String getWindDirectionName(XSSFRow row) {
        Integer windDirectionDegrees = getInteger(row, 1, 0);
        if (windDirectionDegrees != null && (windDirectionDegrees < 0 || windDirectionDegrees > MAX_DEGREES)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind Direction Degrees in column 'B' is not valid. Equals: " + windDirectionDegrees);
        }
        return DegreesWorker.getDirection(windDirectionDegrees);
    }

    public static Integer getWindSpeed(XSSFRow row) {
        Integer windSpeed = getInteger(row, 2, 0);
        if (windSpeed != null && (windSpeed < 0 || windSpeed > MAX_WIND_SPEED)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind speed in column 'C' is not valid. Equals: " + windSpeed);
        }
        return windSpeed;
    }

    public static Integer getWindRush(XSSFRow row) {
        Integer windRush = getInteger(row, 3, 0);
        if (windRush != null && (windRush < MIN_WIND_RUSH || windRush > MAX_WIND_RUSH)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind rush in column 'D' is not valid. Equals: " + windRush);
        }
        return windRush;
    }

    public static Integer getVisibility(XSSFRow row) {
        Integer visibility;
        String stringFromCell = getString(row, 4).toLowerCase();
        stringFromCell = stringFromCell.replaceAll("k", "к");
        stringFromCell = stringFromCell.replaceAll("m", "м");
        Integer value = getInteger(row, 4, 0);
        if (value != null && stringFromCell.contains("км")) {
            visibility = value * 1000;
        } else {
            visibility = value;
        }
        if (visibility != null && (visibility < 0 || visibility > MAX_WIND_VISIBILITY)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Visibility in column 'E' is not valid. Equals: " + visibility);
        }
        return visibility;
    }

    public static Integer getOctantsNumerator(XSSFRow row) {
        Integer octantsNumerator = getInteger(row, 8, 0);
        if (octantsNumerator != null && (octantsNumerator < 0 || octantsNumerator > MAX_OCTANTS)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Octants Numerator in column 'I' is not valid. Equals: " + octantsNumerator);
        }
        return octantsNumerator;
    }

    public static Integer getOctantsDenominator(XSSFRow row) {
        Integer octantsDenominator = getInteger(row, 8, 1);
        if (octantsDenominator != null && (octantsDenominator < 0 || octantsDenominator > MAX_OCTANTS)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Octants Denominator in column 'I' is not valid. Equals: " + octantsDenominator);
        }
        return octantsDenominator;
    }

    public static String getCloudForm(XSSFRow row) {
        return getString(row, 9);
    }

    public static Integer getCloudiness(XSSFRow row) {
        Integer firstNumber = getInteger(row, 10, 0);
        Integer secondNumber = getInteger(row, 10, 1);
        Integer cloudiness;
        if (firstNumber != null && secondNumber != null) {
            cloudiness = firstNumber < secondNumber ? firstNumber : secondNumber;
        } else {
            cloudiness = firstNumber;
        }
        if (cloudiness != null && (cloudiness < 0 || cloudiness > MAX_CLOUDINESS)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Cloudiness in column 'K' is not valid. Equals: " + cloudiness);
        }
        return cloudiness;
    }

    public static Double getTemperature(XSSFRow row) {
        Double temperature = getDouble(row, 11, 0);
        if (temperature != null && (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Temperature in column 'L' is not valid. Equals: " + temperature);
        }
        return temperature;
    }

    public static Double getDewPointTemperature(XSSFRow row) {
        Double DewPointTemperature = getDouble(row, 12, 0);
        if (DewPointTemperature != null && (DewPointTemperature < MIN_DEW_POINT_TEMPERATURE || DewPointTemperature > MAX_DEW_POINT_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Dew point temperature in column 'M' is not valid. Equals: " + DewPointTemperature);
        }
        return DewPointTemperature;
    }

    public static Integer getRelativityHumidity(XSSFRow row) {
        Integer RelativityHumidity = getInteger(row, 13, 0);
        if (RelativityHumidity != null && (RelativityHumidity < 0 || RelativityHumidity > MAX_RELATIVITY_HUMIDITY)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Relativity humidity in column 'N' is not valid. Equals: ");
        }
        return RelativityHumidity;
    }

    public static Double getAbsoluteHumidity(XSSFRow row) {
        Double AbsoluteHumidity = getDouble(row, 14, 0);
        if (AbsoluteHumidity != null && (AbsoluteHumidity < 0 || AbsoluteHumidity > MAX_ABSOLUTE_HUMIDITY)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Absolute humidity in column 'O' is not valid. Equals: " + AbsoluteHumidity);
        }
        return AbsoluteHumidity;
    }

    public static Double getAtmospherePressure(XSSFRow row) {
        Double AtmospherePressure = getDouble(row, 15, 0);
        if (AtmospherePressure != null && (AtmospherePressure < MIN_ATMOSPHERE_PRESSURE || AtmospherePressure > MAX_ATMOSPHERE_PRESSURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Atmosphere pressure in column 'P' is not valid. Equals: " + AtmospherePressure);
        }
        return AtmospherePressure;
    }

    public static Double getBarometricTrend(XSSFRow row) {
        Double barometricTrend = getDouble(row, 16, 0);
        if (barometricTrend != null && (barometricTrend < MIN_BAROMETRIC_TREND || barometricTrend > MAX_BAROMETRIC_TREND)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Barometric trend in column 'Q' is not valid. Equals: " + barometricTrend);
        }
        return barometricTrend;
    }

    public static Double getQnhGpa(XSSFRow row) {
        Double QnhGpa = getDouble(row, 17, 0);
        if (QnhGpa != null && (QnhGpa < MIN_QNH_GPA || QnhGpa > MAX_QNH_GPA)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", QnhGpa in column 'R' is not valid. Equals: " + QnhGpa);
        }
        return QnhGpa;
    }

    public static Double getQnhMm(XSSFRow row) {
        Double QnhMm = getDouble(row, 18, 0);
        if (QnhMm != null && (QnhMm < MIN_QNH_MM || QnhMm > MAX_QNH_MM)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", QnhMm in column 'S' is not valid. Equals: " + QnhMm);
        }
        return QnhMm;
    }

    public static Double getQfe(XSSFRow row) {
        Double Qfe = getDouble(row, 19, 0);
        if (Qfe != null && (Qfe < MIN_QFE || Qfe > MAX_QFE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Qfe in column 'T' is not valid. Equals: " + Qfe);
        }
        return Qfe;
    }

    // ModelExtended's methods
    public static Double getMinTempAir(XSSFSheet sheet) {
        Double minTempAir = getDouble(sheet.getRow(27), 3, 0);
        if (minTempAir != null && (minTempAir < MIN_TEMPERATURE || minTempAir > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName() + ", MinTempAir. Equals: " + minTempAir);
        }
        return minTempAir;
    }

    public static Double getMinTempSoil(XSSFSheet sheet) {
        Double minTempSoil = getDouble(sheet.getRow(28), 3, 0);
        if (minTempSoil != null && (minTempSoil < MIN_TEMPERATURE || minTempSoil > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName() + ", MinTempSoil. Equals: " + minTempSoil);
        }
        return minTempSoil;
    }

    public static Double getMaxTempAir(XSSFSheet sheet) {
        Double maxTempAir = getDouble(sheet.getRow(29), 3, 0);
        if (maxTempAir != null && (maxTempAir < MIN_TEMPERATURE || maxTempAir > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName() + ", MaxTempAir. Equals: " + maxTempAir);
        }
        return maxTempAir;
    }

    public static Double getAverageTemp(XSSFSheet sheet) {
        Double averageTemp = getDouble(sheet.getRow(30), 3, 0);
        if (averageTemp != null && (averageTemp < MIN_TEMPERATURE || averageTemp > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", AverageTemp. Equals: " + averageTemp);
        }
        return averageTemp;
    }

    public static Double getMin2cm(XSSFSheet sheet) {
        Double min2cm = getDouble(sheet.getRow(31), 3, 0);
        if (min2cm != null && (min2cm < MIN_TEMPERATURE || min2cm > MAX_TEMPERATURE)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", Min2cm. Equals: " + min2cm);
        }
        return min2cm;
    }

    public static Double getPrecipitation00(XSSFSheet sheet) {
        Double precipitation00 = getDouble(sheet.getRow(27), 9, 0);
        if (precipitation00 != null && (precipitation00 < 0 || precipitation00 > MAX_PRECIPITATION)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", Precipitation00. Equals: " + precipitation00);
        }
        return precipitation00;
    }

    public static Double getPrecipitation06(XSSFSheet sheet) {
        Double precipitation06 = getDouble(sheet.getRow(28), 9, 0);
        if (precipitation06 != null && (precipitation06 < 0 || precipitation06 > MAX_PRECIPITATION)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", Precipitation06. Equals: " + precipitation06);
        }
        return precipitation06;
    }

    public static Double getPrecipitation12(XSSFSheet sheet) {
        Double precipitation12 = getDouble(sheet.getRow(27), 11, 0);
        if (precipitation12 != null && (precipitation12 < 0 || precipitation12 > MAX_PRECIPITATION)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", Precipitation12. Equals: " + precipitation12);
        }
        return precipitation12;
    }

    public static Double getPrecipitation18(XSSFSheet sheet) {
        Double precipitation18 = getDouble(sheet.getRow(28), 11, 0);
        if (precipitation18 != null && (precipitation18 < 0 || precipitation18 > MAX_PRECIPITATION)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", Precipitation18. Equals: " + precipitation18);
        }
        return precipitation18;
    }

    // common methods
    private static String getString(XSSFRow row, int columnNumber) {
        XSSFCell cell = row.getCell(columnNumber);
        return CellWorker.getString(cell);
    }

    private static Integer getInteger (XSSFRow row, int columnNumber, int numberInCell) {
        Integer result;
        try {
            result = CellWorker.getNInteger(row.getCell(columnNumber), numberInCell);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    private static Double getDouble (XSSFRow row, int columnNumber, int numberInCell) {
        Double result;
        try {
            result = CellWorker.getNDouble(row.getCell(columnNumber), numberInCell);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
