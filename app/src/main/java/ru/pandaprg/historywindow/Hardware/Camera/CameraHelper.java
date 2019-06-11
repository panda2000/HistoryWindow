package ru.pandaprg.historywindow.Hardware.Camera;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

public class CameraHelper {
    private final String LOG_TAG = "myCameraHelper";

    private CameraManager mCameraManager = null;
    private String mCameraID = null;
    private MyCameraDevice device = null;

    private TextureView textureView = null;
    SurfaceTexture texture;
    Surface surface;

    public CameraHelper(@NonNull CameraManager cameraManager, @NonNull String cameraID) {
        mCameraManager = cameraManager;
        mCameraID = cameraID;
        device = new MyCameraDevice(mCameraManager, mCameraID);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void viewFormatSize(int formatSize) { // Получения характеристик камеры
        CameraCharacteristics cc = null;
        try {
            cc = mCameraManager.getCameraCharacteristics(mCameraID); // Получения списка выходного формата, который поддерживает камера
            StreamConfigurationMap configurationMap = cc.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP); // Получения списка разрешений которые поддерживаются для формата jpeg
            Size [] sizesJPEG = configurationMap.getOutputSizes(ImageFormat.JPEG);
            if (sizesJPEG != null) {
                for (Size item:sizesJPEG) {
                    Log.i(LOG_TAG, "w:" + item.getWidth() + " h:" + item.getHeight());
                }
            } else {
                Log.e(LOG_TAG, "camera with id: "+mCameraID+" don`t support format: "+formatSize);
            }
        } catch (CameraAccessException e) {
            Log.e(LOG_TAG,e.getMessage()); //
            e.printStackTrace(); }
    }

    public void setTextureView(TextureView textureView) {
        this.textureView = textureView;
    }

    public void openCamera () {
        if (!device.isOpen())
            device.openCamera();



        try {
            Thread.sleep(1000); //Приостанавливает поток на 1 секунду для подготовки камеры
        } catch (Exception e) {

        }

        createCameraPreviewSession();   // Создаем окно просмотра
    }

    public void closeCamera () {
        if (device.isOpen())
            device.closeCamera();
    }

    private void createCameraPreviewSession() {
        try {
            texture = textureView.getSurfaceTexture();
            texture.setDefaultBufferSize(1920,1080);
            surface = new Surface(texture);
            device.createPreviewSession(surface);
        } catch (Exception e) {
            Log.e (LOG_TAG,"PreviewSession : " + e.getMessage());
        }
    }


}
