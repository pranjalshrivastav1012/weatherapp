package com.pranjal.sri.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranjal.sri.weatherapp.api.response.DataResponse;
import com.pranjal.sri.weatherapp.api.response.Weather;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    TextView txtLat;

    private final String appid = "ff512bbf2e8833415bb66c6427eab63e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDataResponse(30.35f, 76.77f, appid);
    }

    private void setDataResponse(float lat, float lon, String appId){
        txtLat = (TextView) findViewById(R.id.textview1);
        String stringURL ="https://api.openweathermap.org/data/2.5/weather?lat=" +
                lat + "&lon=" + lon + "&appid=" + appId;


        OkHttpClient client = new OkHttpClient();                           //Make client

        Request request = new Request.Builder().url(stringURL).build();     //Make request

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {               //On request fail
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String resp = response.body().string();           //Converted response to String

                    MainActivity.this.runOnUiThread(new Runnable() {        //Run Client request call
                        @SneakyThrows
                        @Override
                        public void run() {                                 //Run request
                            txtLat.setText(resp);                           //String set text
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                DataResponse readValue = mapper.readValue(resp, DataResponse.class);
                                txtLat.setText(readValue.getName());
                            } catch (Exception e) {

                            }
                            //DataResponse dataResponse = new DataResponse;
                            //String (resp) -> convert into DataResponse
                            //Online search : String into Response/POJO in android
                            //txtLat.setText(dataResponse.getName());
                        }
                    });
                }
            }
        });
    }
}