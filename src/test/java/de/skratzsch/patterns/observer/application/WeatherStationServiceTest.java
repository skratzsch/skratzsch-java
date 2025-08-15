package de.skratzsch.patterns.observer.application;

import de.skratzsch.patterns.observer.core.WeatherStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherStationServiceTest {

    @Mock
    private WeatherStation weatherStation;

    @InjectMocks
    private WeatherStationService weatherStationService;

    @Test
    void updateWeatherData_shouldDelegateToWeatherStation() {
        // Given
        String stationId = "station1";
        double temperature = 22.5;
        double humidity = 65.0;
        double pressure = 1013.25;

        // When
        weatherStationService.updateWeatherData(stationId, temperature, humidity, pressure);

        // Then
        verify(weatherStation).setMeasurements(stationId, temperature, humidity, pressure);
    }
}
