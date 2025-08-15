package de.skratzsch.patterns.observer.infrastructure.dto;

public class WeatherDataDto {
    private String stationId;
    private double temperature;
    private double humidity;
    private double pressure;

    // Default-Konstruktor für JSON-Deserialisierung (z. B. Jackson)
    public WeatherDataDto() {}

    // Getter und Setter
    public String getStationId() { return stationId; }
    public void setStationId(String stationId) { this.stationId = stationId; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }
}
