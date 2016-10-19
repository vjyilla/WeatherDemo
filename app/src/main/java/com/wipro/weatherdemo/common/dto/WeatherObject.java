package com.wipro.weatherdemo.common.dto;

import com.wipro.weatherdemo.common.utils.DebugUtil;

/**
 * Created by illa on 19-10-2016.
 */
public class WeatherObject {

    private String dayOfWeek;

    private int weatherIcon;

    private String description;
    private String weatherResult;

    private String weatherResultSmall;

    public WeatherObject(String dayOfWeek, int weatherIcon, String weatherResult, String weatherResultSmall,String description) {
        this.dayOfWeek = dayOfWeek;
        this.weatherIcon = weatherIcon;
        this.weatherResult = weatherResult;
        this.weatherResultSmall = weatherResultSmall;
        this.description = description;
        DebugUtil.debugMessage(getClass().getName(), dayOfWeek+description+"@@@@ "+weatherResultSmall);
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public String getWeatherResult() {
        return weatherResult;
    }

    public String getWeatherResultSmall() {
        return weatherResultSmall;
    }
}

