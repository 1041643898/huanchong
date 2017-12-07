package com.example.a666.petapp.model;


import com.example.a666.petapp.app.MyApplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 廖文博 on 2017/11/22.
 */

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;

    public OkHttpUtil() {
        okHttpClient=new OkHttpClient();
    }
    public static OkHttpUtil getIntens(){
        if (okHttpUtil==null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil==null){
                    okHttpUtil=new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }
    public void getShu(String url, final NoBent noBent){
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String s = e.toString();
                MyApplication.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noBent.getFail(s);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MyApplication.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noBent.getSucceed(string);
                    }
                });

            }
        });

    }
}
