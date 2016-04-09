package OSSwitcherPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class OSDetector {
    private final static String PATH_TO_DATA_FOLDER;
    private final static String USER_NAME_BD;
    private final static String PASSWORD_BD;

    static {
        String path;
        String osType = System.getProperty("os.name");
        switch (osType) {
            case "Mac OS X":
                path = "mac.properties";
                break;
            case "Windows":
                path = "windows.properties";
                break;
            case "Windows 7":
                path = "windows.properties";
                break;
            case "Linux":
                path = "linux.properties";
                break;
            default:
                throw new RuntimeException("Error while execute 'switch case' block. Unsupported OS detected: " + osType);
        }
        Properties properties = new Properties();
        InputStream inputStream = OSDetector.class.getClassLoader().getResourceAsStream(path);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // NOP
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                // NOP
                e.printStackTrace();
            }
        }
        PATH_TO_DATA_FOLDER = properties.getProperty("PATH_TO_DATA_FOLDER");
        USER_NAME_BD = properties.getProperty("USER_NAME_BD");
        PASSWORD_BD = properties.getProperty("PASSWORD_BD");
    }

    public static String getPathToDataFolder() {
        return PATH_TO_DATA_FOLDER;
    }

    public static String getUserNameBd() {
        return USER_NAME_BD;
    }

    public static String getPasswordBd() {
        return PASSWORD_BD;
    }
}
