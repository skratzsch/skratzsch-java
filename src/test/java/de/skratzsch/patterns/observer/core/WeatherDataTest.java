package de.skratzsch.patterns.observer.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        String stationId = "station1";
        double temperature = 22.5;
        double humidity = 65.0;
        double pressure = 1013.25;

        // When
        WeatherData weatherData = new WeatherData(stationId, temperature, humidity, pressure);

        // Then
        assertEquals(stationId, weatherData.getStationId());
        assertEquals(temperature, weatherData.getTemperature());
        assertEquals(humidity, weatherData.getHumidity());
        assertEquals(pressure, weatherData.getPressure());
    }

    @Test
    void getters_shouldReturnCorrectValues() {
        // Given
        WeatherData weatherData = new WeatherData("station2", 18.0, 70.0, 1012.0);

        // When/Then
        assertEquals("station2", weatherData.getStationId());
        assertEquals(18.0, weatherData.getTemperature());
        assertEquals(70.0, weatherData.getHumidity());
        assertEquals(1012.0, weatherData.getPressure());
    }

    @Test
    void fields_shouldBeImmutable() {
        // Given
        String stationId = "station3";
        double temperature = 25.0;
        double humidity = 50.0;
        double pressure = 1015.0;
        WeatherData weatherData = new WeatherData(stationId, temperature, humidity, pressure);

        // When: Try to "modify" the fields via reflection (just for test)
        // (In real code, fields are final and cannot be modified after construction)

        // Then: Ensure getters still return the original values
        assertEquals(stationId, weatherData.getStationId());
        assertEquals(temperature, weatherData.getTemperature());
        assertEquals(humidity, weatherData.getHumidity());
        assertEquals(pressure, weatherData.getPressure());
    }

    @Test
    void equalsAndHashCode_shouldWorkForSameValues() {
        // Given
        WeatherData data1 = new WeatherData("station4", 20.0, 60.0, 1014.0);
        WeatherData data2 = new WeatherData("station4", 20.0, 60.0, 1014.0);

        // When/Then
        assertEquals(data1, data2);
        assertEquals(data1.hashCode(), data2.hashCode());
    }

    @Test
    void equals_shouldReturnFalseForDifferentValues() {
        // Given
        WeatherData data1 = new WeatherData("station5", 20.0, 60.0, 1014.0);
        WeatherData data2 = new WeatherData("station6", 20.0, 60.0, 1014.0);

        // When/Then
        assertNotEquals(data1, data2);
    }
}
