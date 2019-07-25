package ru.pandaprg.core_camera_impl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.TextureView;

import ru.pandaprg.core_camera_api.MyCameraContract;

public class MyCamera implements MyCameraContract {

    //TODO : сделать горизонталькую ориентацию изображения для камеры

    private final String LOG_TAG = "myCamera";
    Context ctx;

    private int currentCamera = 0;
    public final int CAMERA1 = 0;
    public final int CAMERA2 = 1;
    CameraHelper [] myCameras = null;

    private CameraManager cameraManager = null;
    private String [] cameraList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCamera(Context ctx){
        this.ctx = ctx;

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        cameraManager = (CameraManager) ctx.getSystemService(Context.CAMERA_SERVICE);
        getMyCameraList();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String [] getMyCameraList () {
        try {
            cameraList = cameraManager.getCameraIdList(); // получаем список камер


            myCameras = new CameraHelper[cameraList.length];

            for (String cameraID : cameraList) {
                Log.i (LOG_TAG, "Camera ID"+cameraID);
                int id = Integer.parseInt(cameraID);

                //создаём обработчик для камеры
                myCameras[id] = new CameraHelper(cameraManager, cameraID);

                // выводим в лог информацию по камере
                myCameras[id].viewFormatSize(ImageFormat.JPEG);

            }

        } catch (CameraAccessException ex) {
            Log.e (LOG_TAG, ex.getMessage());
            ex.printStackTrace();
        }

        return cameraList;
    }

    public void setTextureView (TextureView textureView){
        if (cameraList != null)
            for (String cameraID : cameraList) {
                int id = Integer.parseInt(cameraID);
                myCameras[id].setTextureView(textureView);
            }
            else
                Log.e(LOG_TAG, "setTextureView() cameraList is null");
    }

    public void choiceCamera (int id){
        if (myCameras == null)
            Log.e(LOG_TAG, "myCameras array is null");
        if (id == 0) {
            myCameras[CAMERA2].closeCamera();
            if (myCameras[CAMERA1] != null) {
                myCameras[CAMERA1].openCamera();
                currentCamera = 0;
            }
        } else {
            myCameras[CAMERA1].closeCamera();
            if (myCameras[CAMERA2] != null) {
                    myCameras[CAMERA2].openCamera();
                currentCamera = 1;
            }
        }
    }



}
