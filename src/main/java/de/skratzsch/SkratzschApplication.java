package de.skratzsch;

import de.skratzsch.patterns.observer.presentation.CurrentConditionsDisplay;
import de.skratzsch.patterns.observer.presentation.StatisticsDisplay;
import de.skratzsch.patterns.observer.core.WeatherStation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkratzschApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkratzschApplication.class, args);

        // Wetterstations-Beispiel
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.addObserver(new CurrentConditionsDisplay());
        weatherStation.addObserver(new StatisticsDisplay());

        // Simuliere Wetteränderungen
        weatherStation.setMeasurements("1", 22.5, 65.0, 1013.1);
        weatherStation.setMeasurements("1",23.1, 64.5, 1012.8);
        weatherStation.setMeasurements("1",21.8, 66.2, 1013.4);

    }
}
