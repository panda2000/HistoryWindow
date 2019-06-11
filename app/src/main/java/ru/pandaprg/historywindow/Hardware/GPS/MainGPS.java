package ru.pandaprg.historywindow.Hardware.GPS;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import java.util.Date;

import ru.pandaprg.historywindow.Main.MainPresenter;
import ru.pandaprg.historywindow.Repository.RepositoryInterface;

public class MainGPS implements RepositoryInterface {

    private final String TAG = "MainGPS";

    private LocationManager locationManager;
    private Context ctx;

    private MainPresenter presenter;

    public MainGPS(Context ctx, MainPresenter presenter) {
        this.ctx = ctx;
        this.presenter = presenter;
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onCreate(Context ctx) {

    }

    @Override
    public void onResume() {
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                0,
                locationListener);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                1000,
                0,
                locationListener);
    }

    @Override
    public void onPause() {
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            checkEnabled();
        }

        @Override
        public void onProviderEnabled(String provider) {
            checkEnabled();
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            showLocation(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void showLocation(Location location) {
        if (location == null)
            return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
           Log.d(TAG,formatLocation(location));
        } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
           Log.d(TAG,formatLocation(location));
        }
        presenter.onGPSLocation(location.getLatitude(), location.getLongitude(),new Date(location.getTime()));
    }

    private String formatLocation(Location location) {
        if (location == null)
            return "";
        return String.format(
                "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                location.getLatitude(), location.getLongitude(), new Date(
                        location.getTime()));
    }

    private void checkEnabled() {
        Log.d(TAG,"Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER));
        Log.d(TAG,"Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER));

    }

    public void onClickLocationSettings(View view) {
        ctx.startActivity(new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    };

}
