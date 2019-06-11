package ru.pandaprg.historywindow.Repository.WEB.HistoryPin;


//Singleton

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private Retrofit mRetrofit;
    private static String BASE_URL;

    public static NetworkService getInstance (String URL) {
        if (mInstance == null) {
            BASE_URL = URL;
            mInstance = new NetworkService();
        }

        return mInstance;
    }

    private NetworkService () {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public JSONHistoryPinAPI getHistoryPinAPI (){
        return mRetrofit.create(JSONHistoryPinAPI.class);
    }
}
