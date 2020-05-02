package com.pranjal.sri.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pranjal.sri.weatherapp.api.response.DataResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    TextView mCity,mDatetime, mdescp,mTemp, mTmin, mTmax;

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
        mButton();




        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat  simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String dateTime= simpleDateFormat.format(calendar.getTime());


        mCity.setText(dataResponse.getName() + "");
        mTemp.setText(dataResponse.getMain().getTemp()-273+ "");
        mTmax.setText(dataResponse.getMain().getTemp_max()-273+"");
        mTmin.setText(dataResponse.getMain().getTemp_min()-273+"");
        mDatetime.setText(dateTime);





        }


    private void mButton(){
        mCity = (TextView) findViewById(R.id.city_name);
        mDatetime= (TextView)findViewById(R.id.date_time);
        mTmax = (TextView)findViewById(R.id.temp_max);
        mTmin = (TextView)findViewById(R.id.temp_min);
        mTemp = (TextView)findViewById(R.id.temp);
        mdescp = (TextView)findViewById(R.id.main_weather);


    }

}