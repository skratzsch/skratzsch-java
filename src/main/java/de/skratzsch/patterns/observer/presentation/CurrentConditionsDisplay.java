package de.skratzsch.patterns.observer.presentation;

import de.skratzsch.patterns.observer.core.Observer;
import de.skratzsch.patterns.observer.core.WeatherData;

/**
 * Displays current weather conditions.
 */
public class CurrentConditionsDisplay implements Observer<WeatherData> {
    @Override
    public void onEvent(WeatherData weatherData) {
        System.out.printf(
                "Current Conditions: %.1f°C, %.1f%% humidity, %.1f hPa%n",
                weatherData.getTemperature(),
                weatherData.getHumidity(),
                weatherData.getPressure()
        );
    }
}
