package local.daniel.microweatherapp.retrofit;

import local.daniel.microweatherapp.model.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("weather?appid=8cffb69694b9a28e51152fbee8c40085")
    Call<WeatherData> getWeather(@Query("q") String city);
}
