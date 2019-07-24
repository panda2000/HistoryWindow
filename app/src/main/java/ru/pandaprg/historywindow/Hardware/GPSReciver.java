package ru.pandaprg.historywindow.Hardware;

import android.util.Log;

import java.util.Date;

import ru.pandaprg.core_gps_api.GPSDataContract;
import ru.pandaprg.core_gps_impl.GPSData;
import ru.pandaprg.core_hardware_api.HardwareDataContract;
import ru.pandaprg.core_hardware_api.HardwareReciveDataCallback;
import ru.pandaprg.historywindow.Model.Model;

public class GPSReciver implements HardwareReciveDataCallback {

    private static final String TAG = "GPSReciver";

    private GPSDataContract data;

    private Model model;

    public GPSReciver (Model model) {
        this.model = model;
    }

    @Override
    public void onRecive(HardwareDataContract data) {

        this.data = (GPSData)data;

        String mess = " Lat:" + ((GPSData)data).getLat() ;
        mess += " Lng:" + ((GPSData)data).getLng();
        mess += " Time :" + ((GPSData)data).getTime();
        Log.i(TAG, "onRecive: " + mess);

        double lat = ((GPSData)data).getLat();
        double lng = ((GPSData)data).getLng();
        Date time = ((GPSData)data).getTime();

        model.setMyLocation(lat, lng, time);
    }
}
