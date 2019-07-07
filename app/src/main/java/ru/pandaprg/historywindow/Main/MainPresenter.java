package ru.pandaprg.historywindow.Main;

import android.content.Context;
import android.util.Log;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ru.pandaprg.historywindow.Base.Presenter.BasePresenter;
import ru.pandaprg.historywindow.Hardware.GPS.MainGPS;
import ru.pandaprg.historywindow.Model.ImagesData;
import ru.pandaprg.historywindow.Model.Model;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.MainHistoryPin;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.POJOUserGallery;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.Result;
import ru.pandaprg.navigator.Hardware.Accelerometr.AcceleromertContract;
import ru.pandaprg.navigator.Hardware.Accelerometr.AccelerometrData;
import ru.pandaprg.navigator.Hardware.Accelerometr.MainAccelerometer;
import ru.pandaprg.navigator.Hardware.HardwareData;

public class MainPresenter extends BasePresenter implements AcceleromertContract {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    private Context ctx;

    private MainGPS gps;
    private MainAccelerometer accel;

    private MainHistoryPin historyPin;

    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(ctx, this);

        // --------------- Для GPS --------------------------------
        gps = new MainGPS(ctx, this);
        gps.onResume();

        accel = new MainAccelerometer(ctx, this);
        accel.onResume();

        //------------------- Для WEB HistoryPin-------------------
        // myLat = 46.3757;
        //            myLng = 48.0485;


    }

    public void onGPSLocation(double lat, double lng, Date time){

        model.setMyLocation(lat, lng, time);

        //historyPin = new MainHistoryPin(this, 46.3757, 48.0485);
        historyPin = new MainHistoryPin(this, model.getParameters());

        if (isAttached()) {
            String mess = String.format("Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT", lat, lng, time);
            ((MainActivity)view).showGPSLocation(mess);
            Log.i (TAG,mess);
        } else {
            Log.e (TAG,"view is null");
        }
    }

//----------------------- Accelerometr -----------------------------
    @Override
    public void onChange(AccelerometrData data) {
        long xy = data.getXy();
        long xz = data.getXz();
        long yz = data.getYz();

        //Log.i(TAG, data.getXy()+ "  "+data.getXz()+"  "+data.getYz());

        model.setMyAccelerometr(xy, xz, yz);

        ((MainActivity)view).showAccelerometerData(String.valueOf(xy), String.valueOf(xz), String.valueOf(yz) );
    }

    @Override
    public void onChange(HardwareData data) {
        Log.i(TAG, "HardwareData");
    }

    @Override
    public void Change(HardwareData data) {
        Log.i(TAG, "HardwareData");
    }

    /*
    public void onAccelerometerChange (){
        model.setMyAccelerometr(xy, xz, yz);
        ((MainActivity)view).showAccelerometerData(String.valueOf(xy), String.valueOf(xz), String.valueOf(yz) );
    }
    */
//--------------------------------------------------------------------
    public void onRotation (float oldDeg, float currentDeg){
        Log.i(TAG, "Rotation from " + oldDeg + " to " + currentDeg);
        ((MainActivity)view).rotationPicture(oldDeg, currentDeg);
    }

    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }

    public void onHistoryPinPictureFind(POJOUserGallery gallery){
        List <ImagesData> imagesData = convertHystoryPin2Model(gallery);
        model.findPictures(imagesData);


    }

    public  void onPictureFind (String imageURL){
        showImage(imageURL);
    }

    public void showImage (String imageURL){
        ((MainActivity)view).showPicture(imageURL);
    }
    public void showArrow (){
        ((MainActivity)view).showArrow();
    }

    public void showMessage (String mess) {((MainActivity)view).showMessage(mess);}

    private LinkedList <ImagesData> convertHystoryPin2Model (POJOUserGallery gallery) {

        LinkedList <ImagesData> imagesData = new LinkedList  <ImagesData> ();
        ImagesData iData = null;

        if (gallery != null ) {

            if (Integer.parseInt(String.valueOf(gallery.getCount())) > 0) {
                ((MainActivity)view).showMessage("Picture found");

                List<Result> results = gallery.getResults();

                double myLat = model.getMyLocationLat();
                double myLng = model.getMyLocationLng();

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

    public void onHistoryPinPictureNotFind(){
        ((MainActivity)view).showMessage("Picture not found");
        ((MainActivity)view).hidePicture();
    }


}
