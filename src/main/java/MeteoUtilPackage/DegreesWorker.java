package MeteoUtilPackage;

import java.util.HashMap;
import java.util.Map;

public class DegreesWorker {
    private static Map<Integer, String> directionMap = new HashMap<>();

    static {
        int count;
        for (count = 1; count < 22; count++)
            directionMap.put(count, "North");
        for (count = 22; count < 67; count++)
            directionMap.put(count, "North-East");
        for (count = 67; count < 112; count++)
            directionMap.put(count, "East");
        for (count = 112; count < 157; count++)
            directionMap.put(count, "South-East");
        for (count = 157; count < 202; count++)
            directionMap.put(count, "South");
        for (count = 202; count < 247; count++)
            directionMap.put(count, "South-West");
        for (count = 247; count < 292; count++)
            directionMap.put(count, "West");
        for (count = 292; count < 337; count++)
            directionMap.put(count, "North-West");
        for (count = 337; count <= 360; count++)
            directionMap.put(count, "North");
    }

    public static String getDirection(Integer degrees) {
        if (degrees == null) {
            return null;
        }
        return directionMap.get(degrees);
    }
}
