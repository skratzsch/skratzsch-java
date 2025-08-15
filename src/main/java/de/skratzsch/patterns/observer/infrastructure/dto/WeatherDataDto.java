package de.skratzsch.patterns.observer.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class WeatherDataDto {
    private String stationId;
    private double temperature;
    private double humidity;
    private double pressure;

    // Default-Konstruktor für JSON-Deserialisierung (z. B. Jackson)
    public WeatherDataDto() {}

}
