package ru.pandaprg.navigator.Hardware.Accelerometr;

import ru.pandaprg.navigator.Hardware.HardwareContract;
import ru.pandaprg.navigator.Hardware.HardwareData;

public interface AcceleromertContract extends HardwareContract {
    void onChange(AccelerometrData data);

    @Override
    void onChange(HardwareData data);

    @Override
    void Change(HardwareData data);
}
