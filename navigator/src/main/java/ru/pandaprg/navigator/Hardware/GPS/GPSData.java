package ru.pandaprg.navigator.Hardware.GPS;

import java.util.Date;
import ru.pandaprg.navigator.Hardware.HardwareData;

public class GPSData extends HardwareData {
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
