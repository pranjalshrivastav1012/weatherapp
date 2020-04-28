package com.pranjal.sri.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pranjal.sri.weatherapp.api.response.DataResponse;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    TextView txtLat, txtLon;

    private final String appid = "ff512bbf2e8833415bb66c6427eab63e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDataResponse(30.35f, 76.77f, appid);
    }

    private void setDataResponse(float lat, float lon, String appId){
        String stringURL ="https://api.openweathermap.org/data/2.5/weather" +
                "?lat=" + lat + "&lon=" + lon + "&appid=" + appId;

        getResponse(stringURL);
    }

    private void getResponse(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {               //On request fail
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(response.isSuccessful()){
                    final String resp = response.body().string();           //Converted response to String
                    MainActivity.this.runOnUiThread(new Runnable() {        //Run Client request call
                        @SneakyThrows
                        @Override
                        public void run() {                                 //Run request
                            DataResponse dataResponse = getPOJOFromString(resp);
                            setData(dataResponse);
                        }
                    });
                }
            }
        });
    }

    //start
    private DataResponse getPOJOFromString(String response){
        Gson mGson = new Gson();
        return mGson.fromJson(response, DataResponse.class);
    }
    //end

    //start
    private void setData(DataResponse dataResponse){
        txtLat = (TextView) findViewById(R.id.textview1);

        txtLat.setText(dataResponse.getMain().getTemp() + "");

        //Normal jo kaam karte the
        //Saare Text view wagrah lagane wale

        /*TIME :
        * Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date_time = sdf.format(cal.getTime())
        * */
    }
    //end
}