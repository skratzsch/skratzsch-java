package de.skratzsch.patterns.observer.application;

import de.skratzsch.patterns.observer.core.WeatherStation;
import org.springframework.stereotype.Service;

@Service
public class WeatherStationService {
    private final WeatherStation weatherStation;

    public WeatherStationService(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    public void updateWeatherData(String stationId, double temperature, double humidity, double pressure) {
        weatherStation.setMeasurements(stationId, temperature, humidity, pressure);
    }
}
