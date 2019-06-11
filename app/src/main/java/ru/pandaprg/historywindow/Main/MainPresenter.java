package ru.pandaprg.historywindow.Main;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import ru.pandaprg.historywindow.Base.Presenter.BasePresenter;
import ru.pandaprg.historywindow.Hardware.GPS.MainGPS;
import ru.pandaprg.historywindow.Model.Model;

public class MainPresenter extends BasePresenter {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    Context ctx;

    private MainGPS gps;

    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(ctx);

        // --------------- Для GPS ---------------------------------


        gps = new MainGPS(ctx, this);
        gps.onResume();


        //-----------------------------------------------------

    }

    public void onGPSLocation(double lat, double lng, Date time){
        if (isAttached()) {
            String mess = String.format("Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT", lat, lng, time);
            ((MainActivity)view).showGPSLocation(mess);
            Log.i (TAG,mess);
        } else {
            Log.e (TAG,"view is null");
        }
    }

    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }

}
