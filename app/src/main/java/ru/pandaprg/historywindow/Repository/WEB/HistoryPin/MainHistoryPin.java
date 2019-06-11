package ru.pandaprg.historywindow.Repository.WEB.HistoryPin;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.POJOUserGallery;

public class MainHistoryPin {

        public MainHistoryPin () {

            String TAG = "testRetrofit";


            double lat_lo = 39.179422;
            double lng_lo = 19.928202;
            double lat_hi = 45.987757;
            double lng_hi = 26.618876;
            String bounds = "" + lat_lo + "," + lng_lo +","+lat_hi+","+lng_hi;
            String sort = "popular";
            int paging = 1;

            String user = ""+62116;


            NetworkService.getInstance("https://www.historypin.org")
                    .getHistoryPinAPI()
                    .getUserGallery(user)
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


                                     if (gallery != null) {
                                         // textView.append(response.body().toString());
                                         Log.d(TAG, gallery.getLimit() + "\n");
                                         Log.d(TAG, gallery.getCount() + "\n");
                                         Log.d(TAG, gallery.getPage() + "\n");

                                     } else {
                                         Log.d(TAG, "NO Data");
                                     }
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
}
