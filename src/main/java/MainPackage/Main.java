package MainPackage;

import DBPackage.DBWorker;
import FilePackage.FilesWorker;
import OSSwitcherPackage.OSDetector;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File AV6Errors = new File("C:/Program Files/JetBrains/AV6Errors.log");
        if (AV6Errors.delete()) {
            System.out.println("AV6Errors.log deleted");
        }
        String pathToDataFolder = OSDetector.getPathToDataFolder();
        File[] files = FilesWorker.getFiles(pathToDataFolder);
        List<Model> models = FilesWorker.getModels(files);
        System.out.println("List<Model> models creation complete");
        AdequateCheck.checkModels(models);
        List<ModelExtended> modelsExtended = FilesWorker.getModelsExtended(files);
        System.out.println("List<ModelExtended> modelsExtended creation complete");
        if (AV6Errors.length() > 0) {
            System.out.println("Errors found. LogFile in C:/Program Files/JetBrains/AV6Errors.log");
        } else {
            DBWorker dbWorkerForModels = new DBWorker(OSDetector.getUserNameBd(), OSDetector.getPasswordBd());
            DBWorker dbWorkerForModelsExtended = new DBWorker(OSDetector.getUserNameBd(), OSDetector.getPasswordBd());
            System.out.println("dbWorkers enabled");
            dbWorkerForModels.fillDB(models);
            System.out.println("models in database");
            dbWorkerForModelsExtended.fillDBExtended(modelsExtended);
            System.out.println("modelsExtended in database");
            System.out.println("work complete");
        }
    }
}
