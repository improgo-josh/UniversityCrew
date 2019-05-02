package com.example.university;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {
    private static String APP_ID = "ji-kv-uni-crew";
    private static String SERVER_URL = "https://university-crew.herokuapp.com/parse";
    private static String MASTER_KEY = "JI_KV_UNI_CREW_MASTER_KEY";

    @Override
    public void onCreate() {
        super.onCreate();

        // Use for troubleshooting -- remove this line for production
//        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APP_ID) // should correspond to APP_ID env variable
                .clientKey(MASTER_KEY)  // set explicitly unless clientKey is explicitly configured on Parse server
//                .clientBuilder(builder)
                .server(SERVER_URL).build());

    }
}
