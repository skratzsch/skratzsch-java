package de.skratzsch;

import de.skratzsch.patterns.observer.CurrentConditionsDisplay;
import de.skratzsch.patterns.observer.StatisticsDisplay;
import de.skratzsch.patterns.observer.WeatherStation;
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
        weatherStation.setMeasurements(22.5, 65.0, 1013.1);
        weatherStation.setMeasurements(23.1, 64.5, 1012.8);
        weatherStation.setMeasurements(21.8, 66.2, 1013.4);

    }
}
