package de.skratzsch.patterns.observer.infrastructure.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDataRepository extends MongoRepository<WeatherDataDocument, String> {
}
