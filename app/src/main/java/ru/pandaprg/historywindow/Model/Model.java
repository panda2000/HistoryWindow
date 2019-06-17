package ru.pandaprg.historywindow.Model;

import android.content.Context;

import java.util.Date;
import java.util.List;

import ru.pandaprg.historywindow.Main.MainPresenter;

public class Model {

    private static Model model;
    private MainPresenter presenter;
    private Context ctx;

    private double myLocationLat;
    private double myLocationLng;
    private Date myLocationTime;

    private RequestParameters parameters;

    private List<ImagesData> imagesArray;

    private Model (Context ctx, MainPresenter presenter) {
        this.ctx = ctx;
        this.presenter = presenter;
    }

    public static Model getInstanse (Context ctx, MainPresenter presenter){
        if (model == null){
            model = new Model (ctx, presenter);
        }
        return model;
    }

    public void setMyLocation(double lat, double lng, Date time){
        myLocationLat = lat;
        myLocationLng = lng;
        myLocationTime = time;

        parameters = new RequestParameters(lat,lng);
    }

    public void setMyAccelerometr (long xy, long xz, long yz){}

    public double getMyLocationLat (){return myLocationLat;}

    public double getMyLocationLng() {return myLocationLng;}

    public RequestParameters getParameters() {
        return parameters;
    }

    public void findPictures (List <ImagesData> gallery){

        if(gallery!= null) {

            imagesArray = gallery;

            String imageURL=null;
            double distance = 0;

            if (!gallery.isEmpty()) {
                if (gallery.get(0).getImageURL() != null) {
                    imageURL = gallery.get(0).getImageURL();
                    distance = gallery.get(0).getDistance();
                }
            }

            if (imageURL != null) {
                presenter.showImage(imageURL);
                presenter.showMessage("Дистанция "+ distance + "м.");
            }
        }
    }
}
