package MainPackage;

import java.util.Date;

public class ModelExtended {
    private Date date;
    private Double minTempAir;
    private Double minTempSoil;
    private Double maxTempAir;
    private Double averageTemp;
    private Double min2cm;
    private Double precipitation00;
    private Double precipitation06;
    private Double precipitation12;
    private Double precipitation18;

    public ModelExtended(Date date, Double minTempAir, Double minTempSoil, Double maxTempAir, Double averageTemp, Double min2cm, Double precipitation00, Double precipitation06, Double precipitation12, Double precipitation18) {
        this.date = date;
        this.minTempAir = minTempAir;
        this.minTempSoil = minTempSoil;
        this.maxTempAir = maxTempAir;
        this.averageTemp = averageTemp;
        this.min2cm = min2cm;
        this.precipitation00 = precipitation00;
        this.precipitation06 = precipitation06;
        this.precipitation12 = precipitation12;
        this.precipitation18 = precipitation18;
    }

    public Date getDate() {
        return date;
    }

    public Double getMinTempAir() {
        return minTempAir;
    }

    public Double getMinTempSoil() {
        return minTempSoil;
    }

    public Double getMaxTempAir() {
        return maxTempAir;
    }

    public Double getAverageTemp() {
        return averageTemp;
    }

    public Double getMin2cm() {
        return min2cm;
    }

    public Double getPrecipitation00() {
        return precipitation00;
    }

    public Double getPrecipitation06() {
        return precipitation06;
    }

    public Double getPrecipitation12() {
        return precipitation12;
    }

    public Double getPrecipitation18() {
        return precipitation18;
    }

    @Override
    public String toString() {
        return "ModelExtended{" +
                "date=" + date +
                ", minTempAir=" + minTempAir +
                ", minTempSoil=" + minTempSoil +
                ", maxTempAir=" + maxTempAir +
                ", averageTemp=" + averageTemp +
                ", min2cm=" + min2cm +
                ", precipitation00=" + precipitation00 +
                ", precipitation06=" + precipitation06 +
                ", precipitation12=" + precipitation12 +
                ", precipitation18=" + precipitation18 +
                '}';
    }
}
