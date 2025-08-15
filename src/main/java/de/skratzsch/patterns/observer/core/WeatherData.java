package de.skratzsch.patterns.observer.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents weather data as an event.
 * Contains temperature, humidity, and pressure.
 */
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class WeatherData implements Event {
    private final String stationId;
    private final double temperature;
    private final double humidity;
    private final double pressure;
}
