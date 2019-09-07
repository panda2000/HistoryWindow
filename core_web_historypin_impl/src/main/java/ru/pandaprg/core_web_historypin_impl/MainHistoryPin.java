package ru.pandaprg.core_web_historypin_impl;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pandaprg.core_web_historypin_impl.POJO.UserGallery.POJOUserGallery;

public class MainHistoryPin {

       // MainPresenter presenter;


        private String user;

        public MainHistoryPin (RequestParameters parameters) {

            String TAG = "testRetrofit";

        //    this.presenter = presenter;

           // My Test Photo
            user = parameters.getUser();


            double lat_lo = parameters.getBounds().getLat_lo();
            double lng_lo = parameters.getBounds().getLng_lo();
            double lat_hi = parameters.getBounds().getLat_hi();
            double lng_hi = parameters.getBounds().getLng_hi();

            String bounds = "" + lat_lo + "," + lng_lo +","+lat_hi+","+lng_hi;



            NetworkService.getInstance("https://www.historypin.org")
                    .getHistoryPinAPI()
                    .getUserGallery(user, bounds)
                    .enqueue(new Callback<POJOUserGallery>() {

                                 @Override
                                 public void onResponse(@NonNull Call<POJOUserGallery> call, @NonNull Response<POJOUserGallery> response) {

                                     String TAG = "testRetrofit";

                                     //  if (response.code() == 400 ) {
                                     Log.d(TAG, "onResponse Call - Message : " + call.request().toString());
                                     Log.d(TAG, "onResponse Call- URL : " + call.request().url());
                                     Log.d(TAG, "onResponse - Status : " + response.code());
                                     //  }

                                     POJOUserGallery gallery = response.body();


                                    // presenter.onHistoryPinPictureFind(gallery);

                                 }

                                 @Override
                                 public void onFailure(@NonNull Call<POJOUserGallery> call, @NonNull Throwable t) {

                                     String TAG = "testRetrofit";

                                     Log.e(TAG, "Error occurred while getting request!");

                                     Log.d(TAG, "onResponse Call - Message : " + call.request().toString());
                                     Log.d(TAG, "onResponse Call- URL : " + call.request().url());
                                     Log.d(TAG,t.getMessage());

                                     t.printStackTrace();
                                 }
                             }
                    );
        }

        /* TODO Create CallbackRegister
    @Override
    public void registerCallBack( callback) {
        this.callback = callback;
    }
    */
}
