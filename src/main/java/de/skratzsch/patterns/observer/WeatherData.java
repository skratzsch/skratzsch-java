package de.skratzsch.patterns.observer;

/**
 * Represents weather data as an event.
 * Contains temperature, humidity, and pressure.
 */
public class WeatherData implements Event {
    private final double temperature;
    private final double humidity;
    private final double pressure;

    /**
     * Creates new weather data.
     * @param temperature Temperature in Celsius.
     * @param humidity Humidity in percent.
     * @param pressure Air pressure in hPa.
     */
    public WeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
