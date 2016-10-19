package com.wipro.weatherdemo.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wipro.weatherdemo.common.io.HttpTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by illa on 19-10-2016.
 */
public class AppUtils {

    public static boolean isRequestFailure(int statusCode) {
        // TODO Auto-generated method stub
        //BADREQUEST=400,UNAUTHORIZED=401,FORBIDDEN=403,NOTFOUND=404,INTERNALERROR=500,NOTIMPLEMENTED=501,GATEWAYTIMEOUT=503

        //400 BadRequest	InvalidParameter	If the num or page parameters are less than 1
        //	400 BadRequest	InvalidSession	SessionID is not recognized, is closed or is malformed
        //401 Unauthorized	LoginRequired
            /*400 BadRequest	AlreadyLoggedIn	The session is logged in, but this action is only valid for non-logged in users.
			400 BadRequest	BadCredentials	Invalid username/password
			400 BadRequest	InvalidSession	SessionID is not recognized, is closed or is malformed*/

        if (statusCode == HttpTask.ResponseStatusCodes.BADREQUEST || statusCode == HttpTask.ResponseStatusCodes.FORBIDDEN || statusCode == HttpTask.ResponseStatusCodes.NOTFOUND) {
            return true;
        }

        return false;
    }


    public static String getString(byte[] myBytes) throws IOException {
        StringBuilder builder = new StringBuilder(0x10000);
        InputStream inStream = new ByteArrayInputStream(myBytes);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(
                inStream), 8192);
        String line;
        while ((line = buffReader.readLine()) != null)
            builder.append(line);
        return builder.toString();
    }


    public static boolean isServerInvalidResponse(int statusCode) {
        // TODO Auto-generated method stub
        if (statusCode == 0 || statusCode >= 500) {
            return true;
        }
        return false;
    }

    public static boolean isOnline(Context context) {
        boolean online = false;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null) {
            NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
            if (netInfo != null)
                online = netInfo.isConnected();
        }
        return online;
    }


}
