package com.wipro.weatherdemo.common.dto;

import java.util.List;

/**
 * Created by illa on 19-10-2016.
 */
public class FiveWeather {

    private String dt_txt;

    private Main main;
    private List<Weather> weather;



    public FiveWeather(String dt_txt, Main main, List<Weather> weather) {
        this.dt_txt = dt_txt;
        this.main = main;

        this.weather = weather;
    }

    public String getDt_txt(){
        return dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getListWeather() {
        return weather;
    }




}
