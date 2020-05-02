package com.pranjal.sri.weatherapp;

import android.content.Context;
import android.content.IntentSender;
import android.graphics.drawable.GradientDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.pranjal.sri.weatherapp.api.response.DataResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.pranjal.sri.weatherapp.R.drawable.ic_storm;

public class MainActivity extends Activity {


    TextView mCity, mDatetime, mdescp, mTemp, mTmin, mTmax, mTfeelsLike, mWindSpeed;
    ImageView mWeatherType;
    LinearLayout mainBg;
    ImageButton mImageBtn;


    private final String appid = "ff512bbf2e8833415bb66c6427eab63e";
    protected static final String TAG = "LocationOnOff";

    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setFinishOnTouchOutside(true);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(MainActivity.this)) {
            //Toast.makeText(MainActivity.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
            //code to get GPS
            setDataResponse(30.35f, 76.77f, appid);
            //finish();
        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(MainActivity.this)){
            Toast.makeText(MainActivity.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(MainActivity.this)) {

            //Toast.makeText(MainActivity.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{

            //Toast.makeText(MainActivity.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
            //yaha bhi same
            setDataResponse(30.35f, 76.77f, appid);
        }

        //setDataResponse(30.35f, 76.77f, appid);
    }

    private void setDataResponse(float lat, float lon, String appId) {
        String stringURL = "https://api.openweathermap.org/data/2.5/weather" +
                "?lat=" + lat + "&lon=" + lon + "&appid=" + appId;

        getResponse(stringURL);
    }

    private void getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {               //On request fail
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
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
    private DataResponse getPOJOFromString(String response) {
        Gson mGson = new Gson();
        return mGson.fromJson(response, DataResponse.class);
    }
    //end

    //start
    private void setData(final DataResponse dataResponse) {
        mButton();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());


        mCity.setText(dataResponse.getName() + "");
        mTemp.setText(String.format("%.1f", (dataResponse.getMain().getTemp() - 273)) + "\u00B0");
        mTmax.setText("Max:" + String.format("%.1f", (dataResponse.getMain().getTemp_max() - 273)) + "\u2103");
        mTmin.setText("Min:" + String.format("%.1f", (dataResponse.getMain().getTemp_min() - 273)) + "\u2103");
        mDatetime.setText(dateTime);
        mdescp.setText(dataResponse.getWeather()[0].getDescription());
        YoYo.with(Techniques.StandUp)
                .duration(1500)
                .repeat(0)
                .playOn(mdescp);


        mImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTfeelsLike.setText("Feels like :" + String.format("%.1f", (dataResponse.getMain().getFeels_like() - 273)) + "\u2103");
                mWindSpeed.setText("Wind Speed :" + dataResponse.getWind().getSpeed());
            }
        });


        if (dataResponse.getWeather()[0].getId() >= 200 && dataResponse.getWeather()[0].getId() <= 232) {

            mWeatherType.setImageResource(ic_storm);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF4D6AA4, 0xFFC7A5A6});
            mainBg.setBackgroundDrawable(gd);


        } else if (dataResponse.getWeather()[0].getId() > 800) {
            mWeatherType.setImageResource(R.drawable.ic_cloud);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF5895B3, 0xFFA1BAC8});
            mainBg.setBackgroundDrawable(gd);

        } else if (dataResponse.getWeather()[0].getId() >= 300 && dataResponse.getWeather()[0].getId() <= 321) {
            mWeatherType.setImageResource(R.drawable.ic_drizzle);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFCB7C87, 0xFF066186});
            mainBg.setBackgroundDrawable(gd);
        } else if (dataResponse.getWeather()[0].getId() >= 500 && dataResponse.getWeather()[0].getId() <= 531) {
            mWeatherType.setImageResource(R.drawable.ic_rain);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF37353C, 0xFFB1907B});
            mainBg.setBackgroundDrawable(gd);

        } else if (dataResponse.getWeather()[0].getId() <= 600 && dataResponse.getWeather()[0].getId() >= 622) {
            mWeatherType.setImageResource(R.drawable.ic_snowing);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF797D87, 0xFFACB5BB});
            mainBg.setBackgroundDrawable(gd);

        } else if (dataResponse.getWeather()[0].getId() <= 721 && dataResponse.getWeather()[0].getId() >= 701) {
            mWeatherType.setImageResource(R.drawable.ic_fog);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFBB8DB8, 0xFF686496});
            mainBg.setBackgroundDrawable(gd);


        } else if (dataResponse.getWeather()[0].getId() <= 761 && dataResponse.getWeather()[0].getId() >= 731) {
            mWeatherType.setImageResource(R.drawable.ic_sandstorm);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFB27239, 0xFFD8A25D});
            mainBg.setBackgroundDrawable(gd);
        } else if (dataResponse.getWeather()[0].getId() == 800) {
            mWeatherType.setImageResource(R.drawable.ic_sunny);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF831062, 0xFFF54D3B});
            mainBg.setBackgroundDrawable(gd);

        } else if (dataResponse.getWeather()[0].getId() >= 801) {
            mWeatherType.setImageResource(R.drawable.ic_cloud);
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF51A4DB, 0xFFFFFFFF});
            mainBg.setBackgroundDrawable(gd);

        }
    }


    private void mButton() {
        mCity = (TextView) findViewById(R.id.city_name);
        mDatetime = (TextView) findViewById(R.id.date_time);
        mTmax = (TextView) findViewById(R.id.temp_max);
        mTmin = (TextView) findViewById(R.id.temp_min);
        mTemp = (TextView) findViewById(R.id.temp);
        mdescp = (TextView) findViewById(R.id.main_weather);
        mWeatherType = (ImageView) findViewById(R.id.weather_type);
        mainBg = (LinearLayout) findViewById(R.id.main_background);
        mWindSpeed = (TextView) findViewById(R.id.wind_speed);
        mTfeelsLike = (TextView) findViewById(R.id.feels_like);
        mImageBtn = (ImageButton) findViewById(R.id.image_btn);
    }

    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(MainActivity.this, REQUEST_LOCATION);

                                finish();
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }
    }

}

