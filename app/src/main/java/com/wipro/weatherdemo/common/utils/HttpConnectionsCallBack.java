package com.wipro.weatherdemo.common.utils;

import com.wipro.weatherdemo.common.io.HttpTask;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by illa on 19-10-2016.
 */
public class HttpConnectionsCallBack {

    private static final String TAG = HttpConnectionsCallBack.class.getSimpleName();


    public HttpTask.HttpCallback getWeatherApiCallBack(final CallbackUtils.HttpGetCallBack httpGetCallBack) {
        // TODO Auto-generated method stub
        return new HttpTask.HttpCallback(){
            @Override
            public void callback(int id, HttpTask.HttpResponse response, Header headers[]) {
                JSONObject jsonObject=null;

                try {
                    if(response.response.getBytes().length>0)
                    {
                        jsonObject= new JSONObject(response.response);
                    }

                    if(jsonObject!=null)
                    {
                        DebugUtil.debugMessage(TAG, id+" getWeatherApiCallBack--->>>"+response.response);
                        httpGetCallBack.setJsonObject(jsonObject);
                        // httpGetCallBack.setErrorJson(jsonArray);
                    }
                    else
                    {
                        DebugUtil.debugMessage(TAG, id+" Error --->>>"+response.response);
                        httpGetCallBack.setJsonObject(jsonObject);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();

                }
            }
        };
    }

}
