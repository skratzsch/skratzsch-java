package de.skratzsch.patterns.observer;

import de.skratzsch.patterns.observer.core.WeatherStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WeatherStationTest {
    @Test
    void testWeatherUpdates() {
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.addObserver(event ->
                System.out.printf("Test: %.1f°C%n", event.getTemperature())
        );

        assertDoesNotThrow(() ->
                weatherStation.setMeasurements("1",20.0, 60.0, 1010.0)
        );
    }
}
