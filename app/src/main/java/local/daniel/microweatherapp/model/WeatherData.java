package local.daniel.microweatherapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
    private WeatherCoordinates coord;
    private WeatherDetail details;
    private String name;
    private Integer cod;
}
