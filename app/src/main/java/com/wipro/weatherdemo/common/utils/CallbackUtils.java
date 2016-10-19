package com.wipro.weatherdemo.common.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by illa on 19-10-2016.
 */
public class CallbackUtils {

    public interface HttpGetCallBack
    {
        public void setJsonObject(JSONObject jsonObject);
        public void setErrorJson(JSONObject errorJson);
    }

}
