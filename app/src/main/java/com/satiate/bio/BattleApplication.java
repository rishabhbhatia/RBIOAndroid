package com.satiate.bio;

import android.app.Application;

import com.satiate.bio.utils.Const;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rishabh Bhatia on 10/6/2016.
 */

public class BattleApplication extends Application {

    private OkHttpClient.Builder httpClient;
    private Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(Const.RETROFIT_NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(Const.RETROFIT_NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofit = new Retrofit.Builder()
                .baseUrl(Const.NETWORK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
