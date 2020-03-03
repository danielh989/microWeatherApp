package local.daniel.microweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import local.daniel.microweatherapp.model.WeatherData;
import local.daniel.microweatherapp.model.WeatherDetail;
import local.daniel.microweatherapp.retrofit.OpenWeatherMap;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUrl weatherUrl = buildWeatherUrl();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(weatherUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        OpenWeatherMap openWeatherMap = retrofit.create(OpenWeatherMap.class);
        Call<WeatherData> weatherCall = openWeatherMap.getWeather("London");

        System.out.println(weatherCall.request().url().toString());

        weatherCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                System.out.println("success");
                System.out.println(response.body().getMain().getTemp());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("failed =(");
            }
        });


    }

    private HttpUrl buildWeatherUrl() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("samples.openweathermap.org")
                .addPathSegments("data/2.5/")
                .build();
    }
}
