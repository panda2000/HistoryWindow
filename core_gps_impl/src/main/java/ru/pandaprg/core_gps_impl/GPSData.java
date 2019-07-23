package ru.pandaprg.core_gps_impl;

import java.util.Date;

import ru.pandaprg.core_gps_api.GPSDataContract;

public class GPSData implements GPSDataContract {
    private double lat;
    private double lng;
    private Date time;

    public GPSData(double lat, double lng, Date time) {
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
