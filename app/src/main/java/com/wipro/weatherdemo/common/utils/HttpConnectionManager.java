package com.wipro.weatherdemo.common.utils;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.wipro.weatherdemo.common.io.HttpTask;

import java.lang.ref.WeakReference;

/**
 * Created by illa on 19-10-2016.
 */

public class HttpConnectionManager {


     private static final String TAG = HttpConnectionManager.class.getSimpleName();

    private WeakReference<HttpTask> mWRHttpTask;
    Context mContext;
    HttpConnectionsCallBack httpCallBack;

    public HttpConnectionManager(Context context) {
        mContext = context;
        httpCallBack = new HttpConnectionsCallBack();
    }

    public void getWeatherApiRequest( CallbackUtils.HttpGetCallBack callBack,String city) {
        try
        {

           String url=APIUtils.getUrlWetherApiRequest(mContext, city);
            executeGetRequest(APIConstants.WEATHER_FORECAST_ID,  httpCallBack.getWeatherApiCallBack(callBack),null,url);

        }
        catch (Exception e) {
            // TODO: handle exception
           e.printStackTrace();

        }
    }

    public void executeGetRequest(int requetID, HttpTask.HttpCallback httpCallback, RequestParams requestParams, String url) {
        HttpTask.HttpRequest httpRequest = new HttpTask.HttpRequest();
        httpRequest.method = HttpTask.HttpRequest.METHOD_GET;
        HttpTask.HttpRequest.requestParams=requestParams;

        httpRequest.url = url;
        HttpTask httpTask = new HttpTask(mContext, requetID, httpCallback, null);
        mWRHttpTask = new WeakReference<HttpTask>(httpTask);
        mWRHttpTask.get().doInBackground(httpRequest);


}

}
