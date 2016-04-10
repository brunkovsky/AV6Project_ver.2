package FilePackage;

import MainPackage.Model;
import MainPackage.ModelExtended;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FilesWorker {
    private static final Logger log = Logger.getLogger(FilesWorker.class.getName());
    private static File[] currentFiles;

    public static File[] getFiles(String pathToFolder) {
        File myFolder = new File(pathToFolder);
        File[] files = myFolder.listFiles(new ExtFilter("xlsx"));
        Arrays.sort(files);
        checkAbsentFiles(files);
        return files;
    }

    public static List<Model> getModels(File[] files) {
        catchNullArgument(files);
        catchEmptyArgument(files);
        currentFiles = files;
        return getModelsFromFiles();
    }

    public static List<ModelExtended> getModelsExtended(File[] files) {
        catchNullArgument(files);
        catchEmptyArgument(files);
        currentFiles = files;
        return getModelsExtendedFromFiles();
    }

    private static List<ModelExtended> getModelsExtendedFromFiles() {
        List<ModelExtended> result = new ArrayList<>();
        for (File file :currentFiles) {
            List<ModelExtended> modelsExtended;
            modelsExtended = FileWorker.getModelsExtended(file);
            result.addAll(modelsExtended);
        }
        return result;
    }

    private static List<Model> getModelsFromFiles() {
        List<Model> result = new ArrayList<>();
        for (File file :currentFiles) {
            List<Model> models;
            models = FileWorker.getModels(file);
            result.addAll(models);
        }
        return result;
    }

    private static void checkAbsentFiles(File[] files) {
        for (int fileCount = 0; fileCount < files.length - 1; fileCount++) {
            String file1 = files[fileCount].getName();
            String file2 = files[fileCount + 1].getName();
            int year1 = Integer.parseInt(file1.substring(0, 4));
            int month1 = Integer.parseInt(file1.substring(4, 6));
            int day1 = Integer.parseInt(file1.substring(6, 8));
            int year2 = Integer.parseInt(file2.substring(0, 4));
            int month2 = Integer.parseInt(file2.substring(4, 6));
            int day2 = Integer.parseInt(file2.substring(6, 8));
            Date date1 = new Date(year1 - 1900, month1 - 1, day1);
            Date date2 = new Date(year2 - 1900, month2 - 1, day2);
            if (date2.getTime() - date1.getTime() > 700000000) {
                log.warn("Warning! file between " + file1 + " and " + file2 + " is absent");
            }
        }
    }

    private static void catchEmptyArgument(File[] files) {
        if (files.length == 0) {
            throw new IllegalArgumentException("Can not get Models from empty folder");
        }
    }

    private static void catchNullArgument(File[] files) {
        if (files == null) {
            throw new IllegalArgumentException("Can not get Models from null files");
        }
    }
}
