package FilePackage;

import ExcelPackage.SheetWorker;
import MainPackage.Model;
import MainPackage.ModelExtended;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileWorker {
    private static Date createFileDate;
    private static File currentFile;
    private static final int DAYS_IN_WEEK = 7;

    public static Date getCreateFileDate() {
        return createFileDate;
    }

    public static File getCurrentFile() {
        return currentFile;
    }

    public static List<Model> getModels(File file) {
        calculateInternalEntity(file);
        return getModelsFromFile();
    }

    public static List<ModelExtended> getModelsExtended(File file) {
        calculateInternalEntity(file);
        return getModelsExtendedFromFile();
    }

    private static void calculateInternalEntity(File file) {
        catchNullArgument(file);
        currentFile = file;
        createFileDate = extractDateFromFileName(file);
    }

    private static Date extractDateFromFileName(File file) {
        int year = Integer.parseInt(file.getName().substring(0, 4));
        int month = Integer.parseInt(file.getName().substring(4, 6));
        int day = Integer.parseInt(file.getName().substring(6, 8));
        return new Date(year - 1900, month - 1, day);
    }

    private static List<ModelExtended> getModelsExtendedFromFile() {
        List<ModelExtended> result = new ArrayList<>();
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(currentFile);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            assert wb != null;
            ModelExtended modelExtended = SheetWorker.getModelExtended(wb.getSheetAt(i));
            result.add(modelExtended);
        }
        return result;
    }

    private static List<Model> getModelsFromFile() {
        List<Model> result = new ArrayList<>();
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(currentFile);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            assert wb != null;
            List<Model> models;
            models = SheetWorker.getModels(wb.getSheetAt(i));
            result.addAll(models);
        }
        return result;
    }

    private static void catchNullArgument(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Can not get Models from null file");
        }
    }
}
