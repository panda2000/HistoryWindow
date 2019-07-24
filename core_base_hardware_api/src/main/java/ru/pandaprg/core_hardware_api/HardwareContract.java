package ru.pandaprg.core_hardware_api;

public interface HardwareContract {
    // TODO renabe to *able
    void registerCallBack (HardwareReciveDataCallback callback);
    void Change(HardwareDataContract data);
}
