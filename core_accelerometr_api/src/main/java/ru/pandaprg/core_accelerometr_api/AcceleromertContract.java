package ru.pandaprg.core_accelerometr_api;


import ru.pandaprg.core_hardware_api.HardwareContract;
import ru.pandaprg.core_hardware_api.HardwareDataContract;

public interface AcceleromertContract extends HardwareContract {
    void onChange(AccelerometrDataContract data);

    @Override
    void onChange(HardwareDataContract data);

    @Override
    void Change(HardwareDataContract data);
}
