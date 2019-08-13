package ru.pandaprg.historywindow.Coordinator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ru.pandaprg.feature_camera_impl.CameraActivity;
import ru.pandaprg.feature_compass2_impl.CompassActivity;
import ru.pandaprg.historywindow.R;


public class MainCoordinator extends BaseCoordinator {

    private static final String TAG = "MainCoordinator";

    public void showActivity (Context ctx, int id){
        Intent intent = null;
        switch (id) {
            case R.id.btn_open_camera1 :
                intent = new Intent(ctx, CameraActivity.class);
                break;
            case R.id.btnCompass:
                intent = new Intent(ctx, CompassActivity.class);
                break;
        }
        try {
            ctx.startActivity(intent);
        } catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }
}
