package MeteoUtilPackage;

import ExcelPackage.CellWorker;
import ExcelPackage.RowWorker;
import ExcelPackage.SheetWorker;
import FilePackage.FileWorker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.util.Date;

public class DataHandler {
    private static final int MAX_TIME = 23;
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
    private static final double MAX_QNH_GPA = 1040;
    private static final double MIN_QNH_MM = 700;
    private static final double MAX_QNH_MM = 800;
    private static final double MIN_QFE = 970;
    private static final double MAX_QFE = 1040;

    // Model's methods
    public static Integer getTime(XSSFRow row) {
        Integer time = getInteger(row, 0, 0);
        if (time != null && (time < 0 || time > MAX_TIME)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Time value in column 'A' is not valid. Equals: " + time);
        }
        return time;
    }

    public static String getWindDirectionName(XSSFRow row) {
        Integer windDirectionDegrees = getInteger(row, 1, 0);
        if (windDirectionDegrees != null && (windDirectionDegrees < 0 || windDirectionDegrees > MAX_DEGREES)) {
            throw new IllegalArgumentException("FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind Direction Degrees in column 'B' is not valid. Equals: " + windDirectionDegrees);
        }
        return DegreesWorker.getDirection(windDirectionDegrees);
    }

    public static Date getDate (XSSFRow row) {
        File file = FileWorker.getCurrentFile();
        XSSFSheet sheet = SheetWorker.getCurrentSheet();
        int year = Integer.parseInt(file.getName().substring(0, 4));
        int month = Integer.parseInt(file.getName().substring(4, 6));
        int day = Integer.parseInt(file.getName().substring(6, 8));
        int hour = row.getRowNum() - 3;
        long dayOfWeekMs = 0;
        String dayOfWeek = sheet.getSheetName();
        switch (dayOfWeek) {
            case "понедельник":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 7;
                break;
            case "вторник":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 6;
                break;
            case "среда":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 5;
                break;
            case "четверг":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 4;
                break;
            case "пятница":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 3;
                break;
            case "суббота":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 2;
                break;
            case "воскресенье":
                dayOfWeekMs = 1000 * 60 * 60 * 24;
                break;
        }
        Date date = new Date(year - 1900, month - 1, day, hour, 0);
        long dateMs = date.getTime();
        dateMs = dateMs - dayOfWeekMs;
        return new Date(dateMs);
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

    private static String getString(XSSFRow row, int columnNumber) {
        XSSFCell cell = row.getCell(columnNumber);
        return CellWorker.getString(cell);
    }

    private static Integer getInteger (XSSFRow row, int columnNumber, int numberInCell) {
        Integer timeResult;
        try {
            timeResult = CellWorker.getNInteger(row.getCell(columnNumber), numberInCell);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return timeResult;
    }

    private static Double getDouble (XSSFRow row, int columnNumber, int numberInCell) {
        Double timeResult;
        try {
            timeResult = CellWorker.getNDouble(row.getCell(columnNumber), numberInCell);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return timeResult;
    }

/*
    // ModelExtended's methods
    public static Double getMinAirTemperature(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 27, 3);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getMinEarthTemperature(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 28, 3);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getMaxAirTemperature(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 29, 3);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getAverageTemperature(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 30, 3);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getPrecipitation00h(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 27, 9);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getPrecipitation06h(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 28, 9);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getPrecipitation12h(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 29, 9);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }

    public static Double getPrecipitation18h(XSSFSheet sheet) {
        String stringFromCell = XLSXFieldWorkerUtil.getStringFromCell(path, sheet, 30, 9);
        Double qfeResult;
        try {
            List<Double> doubleList = XLSXFieldWorkerUtil.getDoublesFromString(stringFromCell);
            qfeResult = XLSXFieldWorkerUtil.getNDoubleFromDoubles(doubleList, 0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return qfeResult;
    }
    */

}
