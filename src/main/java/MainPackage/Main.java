package MainPackage;

import DBPackage.DBWorker;
import FilePackage.FilesWorker;
import OSSwitcherPackage.OSDetector;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File AV6Errors = new File("/Volumes/Users/Stas/AV6Errors.log");
        AV6Errors.delete();
        String pathToDataFolder = OSDetector.getPathToDataFolder();
        File[] files = FilesWorker.getFiles(pathToDataFolder);
        List<Model> models = FilesWorker.getModels(files);
        List<ModelExtended> modelsExtended = FilesWorker.getModelsExtended(files);
        if (AV6Errors.length() > 0) {
            System.err.println("Errors found. Log in /Volumes/Users/Stas/AV6Errors.log");
        } else {
            DBWorker dbWorker = new DBWorker(OSDetector.getUserNameBd(), OSDetector.getPasswordBd());
            dbWorker.fillDB(models);
            dbWorker.fillDBExtended(modelsExtended);
        }
    }
}
