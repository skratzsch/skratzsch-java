package de.skratzsch.patterns.observer.infrastructure;

import de.skratzsch.patterns.observer.application.WeatherStationService;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataDto;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    @Test
    void getWeatherHistory_shouldReturnListOfWeatherData() throws Exception {
        // Given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherStationController).build();
        Instant now = Instant.now();
        WeatherDataResponseDto mockResponse = new WeatherDataResponseDto(
                "station1", 20.0, 60.0, 1013.0, now
        );
        when(weatherStationService.getWeatherHistory()).thenReturn(List.of(mockResponse));

        // When/Then
        mockMvc.perform(get("/api/weather/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stationId").value("station1"))
                .andExpect(jsonPath("$[0].temperature").value(20.0))
                .andExpect(jsonPath("$[0].humidity").value(60.0))
                .andExpect(jsonPath("$[0].pressure").value(1013.0));
    }
}
