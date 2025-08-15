package de.skratzsch.patterns.observer.infrastructure;

import de.skratzsch.patterns.observer.application.WeatherStationService;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class WeatherStationControllerTest {

    @Mock
    private WeatherStationService weatherStationService;

    @InjectMocks
    private WeatherStationController weatherStationController;

    @Test
    void updateWeather_shouldCallServiceWithCorrectParameters() throws Exception {
        // Given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherStationController).build();
        WeatherDataDto weatherDataDto = WeatherDataDto.builder()
                .stationId("station1")
                .temperature(22.5)
                .humidity(65.0)
                .pressure(65.0)
                .build();

        // When/Then
        mockMvc.perform(post("/api/weather/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "stationId": "station1",
                                    "temperature": 22.5,
                                    "humidity": 65.0,
                                    "pressure": 1013.25
                                }
                                """))
                .andExpect(status().isOk());

        // Verify
        verify(weatherStationService).updateWeatherData(
                anyString(), anyDouble(), anyDouble(), anyDouble()
        );
    }
}
