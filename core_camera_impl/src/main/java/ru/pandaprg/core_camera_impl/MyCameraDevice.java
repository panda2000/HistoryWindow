package ru.pandaprg.core_camera_impl;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Surface;

import java.util.Arrays;

public class MyCameraDevice {
    private final String LOG_TAG = "myCameraDevice";

    private CameraDevice cameraDevice = null;
    private CameraManager cameraManager = null;
    String cameraID = null;
    CameraCaptureSession mSession = null;

    MyCameraDevice(@NonNull CameraManager cameraManager, @NonNull String cameraID) {
        this.cameraManager = cameraManager;
        this.cameraID = cameraID;
    }

    public boolean isOpen() {
        if (cameraDevice != null) {
            return true;
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    public void openCamera() {
        try {
            cameraManager.openCamera(cameraID, cameraCallback, null);
        } catch (CameraAccessException ex) {
            Log.e (LOG_TAG,  "openCamera :" +ex.getMessage());
        }
    }

    public void closeCamera() {
        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }

    }

    private CameraDevice.StateCallback cameraCallback = new CameraDevice.StateCallback() {
        @Override public void onOpened(CameraDevice camera) {
            cameraDevice = camera;
            Log.i(LOG_TAG, "Open camera with id:"+cameraDevice.getId());
        }

        @Override public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
            Log.i(LOG_TAG, "disconnect camera with id:"+cameraDevice.getId());
            cameraDevice = null;
        }

        @Override public void onError(CameraDevice camera, int error) {
            Log.i(LOG_TAG, "error! camera id:"+camera.getId()+" error:"+error);
        }
    };

    public void createPreviewSession (Surface surface){
        try {
            final CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            builder.addTarget(surface);
            cameraDevice.createCaptureSession( Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    mSession = session;
                    try {
                        mSession.setRepeatingRequest(builder.build(),null,null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) { }

            }, null );
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}
