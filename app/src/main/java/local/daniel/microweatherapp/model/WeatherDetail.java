package local.daniel.microweatherapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDetail {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double temp_min;
    private Double temp_max;
}
