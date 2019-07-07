package ru.pandaprg.navigator.Hardware.GPS;

import ru.pandaprg.navigator.Hardware.HardwareContract;
import ru.pandaprg.navigator.Hardware.HardwareData;

public interface GPSContract extends HardwareContract {
    void onChange(GPSData data);

    @Override
    void onChange(HardwareData data);

    @Override
    void Change(HardwareData data);
}
