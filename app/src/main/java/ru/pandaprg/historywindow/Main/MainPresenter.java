package ru.pandaprg.historywindow.Main;

import android.content.Context;

import ru.pandaprg.baselibrary.Presenter.BasePresenter;
import ru.pandaprg.core_accelerometr_impl.MainAccelerometer;
import ru.pandaprg.core_gps_impl.MainGPS;
import ru.pandaprg.core_hardware_api.HardwareContract;
import ru.pandaprg.historywindow.Hardware.AccelerometrReciver;
import ru.pandaprg.historywindow.Hardware.GPSReciver;
import ru.pandaprg.historywindow.Model.Model;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.MainHistoryPin;

public class MainPresenter extends BasePresenter {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    private Context ctx;

    private HardwareContract gps;
    private GPSReciver gpsReciver;

    private HardwareContract accel;
    private AccelerometrReciver accelerometrReciver;


    private MainHistoryPin historyPin;

    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(this);


        // --------------- Для GPS --------------------------------
        gps = new MainGPS(ctx);
        gpsReciver =new GPSReciver(model);
        gps.registerCallBack(gpsReciver);


        accel = new MainAccelerometer(ctx);
        accelerometrReciver = new AccelerometrReciver(model);
        accel.registerCallBack(accelerometrReciver);

         //------------------- Для WEB HistoryPin-------------------
        // myLat = 46.3757;
        //            myLng = 48.0485;


    }


//--------------------------------------------------------------------
/*
    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }

    public void onHistoryPinPictureFind(POJOUserGallery gallery){
        List <ImagesData> imagesData = convertHystoryPin2Model(gallery);
        ((Model)model).findPictures(imagesData);
    }

    public void onHistoryPinPictureNotFind(){
      //  ((MainActivity)view).showMessage("Picture not found");
      //  ((MainActivity)view).hidePicture();
    }

*/
//--------------------------------------------------------------------
/*
    public  void onPictureFind (String imageURL){
        showImage(imageURL);
    }

    public void showImage (String imageURL){
        ((MainActivity)view).showPicture(imageURL);
    }


    // TODO move to galleryLib ?
    private LinkedList<ImagesData> convertHystoryPin2Model (POJOUserGallery gallery) {

        LinkedList <ImagesData> imagesData = new LinkedList  <ImagesData> ();
        ImagesData iData = null;

        if (gallery != null ) {

            if (Integer.parseInt(String.valueOf(gallery.getCount())) > 0) {
                ((MainActivity)view).showMessage("Picture found");

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
            else
                onHistoryPinPictureNotFind();

        } else {
            Log.d(TAG, "NO Data");
            onHistoryPinPictureNotFind();
        }

        return imagesData;
    }
*/
    public void showMessage (String mess) {
        //((MainActivity)view).showMessage(mess);
        //
    }


    @Override
    public void onPause() {
        gps.onPause();
        accel.onPause();
    }

    @Override
    public void onResume() {
        gps.onResume();
        accel.onResume();
    }
}
