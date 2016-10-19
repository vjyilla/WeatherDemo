package com.wipro.weatherdemo.common.io;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;

/**
 * Created by illa on 19-10-2016.
 */
public class HttpHelper {
    public static final String[][] headersDefault = {
            { "Content-Type", "application/json" }
    };

    public static Header[] getDefalutHeaders() {
        Header[] headers= new Header[headersDefault.length] ;
        for (int i=0;i<headersDefault.length;i++)
        {
            final String[] stHeaderDefault=headersDefault[i];
            Header header=new Header() {

                @Override
                public String getValue() {
                    // TODO Auto-generated method stub
                    return stHeaderDefault[1];
                }

                @Override
                public String getName() {
                    // TODO Auto-generated method stub
                    return stHeaderDefault[0];
                }

                @Override
                public HeaderElement[] getElements() throws ParseException {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
            headers[i]=header;
        }

        return headers;
    }

}
