package MainPackage;

import java.util.List;

public class AdequateCheck {
    private static final int THRESHOLD_ADEQUATE_WIND_SPEED_DIFFERENCE = 10;
    private static final int THRESHOLD_ADEQUATE_WIND_RUSH_DIFFERENCE = 20;
    private static final double THRESHOLD_ADEQUATE_TEMPERATURE = 5.0;
    private static final int THRESHOLD_ADEQUATE_RELATIVITY_HUMIDITY = 25;
    private static final double THRESHOLD_ADEQUATE_ABSOLUTE_HUMIDITY = 20.0;

    public static void checkModels(List<Model> models) {
        for (int i = 1; i < models.size(); i++) {
            checkAdequateWindSpeedDifference(models, i);
            checkAdequateWindRushDifference(models, i);
            checkAdequateTemperature(models, i);
            checkAdequateDewPointTemperature(models, i);
            checkAdequateRelativityHumidity(models, i);
            checkAdequateAbsoluteHumidity(models, i);
        }
    }

    private static void checkAdequateWindSpeedDifference(List<Model> models, int i) {
        int currentWindSpeed = models.get(i).getWindSpeed() == null ? 0 : models.get(i).getWindSpeed();
        int previousWindSpeed = models.get(i - 1).getWindSpeed() == null ? 0 : models.get(i - 1).getWindSpeed();
        if (Math.abs(currentWindSpeed - previousWindSpeed) > THRESHOLD_ADEQUATE_WIND_SPEED_DIFFERENCE) {
            if (currentWindSpeed != 0.0 && previousWindSpeed != 0.0) {
                System.out.println("Wind Speed Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousWindSpeed + " to " + currentWindSpeed);
            }
        }
    }

    private static void checkAdequateWindRushDifference(List<Model> models, int i) {
        int currentWindRush = models.get(i).getWindRush() == null ? 0 : models.get(i).getWindRush();
        int previousWindRush = models.get(i - 1).getWindRush() == null ? 0 : models.get(i - 1).getWindRush();
        if (Math.abs(currentWindRush - previousWindRush) > THRESHOLD_ADEQUATE_WIND_RUSH_DIFFERENCE) {
            if (currentWindRush != 0.0 && previousWindRush != 0.0) {
                System.out.println("Wind Rush Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousWindRush + " to " + currentWindRush);
            }
        }
    }

    private static void checkAdequateTemperature(List<Model> models, int i) {
        double currentTemperature = models.get(i).getTemperature() == null ? 0 : models.get(i).getTemperature();
        double previousTemperature = models.get(i - 1).getTemperature() == null ? 0 : models.get(i - 1).getTemperature();
        if (Math.abs(currentTemperature - previousTemperature) > THRESHOLD_ADEQUATE_TEMPERATURE) {
            if (currentTemperature != 0.0 && previousTemperature != 0.0) {
                System.out.println("Temperature Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousTemperature + " to " + currentTemperature);
            }
        }
    }

    private static void checkAdequateDewPointTemperature(List<Model> models, int i) {
        double currentTemperature = models.get(i).getDewPointTemperature() == null ? 0 : models.get(i).getDewPointTemperature();
        double previousTemperature = models.get(i - 1).getDewPointTemperature() == null ? 0 : models.get(i - 1).getDewPointTemperature();
        if (Math.abs(currentTemperature - previousTemperature) > THRESHOLD_ADEQUATE_TEMPERATURE) {
            if (currentTemperature != 0.0 && previousTemperature != 0.0) {
                System.out.println("Dew Point Temperature Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousTemperature + " to " + currentTemperature);
            }
        }
    }

    private static void checkAdequateRelativityHumidity(List<Model> models, int i) {
        int currentHumidity = models.get(i).getRelativityHumidity() == null ? 0 : models.get(i).getRelativityHumidity();
        int previousHumidity = models.get(i - 1).getRelativityHumidity() == null ? 0 : models.get(i - 1).getRelativityHumidity();
        if (Math.abs(currentHumidity - previousHumidity) > THRESHOLD_ADEQUATE_RELATIVITY_HUMIDITY) {
            if (currentHumidity != 0.0 && previousHumidity != 0.0) {
                System.out.println("Relativity Humidity Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousHumidity + " to " + currentHumidity);
            }
        }
    }

    private static void checkAdequateAbsoluteHumidity(List<Model> models, int i) {
        double currentHumidity = models.get(i).getAbsoluteHumidity() == null ? 0 : models.get(i).getAbsoluteHumidity();
        double previousHumidity = models.get(i - 1).getAbsoluteHumidity() == null ? 0 : models.get(i - 1).getAbsoluteHumidity();
        if (Math.abs(currentHumidity - previousHumidity) > THRESHOLD_ADEQUATE_ABSOLUTE_HUMIDITY) {
            if (currentHumidity != 0.0 && previousHumidity != 0.0) {
                System.out.println("Absolute Humidity Warning between: " + models.get(i - 1).getDate() + " and " + models.get(i).getDate() + ". from " + previousHumidity + " to " + currentHumidity);
            }
        }
    }
}
