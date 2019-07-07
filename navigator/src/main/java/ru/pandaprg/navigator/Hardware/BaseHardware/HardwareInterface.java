package ru.pandaprg.navigator.Hardware.BaseHardware;

import android.content.Context;

public interface HardwareInterface {
    public void onCreate(Context ctx);
    public void onResume();
    public void onPause();

}
