package ExcelPackage;

import MainPackage.Model;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class SheetWorker {

    private static XSSFSheet currentSheet;
    private static final int FIRST_ROW_OF_DATA = 3;
    private static final int LAST_ROW_OF_DATA = 27;

    public static List<Model> getModels(XSSFSheet sheet) {
        catchNullArgument(sheet);
        currentSheet = sheet;
        return getModelsFromSheet();
    }

    public static XSSFSheet getCurrentSheet() {
        return currentSheet;
    }

    private static List<Model> getModelsFromSheet() {
        List<Model> result = new ArrayList<>();
        for (int i = FIRST_ROW_OF_DATA; i < LAST_ROW_OF_DATA; i++) {
            Model model;
            model = RowWorker.getModel(currentSheet.getRow(i));
//            if (model.getTime() != null && model.getTime() + FIRST_ROW_OF_DATA == i) {
                result.add(model);
//            } else {
//                throw new IllegalArgumentException("Invalid row in sheet = " + currentSheet.getSheetName() + ", #row = " + (i + 1) + ". Maybe not valid column 'A'");
//            }
        }
        return result;
    }

    private static void catchNullArgument(XSSFSheet sheet) {
        if (sheet == null) {
            throw new IllegalArgumentException("Can not get Models from null sheet");
        }
    }
}
