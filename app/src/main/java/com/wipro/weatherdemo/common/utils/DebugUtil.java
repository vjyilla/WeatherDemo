package com.wipro.weatherdemo.common.utils;


import android.util.Log;

import com.wipro.weatherdemo.common.io.HttpTask;

import java.util.Locale;

import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by illa on 19-10-2016.
 */
public class DebugUtil {

    public final static void debugResponse(String TAG, String response) {
        if (response != null) {
            Log.d(TAG, "Response data:" + response);

        }
    }

    public final static void debugStatusCode(String TAG, int statusCode) {
        String msg = String.format(Locale.US, "Return Status Code: %d", statusCode);
        Log.d(TAG, msg);
    }

    public final static void debugMessage(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Message :" + message);
        }
    }

    public final static void debugEntity(HttpTask.HttpRequest request, String TAG, int mId) {


        try {
            if (request.entity != null) {

                String reqBody = EntityUtils.toString(request.entity, HTTP.UTF_8);
                if (reqBody != null && !reqBody.toLowerCase().contains("password")) {
                    Log.d(TAG, mId + " Message : Entity " + reqBody);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public final static void debugHttpRequest(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Request  :" + message);
        }
    }

    public final static void debugHttpResponse(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Response  :" + message);
        }
    }


}
