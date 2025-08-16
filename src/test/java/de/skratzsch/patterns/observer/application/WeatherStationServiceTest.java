package de.skratzsch.patterns.observer.application;

import de.skratzsch.patterns.observer.core.WeatherStation;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataResponseDto;
import de.skratzsch.patterns.observer.infrastructure.mongodb.WeatherDataDocument;
import de.skratzsch.patterns.observer.infrastructure.mongodb.WeatherDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherStationServiceTest {

    @Mock
    private WeatherStation weatherStation;

    @Mock
    private WeatherDataRepository repository;

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

    @Test
    void getWeatherHistory_shouldReturnDataFromRepository() {
        // Given
        WeatherDataDocument mockDocument = new WeatherDataDocument(
                "123",
                "station1",
                20.0,
                60.0,
                1013.0,
                Instant.now()
        );
        when(repository.findAll()).thenReturn(List.of(mockDocument));

        // When
        List<WeatherDataResponseDto> result = weatherStationService.getWeatherHistory();

        // Then
        assertEquals(1, result.size());
        assertEquals("station1", result.get(0).getStationId());
        verify(repository, times(1)).findAll();
    }
}
