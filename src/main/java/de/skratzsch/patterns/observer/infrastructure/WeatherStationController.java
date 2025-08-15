package de.skratzsch.patterns.observer.infrastructure;

import de.skratzsch.patterns.observer.application.WeatherStationService;
import de.skratzsch.patterns.observer.infrastructure.dto.WeatherDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
