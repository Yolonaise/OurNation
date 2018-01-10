package com.example.arnaudschaal.ournation.RestClient;

import com.example.arnaudschaal.ournation.BuildConfig;
import com.example.arnaudschaal.ournation.RestClient.Listeners.IClientListener;
import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.Session.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public class APIClient {
    public final String url = "http://clegascha.somee.com/";
    protected Retrofit retrofit;
    protected IClientListener listener;

    public APIClient(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                String token = Context.getInstance().getToken();
                Request request = original.newBuilder()
                        .addHeader("apikey", BuildConfig.MyzorinoApiKey)
                        .addHeader("token", token)
                        .build();

                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        this.listener = null;
    }

    public void setClientListener(IClientListener listener){
        this.listener = listener;
    }

    protected void dispatchListener(Response response, String method){
        if(response.body() == null)
            listener.onResponseErrorEnd(response.errorBody(), method);
        else
            listener.onResponseEnd((IJSONMessage) response.body(), method);
    }
}
