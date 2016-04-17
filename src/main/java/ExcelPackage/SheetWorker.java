package ExcelPackage;

import FilePackage.FileWorker;
import MainPackage.Model;
import MainPackage.ModelExtended;
import MeteoUtilPackage.DataHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SheetWorker {
    private static Date createSheetDate;
    private static final Logger log = Logger.getLogger(SheetWorker.class.getName());
    private static XSSFSheet currentSheet;
    private static final int FIRST_ROW_OF_DATA = 3;
    private static final int LAST_ROW_OF_DATA = 27;

    public static Date getCreateSheetDate() {
        return createSheetDate;
    }

    public static XSSFSheet getCurrentSheet() {
        return currentSheet;
    }

    public static List<Model> getModels(XSSFSheet sheet) {
        calculateInternalEntity(sheet);
        return getModelsFromSheet();
    }

    public static ModelExtended getModelExtended(XSSFSheet sheet) {
        calculateInternalEntity(sheet);
        checkModelExtendedHeaders();
        return getModelExtendedFromSheet();
    }

    private static void checkModelExtendedHeaders() {
        try {
            DataHandler.checkAB28Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkAB29Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkAB30Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkAB31Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkI28Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkI29Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkK28Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        try {
            DataHandler.checkK29Cell(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
    }

    private static void calculateInternalEntity(XSSFSheet sheet) {
        catchNullArgument(sheet);
        currentSheet = sheet;
        createSheetDate = calculateSheetDate();
    }

    private static Date calculateSheetDate() {
        long dayOfWeekMs = 0;
        String dayOfWeek = currentSheet.getSheetName();
        Date date = FileWorker.getCreateFileDate();
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
        long dateMs = date.getTime();
        dateMs = dateMs - dayOfWeekMs;
        return new Date(dateMs);
    }

    private static ModelExtended getModelExtendedFromSheet() {
        Date date = calculateSheetDate();

        Double minTempAir = null;
        try {
            minTempAir = DataHandler.getMinTempAir(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double minTempSoil = null;
        try {
            minTempSoil = DataHandler.getMinTempSoil(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double maxTempAir = null;
        try {
            maxTempAir = DataHandler.getMaxTempAir(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double averageTempAir = null;
        try {
            averageTempAir = DataHandler.getAverageTemp(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double min2cm = null;
        try {
            min2cm = DataHandler.getMin2cm(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double Precipitation00 = null;
        try {
            Precipitation00 = DataHandler.getPrecipitation00(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double Precipitation06 = null;
        try {
            Precipitation06 = DataHandler.getPrecipitation06(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double Precipitation12 = null;
        try {
            Precipitation12 = DataHandler.getPrecipitation12(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        Double Precipitation18 = null;
        try {
            Precipitation18 = DataHandler.getPrecipitation18(currentSheet);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        return new ModelExtended(date, minTempAir, minTempSoil, maxTempAir, averageTempAir, min2cm, Precipitation00, Precipitation06, Precipitation12, Precipitation18);
    }

    private static List<Model> getModelsFromSheet() {
        List<Model> result = new ArrayList<>();
        for (int i = FIRST_ROW_OF_DATA; i < LAST_ROW_OF_DATA; i++) {
            Model model;
            model = RowWorker.getModel(currentSheet.getRow(i));
            result.add(model);
        }
        return result;
    }

    private static void catchNullArgument(XSSFSheet sheet) {
        if (sheet == null) {
            throw new IllegalArgumentException("Can not get Models from null sheet");
        }
    }
}
