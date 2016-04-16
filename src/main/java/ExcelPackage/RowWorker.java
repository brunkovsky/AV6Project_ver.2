package ExcelPackage;

import MainPackage.Model;
import MeteoUtilPackage.DataHandler;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Date;

public class RowWorker {
    private static final Logger log = Logger.getLogger(RowWorker.class.getName());
    private static XSSFRow currentRow;
    private static Date currentRowTime;

    public static XSSFRow getCurrentRow() {
        return currentRow;
    }

    public static Date getCurrentRowTime() {
        return currentRowTime;
    }

    public static Model getModel(XSSFRow row) {
        catchNullArgument(row);
        currentRow = row;
        currentRowTime = calculateRowTime();
        return getModelFromRow();
    }

    private static Date calculateRowTime() {
        Date createSheetDate = SheetWorker.getCreateSheetDate();
        long longDate = createSheetDate.getTime();
        longDate = longDate - (3 - currentRow.getRowNum()) * 1000 * 60 * 60;
        System.out.println(new Date(longDate));
        return new Date(longDate);
    }

    private static Model getModelFromRow() {
        Date date = getCurrentRowTime();

        String windDirectionName = null;
        try {
            windDirectionName = DataHandler.getWindDirectionName(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer windSpeed = null;
        try {
            windSpeed = DataHandler.getWindSpeed(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer windRush = null;
        try {
            windRush = DataHandler.getWindRush(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer visibility = null;
        try {
            visibility = DataHandler.getVisibility(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer octantsNumerator = null;
        try {
            octantsNumerator = DataHandler.getOctantsNumerator(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer octantsDenominator = null;
        try {
            octantsDenominator = DataHandler.getOctantsDenominator(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        String cloudForm = null;
        try {
            cloudForm = DataHandler.getCloudForm(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer cloudiness = null;
        try {
            cloudiness = DataHandler.getCloudiness(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double temperature = null;
        try {
            temperature = DataHandler.getTemperature(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double dewPointTemperature = null;
        try {
            dewPointTemperature = DataHandler.getDewPointTemperature(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Integer relativityHumidity = null;
        try {
            relativityHumidity = DataHandler.getRelativityHumidity(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double absoluteHumidity = null;
        try {
            absoluteHumidity = DataHandler.getAbsoluteHumidity(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double atmospherePressure = null;
        try {
            atmospherePressure = DataHandler.getAtmospherePressure(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double barometricTrend = null;
        try {
            barometricTrend = DataHandler.getBarometricTrend(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double qnhGpa = null;
        try {
            qnhGpa = DataHandler.getQnhGpa(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double qnhMm = null;
        try {
            qnhMm = DataHandler.getQnhMm(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double qfe = null;
        try {
            qfe = DataHandler.getQfe(currentRow);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        return new Model(date, windDirectionName, windSpeed, windRush, visibility, octantsNumerator, octantsDenominator, cloudForm, cloudiness, temperature, dewPointTemperature, relativityHumidity, absoluteHumidity, atmospherePressure, barometricTrend, qnhGpa, qnhMm, qfe);
    }

    private static void catchNullArgument(XSSFRow row) {
        if (row == null) {
            throw new IllegalArgumentException("Can not get Model from null row");
        }
    }
}
