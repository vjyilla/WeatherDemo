package com.wipro.weatherdemo.common.dto;

import java.util.List;

/**
 * Created by illa on 19-10-2016.
 */
public class Forecast {

    private List<FiveWeather> list;

    public Forecast(List<FiveWeather> list) {
        this.list = list;
    }

    public List<FiveWeather> getList() {
        return list;
    }
}
