package ru.pandaprg.historywindow.Main;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import ru.pandaprg.historywindow.Base.Presenter.BasePresenter;
import ru.pandaprg.historywindow.Hardware.Accelerometr.MainAccelerometer;
import ru.pandaprg.historywindow.Hardware.GPS.MainGPS;
import ru.pandaprg.historywindow.Model.Model;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.MainHistoryPin;

public class MainPresenter extends BasePresenter {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    Context ctx;

    private MainGPS gps;
    private MainAccelerometer accel;

    private MainHistoryPin historyPin;

    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(ctx);

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

    public void onAccelerometerChange (long xy, long xz, long yz){
        model.setMyAccelerometr(xy, xz, yz);
        ((MainActivity)view).showAccelerometerData(String.valueOf(xy), String.valueOf(xz), String.valueOf(yz) );
    }

    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }

    public void onHistoryPinPictureFind(String imageURL){
        ((MainActivity)view).showMessage("Picture found");
        ((MainActivity)view).showPicture(imageURL);

    }

    public void onHistoryPinPictureNotFind(){
        ((MainActivity)view).showMessage("Picture not found");
        ((MainActivity)view).hidePicture();
    }

}
