package com.wipro.weatherdemo.common.utils;

import android.content.Context;

import com.wipro.weatherdemo.BuildConfig;
import com.wipro.weatherdemo.R;

/**
 * Created by illa on 19-10-2016.
 */
public class APIUtils {

    final static String TAG = APIUtils.class.getClass().getSimpleName();
    public static String getUrlVersionAppend(Context mContext,String method) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder(0x10000);
        builder.append(BuildConfig.WEATHER_SERVICE_URL);
        builder.append(Constants.FORWARD_SLASH);
        builder.append(method);
         return builder.toString();
    }


    public static String getUrlWetherApiRequest(Context mContext,String query) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder(0x10000);

        builder.append(getUrlVersionAppend(mContext, mContext.getResources().getString(R.string.forecast_method)) );
        builder.append(APIConstants.Q_PARAM);
        builder.append(APIConstants.EQUAL_TO);
        builder.append(query);

        builder.append(APIConstants.AMPRESAND);
        builder.append(APIConstants.MODE);
        builder.append(APIConstants.EQUAL_TO);
        builder.append(APIConstants.JSON_VALUE);

        builder.append(APIConstants.AMPRESAND);
        builder.append(APIConstants.APP_ID);
        builder.append(APIConstants.EQUAL_TO);
        builder.append(BuildConfig.WEATHER_API_KEY);
        return builder.toString();

    }
}
