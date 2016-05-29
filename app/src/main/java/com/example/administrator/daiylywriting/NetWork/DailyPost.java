package com.example.administrator.daiylywriting.NetWork;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2015/3/11.
 */
public class DailyPost {
//    private static final String BASE_URL = "http://120.24.169.141:8000/123/";
   private static final String BASE_URL = "http://120.24.169.141:8000/123/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post( RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(25000);
        client.post(BASE_URL, params, responseHandler);
    }

}
