package MainPackage;

import FilePackage.FilesWorker;
import OSSwitcherPackage.OSDetector;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File AV6Errors = new File("D:/AV6Errors.log");
        AV6Errors.delete();
        String pathToDataFolder = OSDetector.getPathToDataFolder();
        File[] files = FilesWorker.getFiles(pathToDataFolder);
        List<Model> models = FilesWorker.getModels(files);
        List<ModelExtended> modelsExtended = FilesWorker.getModelsExtended(files);
        if (AV6Errors.length() > 0) {
            System.out.println("Errors found. Log in D:/AV6Errors.log");
        } else {
            for (Model model :models) {
                System.out.println(model);
            }
            System.out.println();
            for (ModelExtended modelExtended :modelsExtended) {
                System.out.println(modelExtended);
            }
        }

    }
}
