package ru.pandaprg.historywindow.Hardware;

import android.util.Log;

import ru.pandaprg.core_accelerometr_impl.AccelerometrData;
import ru.pandaprg.core_hardware_api.HardwareDataContract;
import ru.pandaprg.core_hardware_api.HardwareReciveDataCallback;
import ru.pandaprg.historywindow.Model.Model;

public class AccelerometrReciver implements HardwareReciveDataCallback {

    private static final String TAG = "AccelerometrReciver";

    Model model;

    public AccelerometrReciver (Model model){
        this.model = model;
    }

    @Override
    public void onRecive(HardwareDataContract data) {

        String mess = " " + ((AccelerometrData)data).getXy();
        mess += "  " + ((AccelerometrData)data).getXz();
        mess += "  " + ((AccelerometrData)data).getYz();
        Log.i(TAG, mess);

        long xy = ((AccelerometrData)data).getXy();
        long xz = ((AccelerometrData)data).getXz();
        long yz = ((AccelerometrData)data).getYz();

        ((Model)model).setMyAccelerometr(xy, xz, yz);

    }
}
