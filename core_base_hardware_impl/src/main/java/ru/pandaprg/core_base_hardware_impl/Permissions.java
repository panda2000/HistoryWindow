package ru.pandaprg.core_base_hardware_impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.pandaprg.core_hardware_api.PermissionsContract;

public class Permissions implements PermissionsContract, ActivityCompat.OnRequestPermissionsResultCallback {

    private String TAG = "Navigator.Permissions";

    //region Marshmellows permissions
    // TODO Test add permissions

    Activity activity;
    Context ctx;

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;


    public void CheckPermissions(Context ctx, Activity activity) {
        this.activity = activity;
        this.ctx = ctx;

        if (activity == null) {
            Log.d(TAG, "CheckPermissions: activity is null");
        } else {
            checkAndAddPermission();
        }
    }

    private void checkAndAddPermission() {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<>();
        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");

        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("Coarse");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                Log.i(TAG, message);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(ctx, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (activity == null)
                Log.d(TAG, "addPermission: activity is null");
            if (permission == null)
                Log.d(TAG, "addPermission: permission is null");
            if (!activity.shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);

                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    startBackgroundServiceForLocationUpdate();
                } else {
                    // Permission Denied
                    Toast.makeText(activity, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startBackgroundServiceForLocationUpdate() {

    }
}
