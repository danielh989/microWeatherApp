package local.daniel.microweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
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
    }

    private HttpUrl buildWeatherUrl() {
        String api_key = "b6907d289e10d714a6e88b30761fae22";
        return new HttpUrl.Builder()
                .scheme("https")
                .host("samples.openweathermap.org")
                .addPathSegments("data/2.5")
                .addQueryParameter("appid", api_key)
                .build();
    }
}
