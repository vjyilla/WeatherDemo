package com.wipro.weatherdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wipro.weatherdemo.common.dto.Forecast;
import com.wipro.weatherdemo.common.dto.FiveWeather;
import com.wipro.weatherdemo.common.dto.WeatherObject;
import com.wipro.weatherdemo.common.utils.CallbackUtils;
import com.wipro.weatherdemo.common.utils.DebugUtil;
import com.wipro.weatherdemo.common.utils.HttpConnectionManager;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
    public HttpConnectionManager mHttpConnectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHttpConnectionManager = new HttpConnectionManager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public CallbackUtils.HttpGetCallBack getHttpWeatherCallBack(){
        return new CallbackUtils.HttpGetCallBack() {
            @Override
            public void setJsonObject(JSONObject jsonObject) {

                try
                {
               List<WeatherObject> daysOfTheWeek = new ArrayList<WeatherObject>();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Forecast forecast = gson.fromJson(jsonObject.toString(), Forecast.class);
                 if(forecast!=null)
                  {

                    int[] everyday = new int[]{0, 0, 0, 0, 0, 0, 0};
                    List<FiveWeather> weatherInfo = forecast.getList();
                    for (int i = 0; i < weatherInfo.size(); i++) {
                        String time = weatherInfo.get(i).getDt_txt();



                        String shortDay = convertTimeToDay(time);
                        String temp = weatherInfo.get(i).getMain().getTemp();
                        String tempMin = weatherInfo.get(i).getMain().getTemp_min();
                        if (convertTimeToDay(time).equals("Mon") && everyday[0] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[0] = 1;
                        }
                        if (convertTimeToDay(time).equals("Tue") && everyday[1] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[1] = 1;
                        }
                        if (convertTimeToDay(time).equals("Wed") && everyday[2] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[2] = 1;
                        }
                        if (convertTimeToDay(time).equals("Thu") && everyday[3] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[3] = 1;
                        }
                        if (convertTimeToDay(time).equals("Fri") && everyday[4] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[4] = 1;
                        }
                        if (convertTimeToDay(time).equals("Sat") && everyday[5] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[5] = 1;
                        }
                        if (convertTimeToDay(time).equals("Sun") && everyday[6] < 1) {
                            daysOfTheWeek.add(new WeatherObject(shortDay, R.mipmap.ic_launcher ,temp, tempMin,weatherInfo.get(i).getListWeather().get(0).getDescription()));
                            everyday[6] = 1;
                        }
                    }
                  }

                    }
                    catch (Exception e)
                    {
                    e.printStackTrace();
                    }


            }

            @Override
            public void setErrorJson(JSONObject jsonObject) {

            }
        };
    }


        private String convertTimeToDay(String time){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSSS", Locale.getDefault());
            String days = "";
            try {
                Date date = format.parse(time);
                System.out.println("Our time " + date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                days = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
                System.out.println("Our time " + days);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return days;
        }

    @Override
    protected void onResume() {

        super.onResume();

        mHttpConnectionManager.getWeatherApiRequest(getHttpWeatherCallBack(), "London,us" );
    }
}
