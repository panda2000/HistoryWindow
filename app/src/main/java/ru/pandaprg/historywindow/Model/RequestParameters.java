package ru.pandaprg.historywindow.Model;

public class RequestParameters {
    private String user = "" + 101705;
    private Bounds bounds;
    private double delta = 0.001;

    private double myLat;
    private double myLng;

    public RequestParameters( double myLat, double myLng) {
        this.myLat = myLat;
        this.myLng = myLng;
        bounds = new Bounds();
    }

    public void setMyLocation(double myLat, double myLng) {
        this.myLat = myLat;
        this.myLng = myLng;
        bounds = new Bounds();
    }

    public void setDelta(double delta) {
        this.delta = delta;
        bounds = new Bounds();
    }

    public String getUser() {
        return user;
    }

    public Bounds getBounds() {
        return bounds;
    }


    public class Bounds {
        private double lat_lo;
        private double lng_lo;
        private double lat_hi;
        private double lng_hi;

        public Bounds() {
            lat_lo = myLat - delta;
            lng_lo = myLng - delta;
            lat_hi = myLat + delta;
            lng_hi = myLng + delta;

        }

        public double getLat_hi() {
            return lat_hi;
        }

        public double getLat_lo() {
            return lat_lo;
        }

        public double getLng_lo() {
            return lng_lo;
        }

        public double getLng_hi() {
            return lng_hi;
        }
    }
}