package com.kekelian.net;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sky on 2018/8/16.
 */
public class HttpClient {
   public static final String TAG="HttpClient";
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    /**
     * post请求
     *
     * @param tag
     * @param url
     * @param map
     * @param callBack
     */
    public static void post(@NonNull Object tag, @NonNull final String url, @NonNull HashMap<String, String> map, @NonNull final CallBack callBack) {


        FormBody.Builder builder = new FormBody.Builder();
        StringBuilder postDataStr = new StringBuilder();
        if (map != null) {
            for (String s : map.keySet()) {
                builder.add(s, map.get(s));
                postDataStr.append("[ key = ").append(s).append(",").append("value=").append(map.get(s)).append("]");
            }
        }
        RequestBody requestBody = builder.build();

        final Request request = new Request.Builder()
                .tag(tag)
                .url(url)
                .post(requestBody)
                .build();
        Log.d(TAG, "post:url:" + url + " postData:" + postDataStr.toString());
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getLocalizedMessage() + "error url:"
                                + url);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
               Log.i(TAG,"返回的json数据："+json);
                final String finalJson = json;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("success");
                            callBack.onSuccess(new Gson().fromJson(finalJson, callBack.type));
                        } catch (Exception e) {
                            e.printStackTrace();
                            callBack.onFailure(e.getLocalizedMessage() + "error url:"
                                    + url);
                        }

                    }
                });

            }
        });
    }



    public static void get(@NonNull final Object tag, @NonNull final String url, @NonNull final CallBack callBack) {
        Request request = new Request.Builder()
                .tag(tag)
                .url(url)
                .build();
        Log.d(TAG,"get:url:" + url);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getLocalizedMessage() + "error url:"
                                + url);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String json = response.body().string();
                final String finalJson = json;
                Log.i(TAG,"返回的json数据："+json);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            callBack.onSuccess(new Gson().fromJson(finalJson, callBack.type));

                        } catch (Exception e) {
                            callBack.onFailure(e.getLocalizedMessage() + "  error url:"
                                    + url);
                        }
                    }
                });

            }
        });

    }


}
