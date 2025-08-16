package de.skratzsch.patterns.observer.infrastructure;

import de.skratzsch.patterns.observer.application.WeatherStationService;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataDto;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataResponseDto;
import de.skratzsch.patterns.observer.infrastructure.mongodb.WeatherDataDocument;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherStationController {
    private final WeatherStationService weatherStationService;

    public WeatherStationController(WeatherStationService weatherStationService) {
        this.weatherStationService = weatherStationService;
    }

    @PostMapping("/update")
    public void updateWeather(@RequestBody WeatherDataDto weatherDataDto) {
        weatherStationService.updateWeatherData(
                weatherDataDto.getStationId(),
                weatherDataDto.getTemperature(),
                weatherDataDto.getHumidity(),
                weatherDataDto.getPressure()
        );
    }

    @GetMapping("/history")
    public List<WeatherDataResponseDto> getWeatherHistory() {
        return weatherStationService.getWeatherHistory();
    }
}
