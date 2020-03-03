package local.daniel.microweatherapp.retrofit;

import local.daniel.microweatherapp.model.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("weather")
    Call<WeatherData> getWeather(@Query("q") String city);
}
