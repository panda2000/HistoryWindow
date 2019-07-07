package ru.pandaprg.historywindow.Model;

import android.content.Context;

import java.util.Date;
import java.util.List;

import ru.pandaprg.historywindow.Main.MainPresenter;

public class Model {

    // TODO refactor Model

    private static Model model;
    private MainPresenter presenter;
    private Context ctx;

    private double myLocationLat;
    private double myLocationLng;
    private Date myLocationTime;

    private RequestParameters parameters;

    private List<ImagesData> imagesArray;

    String imageURL=null;
    double distance=0;
    double imageAzimut=0;

    private float currentDeg;
    private float oldDeg;

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

    public void setMyAccelerometr (long xy, long xz, long yz){

        oldDeg = currentDeg;
        currentDeg = (float) (xy * (-1) + imageAzimut);

        if (Math.abs(oldDeg - currentDeg) > 1 && distance > 10) {
            presenter.showArrow();
            presenter.onRotation(oldDeg, currentDeg);
        }
    }

    public double getMyLocationLat (){return myLocationLat;}

    public double getMyLocationLng() {return myLocationLng;}

    public RequestParameters getParameters() {
        return parameters;
    }

    public void findPictures (List <ImagesData> gallery){

        if(gallery!= null) {

            imagesArray = gallery;

            distance = 0;

            if (!gallery.isEmpty()) {
                if (gallery.get(0).getImageURL() != null) {
                    imageURL = gallery.get(0).getImageURL();
                    distance = gallery.get(0).getDistance();
                    imageAzimut = gallery.get(0).getAzimut();
                }
            }

            if (imageURL != null ) {
                if (distance < 10) {
                    presenter.showImage(imageURL);
                    presenter.onRotation(currentDeg, 0);
                }
                presenter.showMessage("Дистанция "+ distance + "м.");
                presenter.showArrow();
            } else {
                presenter.showMessage("Изображение не обнаружено");
            }
        } else {
            imageURL = null;
            presenter.showMessage("Изображение не обнаружено");
        }
    }
}
