package ru.pandaprg.historywindow.Main;

import android.content.Context;

import ru.pandaprg.baselibrary.Presenter.BasePresenter;
import ru.pandaprg.core_accelerometr_impl.MainAccelerometer;
import ru.pandaprg.core_gps_impl.MainGPS;
import ru.pandaprg.core_hardware_api.HardwareContract;
import ru.pandaprg.historywindow.Hardware.AccelerometrReciver;
import ru.pandaprg.historywindow.Hardware.GPSReciver;
import ru.pandaprg.historywindow.Model.Model;

public class MainPresenter extends BasePresenter {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    private Context ctx;

    private HardwareContract gps;
    private GPSReciver gpsReciver;

    private HardwareContract accel;
    private AccelerometrReciver accelerometrReciver;



    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(this);


        // --------------- Start GPS and Accelerometr --------------------------------
        gps = new MainGPS(ctx);
        gpsReciver =new GPSReciver(model);
        gps.registerCallBack(gpsReciver);


        accel = new MainAccelerometer(ctx);
        accelerometrReciver = new AccelerometrReciver(model);
        accel.registerCallBack(accelerometrReciver);

         //------------------- Для WEB HistoryPin-------------------
        // myLat = 46.3757;
        // myLng = 48.0485;


    }


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
