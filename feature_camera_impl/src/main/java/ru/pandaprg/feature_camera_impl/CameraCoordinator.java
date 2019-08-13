package ru.pandaprg.feature_camera_impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ru.pandaprg.baselibrary.Coordinator.BaseCoordinator;

public class CameraCoordinator extends BaseCoordinator {
    private static final String TAG = "CameraCoordinator";

    public CameraCoordinator(Context ctx) {
        super(ctx);
    }

    public void showActivity (int id){
        Intent intent = new Intent("ru.pandaprg.feature_compass2_impl.CompassActivity");
        intent.setPackage("ru.pandaprg.feature_compass2_impl");
        // Todo FIX NoActivity Error
        Log.d(TAG, "showActivity: "+intent.toString());

        try {
            super.ctx.startActivity(intent);
        } catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }
}
