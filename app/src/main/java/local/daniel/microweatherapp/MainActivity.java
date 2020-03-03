package local.daniel.microweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import local.daniel.microweatherapp.model.WeatherData;
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
    TextView tempTxt;
    Button searchBtn;
    EditText cityBox;
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

        cityBox = (EditText) findViewById(R.id.cityBox);
        searchBtn = (Button) findViewById(R.id.search);
        tempTxt = (TextView) findViewById(R.id.temp);

        OpenWeatherMap openWeatherMap = retrofit.create(OpenWeatherMap.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked");
                refreshCityWeather(openWeatherMap, cityBox.getText().toString());
            }
        });
     




    }

    private void refreshCityWeather(OpenWeatherMap openWeatherMap, String cityName) {
        Call<WeatherData> weatherCall = openWeatherMap.getWeather(cityName);

        weatherCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                System.out.println("success");
                WeatherData weatherData = response.body();
                tempTxt.setText(weatherData.getMain().getTemp().toString());
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
