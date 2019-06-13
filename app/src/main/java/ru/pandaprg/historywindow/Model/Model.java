package ru.pandaprg.historywindow.Model;

import android.content.Context;

import java.util.Date;

public class Model {

    private static Model model;
    Context ctx;

    private double myLocationLat;
    private double myLocationLng;
    private Date myLocationTime;

    RequestParameters parameters;

    private Model (Context ctx) {
        this.ctx = ctx;
    }

    public static Model getInstanse (Context ctx){
        if (model == null){
            model = new Model (ctx);
        }
        return model;
    }

    public void setMyLocation(double lat, double lng, Date time){
        myLocationLat = lat;
        myLocationLng = lng;
        myLocationTime = time;

        parameters = new RequestParameters(lat,lng);
    }

    public double getMyLocationLat (){return myLocationLat;}

    public double getMyLocationLng() {return myLocationLng;}

    public RequestParameters getParameters() {
        return parameters;
    }
}
