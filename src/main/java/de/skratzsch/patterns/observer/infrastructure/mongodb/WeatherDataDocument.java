package de.skratzsch.patterns.observer.infrastructure.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "weather_data")
public record WeatherDataDocument(
        @Id String id,
        String stationId,
        double temperature,
        double humidity,
        double pressure,
        Instant timestamp
) {}
