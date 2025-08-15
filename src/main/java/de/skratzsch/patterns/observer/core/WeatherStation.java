package de.skratzsch.patterns.observer.core;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather station that notifies observers about weather changes.
 */
@Component
public class WeatherStation {
    private final List<Observer<WeatherData>> observers = new ArrayList<>();
    private String stationId;
    private double temperature;
    private double humidity;
    private double pressure;

    /**
     * Adds an observer to the list.
     * @param observer The observer to add.
     */
    public void addObserver(Observer<WeatherData> observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list.
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer<WeatherData> observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers about new weather data.
     */
    private void notifyObservers() {
        WeatherData weatherData = new WeatherData(stationId, temperature, humidity, pressure);
        observers.forEach(observer -> observer.onEvent(weatherData));
    }

    /**
     * Updates weather measurements and notifies observers.
     * @param temperature New temperature in Celsius.
     * @param humidity New humidity in percent.
     * @param pressure New air pressure in hPa.
     */
    public void setMeasurements(String stationId, double temperature, double humidity, double pressure) {
        this.stationId = stationId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}
