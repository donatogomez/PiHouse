package dg.com.proyecto.Data;

public class DataSet {

    public String temperature;
    public String pressure;
    public String humidity;

    public DataSet(String temperature, String pressure, String humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public DataSet() {
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }
}
