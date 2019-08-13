package ru.pandaprg.historywindow.WEB.HystoryPin;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import ru.pandaprg.core_base_web_api.WebReceivableCallback;
import ru.pandaprg.core_base_web_api.WebReceivableData;
import ru.pandaprg.core_web_historypin_impl.POJO.UserGallery.POJOUserGallery;
import ru.pandaprg.core_web_historypin_impl.POJO.UserGallery.Result;
import ru.pandaprg.historywindow.Model.ImagesData;
import ru.pandaprg.historywindow.Model.Model;

public class HystoryPinReciver implements WebReceivableCallback {
    private static final String TAG = "HystoryPinReciver";

    private Model model;

    public HystoryPinReciver (Model model) {this.model = model;}

     /*
    // TODO
    private LinkedList<ImagesData> convertHystoryPin2Model (POJOUserGallery gallery) { }
*/


    @Override
    public void onReceive(WebReceivableData data) {
        POJOUserGallery gallery = (POJOUserGallery) data;

        LinkedList<ImagesData> imagesData = new LinkedList  <ImagesData> ();
        ImagesData iData = null;

        if (gallery != null ) {

            if (Integer.parseInt(String.valueOf(gallery.getCount())) > 0) {
               // ((MainActivity)view).showMessage("Picture found");

                List<Result> results = gallery.getResults();

                double myLat = ((Model)model).getMyLocationLat();
                double myLng = ((Model)model).getMyLocationLng();

                for (Result res: results) {
                    Log.d(TAG, res.getImage());
                    Log.d(TAG, res.getDate());
                    Log.d(TAG, res.getLocation().getLat().toString());
                    Log.d(TAG, myLat+"");
                    Log.d(TAG, myLng+"");

                    iData = new ImagesData(res.getImage(),
                            res.getDate(),
                            res.getLocation(),
                            myLat,
                            myLng);

                    imagesData.add(iData);

                }


            }
            else {
                Log.d(TAG, "NO Data");
                //onHistoryPinPictureNotFind();
            }
        } else {
            Log.d(TAG, "NO Data");
            //onHistoryPinPictureNotFind();
        }

        // TODO move to model
       // return imagesData;
    }
}
