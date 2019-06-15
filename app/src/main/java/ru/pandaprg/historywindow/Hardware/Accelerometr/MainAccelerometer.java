package ru.pandaprg.historywindow.Hardware.Accelerometr;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import ru.pandaprg.historywindow.Main.MainPresenter;
import ru.pandaprg.historywindow.Repository.RepositoryInterface;

public class MainAccelerometer implements RepositoryInterface, SensorEventListener {
    private final String TAG = "MainAccelerometr";

    private Context ctx;
    private SensorManager sensorManager;

    private float[] rotationMatrix;     //Матрица поворота
    private float[] accelData;           //Данные с акселерометра
    private float[] magnetData;       //Данные геомагнитного датчика
    private float[] OrientationData; //Матрица положения в пространстве

    private MainPresenter presenter;

    public MainAccelerometer(Context ctx, MainPresenter presenter) {
        this.ctx = ctx;
        this.presenter = presenter;
        sensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];
    }


    @Override
    public void onCreate(Context ctx) {

    }

    @Override
    public void onResume() {
        sensorManager.registerListener( (SensorEventListener)this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
        sensorManager.registerListener( (SensorEventListener)this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );
    }

    @Override
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

        presenter.onAccelerometerChange(
                Math.round(Math.toDegrees(OrientationData[0])),     //xy
                Math.round(Math.toDegrees(OrientationData[1])),     //xz
                Math.round(Math.toDegrees(OrientationData[2])));    //zy

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
