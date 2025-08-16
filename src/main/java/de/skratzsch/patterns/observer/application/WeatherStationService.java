package de.skratzsch.patterns.observer.application;

import de.skratzsch.patterns.observer.core.WeatherStation;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataResponseDto;
import de.skratzsch.patterns.observer.infrastructure.mongodb.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStationService {
    private final WeatherStation weatherStation;
    private final WeatherDataRepository repository;

    public WeatherStationService(WeatherStation weatherStation, WeatherDataRepository repository) {
        this.weatherStation = weatherStation;
        this.repository = repository;
    }

    public void updateWeatherData(String stationId, double temperature, double humidity, double pressure) {
        weatherStation.setMeasurements(stationId, temperature, humidity, pressure);
    }

    public List<WeatherDataResponseDto> getWeatherHistory() {
        return repository.findAll().stream().map(doc -> new WeatherDataResponseDto(
                doc.stationId(),
                doc.temperature(),
                doc.humidity(),
                doc.pressure(),
                doc.timestamp())
        ).collect(Collectors.toList());
    }
}
