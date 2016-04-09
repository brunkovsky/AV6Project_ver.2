package FilePackage;

import ExcelPackage.SheetWorker;
import MainPackage.Model;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {

    private static File currentFile;
    private static final int DAYS_IN_WEEK = 7;

    public static List<Model> getModels(File file) {
        catchNullArgument(file);
        currentFile = file;
        return getModelsFromFile();
    }

    public static File getCurrentFile() {
        return currentFile;
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
