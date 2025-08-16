package de.skratzsch.patterns.observer.infrastructure.mongodb;

import de.skratzsch.patterns.observer.core.Observer;
import de.skratzsch.patterns.observer.core.WeatherData;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class WeatherDataMongoDbObserver implements Observer<WeatherData> {

    private final WeatherDataRepository repository;

    public WeatherDataMongoDbObserver(WeatherDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onEvent(WeatherData weatherData) {
        WeatherDataDocument document = new WeatherDataDocument(
                UUID.randomUUID().toString(),
                weatherData.getStationId(),
                weatherData.getTemperature(),
                weatherData.getHumidity(),
                weatherData.getPressure(),
                Instant.now()
        );
        repository.save(document);
    }
}
