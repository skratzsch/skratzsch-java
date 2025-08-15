package de.skratzsch.patterns.observer.presentation;

import de.skratzsch.patterns.observer.core.Observer;
import de.skratzsch.patterns.observer.core.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays weather statistics (min/max/avg).
 */
public class StatisticsDisplay implements Observer<WeatherData> {
    private final List<Double> temperatures = new ArrayList<>();

    @Override
    public void onEvent(WeatherData weatherData) {
        temperatures.add(weatherData.getTemperature());

        double min = temperatures.stream().min(Double::compare).orElse(0.0);
        double max = temperatures.stream().max(Double::compare).orElse(0.0);
        double avg = temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        System.out.printf(
                "Statistics: Min=%.1f°C, Max=%.1f°C, Avg=%.1f°C%n",
                min, max, avg
        );
    }
}
