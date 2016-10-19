package com.wipro.weatherdemo.common.io;

import android.content.Context;
import android.os.Handler;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.wipro.weatherdemo.common.utils.APIConstants;
import com.wipro.weatherdemo.common.utils.AppUtils;
import com.wipro.weatherdemo.common.utils.DebugUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by illa on 19-10-2016.
 */
public class HttpTask {

    private static final String TAG = HttpTask.class.getSimpleName();

    public ResponseHandlerInterface getResponseHandler() {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {	try {

                DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess Response code  "+statusCode);
                mResponse.statusCode = statusCode;

                DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess 1  "+statusCode);
                DebugUtil.debugStatusCode(TAG, statusCode);
                //DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess 2 "+statusCode);
                if (response != null && response.length > 0) {
                    DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess 3  "+statusCode);
                    mResponse.response = AppUtils.getString(response);

                    if(mResponse.response!=null&&mResponse.response.contains("<html>"))
                    {
                        DebugUtil.debugResponse(TAG, mId+" onSuccess 4"	+ new String(response));
                        JSONObject jsonObject= new JSONObject();
                        jsonObject.put(APIConstants.ERROR, mResponse.response);
                        jsonObject.put(APIConstants.ERRORMESSAGE, mResponse.response);
                        mResponse.response=jsonObject.toString();
                        mCallback.callback(mId, mResponse,headers);
                    }else
                    {
                        DebugUtil.debugResponse(TAG, mId+" onSuccess 5"	+ new String(response));
                        mCallback.callback(mId, mResponse,headers);
                    }

                    DebugUtil.debugResponse(TAG, mId+" onSuccess"	+ new String(response));
                    //DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess Response 4  "+statusCode);
                }
                else
                {
                    DebugUtil.debugResponse(TAG, mId+" onSuccess 6"	+ new String(response));
                    //DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess Response 5  "+statusCode);
                    mResponse.response = "";
                    mCallback.callback(mId, mResponse,headers);
                    //	DebugUtil.debugHttpResponse(TAG,  mId+"  onSuccess Response 6  "+statusCode);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                DebugUtil.debugHttpResponse(TAG, "onSuccess  7  "+statusCode);
            }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable error) {


                DebugUtil.debugHttpResponse(TAG, mId + "  onFailure statusCode " + statusCode + headers);
                //AppUtils.debugThrowable(TAG, e,mId);
                mResponse.status = ResponseStatus.ERROR;
                mResponse.statusCode = statusCode;


                try
                {
                    if (errorResponse != null && errorResponse.length > 0) {

                        mResponse.response = AppUtils.getString(errorResponse);
                        DebugUtil.debugHttpResponse(TAG, mId + "  onFailure  " + mResponse.response);

                    }


                }catch(Exception e)
                {
                    e.printStackTrace();
                }

                if(AppUtils.isServerInvalidResponse(statusCode)&&AppUtils.isOnline(mContext))
                {
                    try
                    {
                        JSONObject jsonObject= new JSONObject();
                        jsonObject.put(APIConstants.ERROR, "Oops! We are having trouble connecting you. Please try again later.");
                        jsonObject.put(APIConstants.ERRORMESSAGE, "Oops! We are having trouble connecting you. Please try again later.");
                        mResponse.response=jsonObject.toString();

                        mCallback.callback(mId, mResponse,headers);
                        DebugUtil.debugHttpResponse(TAG,  mId+"onFailureL  8"+jsonObject.toString());
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                        DebugUtil.debugHttpResponse(TAG, mId + "  onFailure 18 " + statusCode);
                    }

                }
                else
                {



                    try {
                        DebugUtil.debugHttpResponse(TAG,  mId+"  onFailure 3 "+statusCode);
                        if (errorResponse != null && errorResponse.length > 0) {
                            DebugUtil.debugHttpResponse(TAG, mId+ "  onFailure 4 4"+statusCode);
                            mResponse.response = AppUtils.getString(errorResponse);
                            //DebugUtil.debugHttpResponse(TAG, "  onFailure 5 "+statusCode);
                            mCallback.callback(mId, mResponse,headers);
                            //	DebugUtil.debugHttpResponse(TAG, "  onFailure 6 "+statusCode);
                            DebugUtil.debugResponse(TAG, "onFailure"	+ new String(errorResponse));
                            //	DebugUtil.debugHttpResponse(TAG, "  onFailure 7 "+statusCode);
                        }
                        else
                        {
                            if(mContext!=null&&!AppUtils.isOnline(mContext))
                            {
                                JSONObject jsonObject= new JSONObject();
                                jsonObject.put(APIConstants.ERROR, "The Internet connection appears to be offline");
                                jsonObject.put(APIConstants.ERRORMESSAGE, "The Internet connection appears to be offline");
                                mResponse.response=jsonObject.toString();

                                mCallback.callback(mId, mResponse,headers);
                                DebugUtil.debugHttpResponse(TAG,  mId+"onFailureL  8"+jsonObject.toString());
                            }
                            else
                            {
                                //DebugUtil.debugHttpResponse(TAG,  mId+"  onFailure  8"+statusCode);
                                mResponse.response = "";
                                mCallback.callback(mId, mResponse,headers);
                                DebugUtil.debugHttpResponse(TAG, "  onFailure 9"+statusCode);
                                if (errorResponse != null && errorResponse.length > 0) {
                                    DebugUtil.debugResponse(TAG, "onFailure"
                                            + new String(errorResponse));
                                }
                                DebugUtil.debugHttpResponse(TAG,  mId+"  onFailure 10"+statusCode);
                            }
                        }
                    }catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        DebugUtil.debugHttpResponse(TAG,  mId+"  onFailure 11 "+statusCode);
                    }




                    // setError();
                    // cancel(false);
                }


            }



        };
    }

    public interface HttpCallback {
        public void callback(int id, HttpResponse response, Header headers[]);
    }

    public static class HttpRequest {

        public static final int METHOD_GET = 0;


        public static List<NameValuePair> params;
        public static RequestParams requestParams;

        public HttpEntity entity;

        public boolean isResponseBinary;

        public int method;
        public String url;

        public HttpRequest() {

        }

        public HttpRequest(int method, String url, String data) {
            this.method = method;
            this.url = url;
            try {
                entity = new StringEntity(data);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public HttpRequest(int method, String url) {
            this.method = method;
            this.url = url;
        }
    }

    public static class HttpResponse {
        public String response;
        public InputStream respStream;
        public int status = ResponseStatus.NONE;
        public int statusCode = ResponseStatusCodes.OK;

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return String.format("HttpResponse{response: %s, status: %d}",
                    response, status);
        }
    }

    public static class ResponseStatus {
        public static final int NONE = 0, RESPONSE_WAITING = 1,  ERROR = 2;
    }

    public static class ResponseStatusCodes {

        public static final int OK = 200,
                BADREQUEST=400,
                FORBIDDEN=403,NOTFOUND=404;

    }

    private HttpCallback mCallback;

    private int mId;
    private HttpResponse mResponse;

    private Handler mHandler;

    private int mErrorMsgWhat;

    private Context mContext;

    private Throwable mException;
    public AsyncHttpClient client;

    public HttpTask(Context context, int id, HttpCallback callback,
                    Handler handler) {
        mId = id;
        mCallback = callback;
        // mUserAgent = userAgent;
        mHandler = handler;
        // mTimeout = timeout;
        mErrorMsgWhat = id;
        // mDeviceId = deviceId;
        mContext = context;
        client = new AsyncHttpClient();

    }

    public HttpTask(HttpCallback callback) {
        mCallback = callback;

    }



    public HttpResponse doInBackground(HttpRequest request) {

        RequestHandle requestHandle;


        client.setTimeout(30 * 1000);



        mResponse = new HttpResponse();

        InputStream respStream = null;
        try {

            switch (request.method) {


                case HttpRequest.METHOD_GET:
                    DebugUtil.debugHttpRequest(TAG, mId+"  METHOD_GET" + request.url);

                    requestHandle = client.get(mContext, request.url,
                            HttpHelper.getDefalutHeaders(), HttpRequest.requestParams,
                            getResponseHandler());
                    break;
                default:
                    throw new UnsupportedOperationException("method not supported");
            }
            if (!requestHandle.isFinished()) {
                mResponse.status = ResponseStatus.RESPONSE_WAITING;
            }

            if (requestHandle.isFinished()) {
                return mResponse;
            } else {
                return null;
            }

        }

        catch (Exception e) {
            mException = e;
             e.printStackTrace();
        }

        finally {
            if (!request.isResponseBinary && respStream != null)
                try {
                    respStream.close();
                } catch (IOException e) {// ignore
                    e.printStackTrace();
                }
        }
        return null;
    }
}
