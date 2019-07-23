package ru.pandaprg.core_accelerometr_impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import ru.pandaprg.core_accelerometr_api.AcceleromertContract;
import ru.pandaprg.core_hardware_api.HardwareContract;
import ru.pandaprg.core_hardware_api.HardwareDataContract;


public class MainAccelerometer implements SensorEventListener, AcceleromertContract {

    private final String TAG = "MainAccelerometr";

    private Context ctx;
    private SensorManager sensorManager;

    private float[] rotationMatrix;     //Матрица поворота
    private float[] accelData;           //Данные с акселерометра
    private float[] magnetData;       //Данные геомагнитного датчика
    private float[] OrientationData; //Матрица положения в пространстве

    //private MainPresenter presenter;
    private HardwareContract contract;

    public MainAccelerometer(Context ctx, HardwareContract contract) {
        this.ctx = ctx;
        this.contract = (AcceleromertContract) contract;   // привязываем объект реализующий контракт
        sensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];
        onResume();
    }


    //@Override
    public void onCreate(Context ctx) {

    }

    //@Override
    public void onResume() {
        sensorManager.registerListener( (SensorEventListener)this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
        sensorManager.registerListener( (SensorEventListener)this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );
    }

    //@Override
    public void onPause() {
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    private void loadNewSensorData(SensorEvent event) {
        final int type = event.sensor.getType(); //Определяем тип датчика
        if (type == Sensor.TYPE_ACCELEROMETER) { //Если акселерометр
            accelData = event.values.clone();
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) { //Если геомагнитный датчик
            magnetData = event.values.clone();
        }
    }

    public void onSensorChanged(SensorEvent event) {
        loadNewSensorData(event); // Получаем данные с датчика
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData); //Получаем матрицу поворота
        SensorManager.getOrientation(rotationMatrix, OrientationData); //Получаем данные ориентации устройства в пространстве

        AccelerometrData data = new AccelerometrData(
                Math.round(Math.toDegrees(OrientationData[0])),     //xy
                Math.round(Math.toDegrees(OrientationData[1])),     //xz
                Math.round(Math.toDegrees(OrientationData[2])));      //zy

        //Log.i(TAG, data.getXy()+ "  "+data.getXz()+"  "+data.getYz());

        contract.onChange((HardwareDataContract) data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onChange(HardwareDataContract data) {

    }

    @Override
    public void Change(HardwareDataContract data) {

    }
}
