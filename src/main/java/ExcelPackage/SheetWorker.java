package ExcelPackage;

import MainPackage.Model;
import MainPackage.ModelExtended;
import MeteoUtilPackage.DataHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SheetWorker {
    private static final Logger log = Logger.getLogger(SheetWorker.class.getName());
    private static XSSFSheet currentSheet;
    private static final int FIRST_ROW_OF_DATA = 3;
    private static final int LAST_ROW_OF_DATA = 27;

    public static List<Model> getModels(XSSFSheet sheet) {
        catchNullArgument(sheet);
        currentSheet = sheet;
        return getModelsFromSheet();
    }

    public static ModelExtended getModelExtended(XSSFSheet sheet) {
        catchNullArgument(sheet);
        currentSheet = sheet;
        return getModelExtendedFromSheet();
    }

    private static ModelExtended getModelExtendedFromSheet() {
        Date date = DataHandler.getDate(currentSheet);

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

    public static XSSFSheet getCurrentSheet() {
        return currentSheet;
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
