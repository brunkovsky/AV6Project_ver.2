package FilePackage;

import java.io.File;
import java.io.FileFilter;

public class ExtFilter implements FileFilter{
    private String ext;

    public ExtFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File pathname) {
        String extension = getExtension(pathname);
        return extension.equals(ext) && (getFirstSymbol(pathname) != '~');
    }

    private String getExtension(File pathname) {
        String filename = pathname.getName();
        int i = filename.lastIndexOf('.');
        return filename.substring(i + 1).toLowerCase();
    }

    private char getFirstSymbol(File pathname) {
        String filename = pathname.getName();
        return filename.charAt(0);
    }
}
