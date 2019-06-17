package ru.pandaprg.historywindow.Repository.WEB.HistoryPin;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pandaprg.historywindow.Main.MainPresenter;
import ru.pandaprg.historywindow.Model.RequestParameters;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.POJOUserGallery;

public class MainHistoryPin {

        MainPresenter presenter;


        private String user;

        public MainHistoryPin (final MainPresenter presenter, RequestParameters parameters) {

            String TAG = "testRetrofit";

            this.presenter = presenter;

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


                                     if (gallery != null ) {
                                         if (Integer.parseInt(String.valueOf(gallery.getCount())) > 0) {
                                             // textView.append(response.body().toString());
                                             String imageURL;
                                             int resultID = 0;

                                             if (gallery.getResults().get(resultID).getImage() != null) {
                                                 imageURL = gallery.getResults().get(resultID).getImage().toString();
                                                 presenter.onHistoryPinPictureFind(imageURL, gallery);
                                             }
                                             else
                                                 imageURL = gallery.getResults().get(resultID).getImages().get(0).getUrl().toString();

                                             Log.d(TAG, imageURL + "\n");
                                             Log.d(TAG, gallery.getResults().get(resultID).getCaption() + "\n");
                                             Log.d(TAG, gallery.getResults().get(resultID).getDesc() + "\n");


                                         }
                                         else
                                            presenter.onHistoryPinPictureNotFind();

                                     } else {
                                         Log.d(TAG, "NO Data");
                                         presenter.onHistoryPinPictureNotFind();
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
