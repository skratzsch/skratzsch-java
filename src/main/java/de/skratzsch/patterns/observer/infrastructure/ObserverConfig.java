package de.skratzsch.patterns.observer.infrastructure;

import de.skratzsch.patterns.observer.core.WeatherStation;
import de.skratzsch.patterns.observer.infrastructure.mongodb.WeatherDataMongoDbObserver;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ObserverConfig {

    private final WeatherStation weatherStation;
    private final WeatherDataMongoDbObserver mongoDbObserver;

    public ObserverConfig(WeatherStation weatherStation, WeatherDataMongoDbObserver mongoDbObserver) {
        this.weatherStation = weatherStation;
        this.mongoDbObserver = mongoDbObserver;
    }

    @PostConstruct
    public void registerObservers() {
        weatherStation.addObserver(mongoDbObserver);
    }
}
