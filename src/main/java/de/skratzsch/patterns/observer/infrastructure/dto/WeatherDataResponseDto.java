package de.skratzsch.patterns.observer.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class WeatherDataResponseDto {
    private String stationId;
    private double temperature;
    private double humidity;
    private double pressure;
    private Instant timestamp;
}
