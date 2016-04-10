package ExcelPackage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellWorker {
    public static String getString(XSSFCell cell) {
        return cell == null ? "" : getStringFromCell(cell);
    }

    public static Double getNDouble(XSSFCell cell, int n) {
        String string = getString(cell);
        List<Double> doubles = getDoubles(string);
        return getNDouble(doubles, n);
    }

    public static Integer getNInteger(XSSFCell cell, int n) {
        return getNDouble(cell, n).intValue();
    }

    private static List<Double> getDoubles(String stringArg) {
        catchNullArgument(stringArg);
        if (stringArg.equals("")) {
            return new ArrayList<>();
        }
        return getDoublesFromString(stringArg);
    }

    private static Double getNDouble(List<Double> doubles, int n) {
        catchNullOrEmptyList(doubles);
        catchNegativeValue(n);
        return getNDoubleFromDoubles(doubles, n);
    }

    private static Double getNDoubleFromDoubles(List<Double> doubles, int n) {
        Double result;
        try {
            result = doubles.get(n);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Can not read element " + n + " from List<Doubles>: " + doubles + ", where length = " + doubles.size() + " ", e);
        }
        return result;
    }

    private static void catchNegativeValue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of element can not be negative. But n = " + n);
        }
    }

    private static void catchNullOrEmptyList(List<Double> doubles) {
        if (doubles == null || doubles.isEmpty()) {
            throw new IllegalArgumentException("Can not get Double from empty List<Double> or null. List<Double> = " + doubles);
        }
    }

    private static List<Double> getDoublesFromString(String stringArg) {
        List<Double> result = new ArrayList<>();
        stringArg = stringArg.replaceAll(",", ".");
        Pattern p = Pattern.compile("-?\\d+([.]\\d+)?");
        Matcher m = p.matcher(stringArg);
        while (m.find()) {
            Double currentParsingDouble;
            try {
                currentParsingDouble = Double.valueOf(m.group());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Can not convert String \"" + stringArg + "\" to Double");
            }
            result.add(currentParsingDouble);
        }
        return result;
    }

    private static void catchNullArgument(String stringArg) {
        if (stringArg == null) {
            throw new IllegalArgumentException("Can not get Doubles from null");
        }
    }

    private static String getStringFromCell(XSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return String.valueOf(cell.getCellFormula());
        }
        return "";
    }
}
