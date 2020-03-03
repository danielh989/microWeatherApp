package local.daniel.microweatherapp.retrofit;

import local.daniel.microweatherapp.model.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("weather?appid=b6907d289e10d714a6e88b30761fae22")
    Call<WeatherData> getWeather(@Query("q") String city);
}
