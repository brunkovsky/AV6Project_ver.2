package FilePackage;

import MainPackage.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesWorker {

    private static File[] currentFiles;

    public static File[] getFiles(String pathToFolder) {
        File myFolder = new File(pathToFolder);
        File[] files = myFolder.listFiles(new ExtFilter("xlsx"));
        Arrays.sort(files);
        return files;
    }

    public static List<Model> getModels(File[] files) {
        catchNullArgument(files);
        catchEmptyArgument(files);
        currentFiles = files;
        return getModelsFromFiles();
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
