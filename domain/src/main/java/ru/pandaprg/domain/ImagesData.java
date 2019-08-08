package ru.pandaprg.domain;

import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.Location;

public class ImagesData {
    private String imageURL;
    private String imageDate;
    private Location location;
    private double distance;
    private double azimut;

    public ImagesData(String imageURL, String imageDate, Location location, double myLat, double myLng) {
        this.imageDate = imageDate;
        this.imageURL = imageURL;
        this.location = location;
        calcDistance(myLat, myLng);
        calcAzimut(myLat, myLng);
    }

    public void setImageURL (String imageURL){this.imageURL = imageURL;}
    public String getImageURL () {return imageURL;}

    public void setImageDate (String imageDate){this.imageDate = imageDate;}
    public String getImageDate () {return imageDate;}

    public void setLocation (Location location){this.location = location;}
    public Location getLocation () {return location;}


    public double calcDistance(double myLat, double myLng){
        // TODO refactor calc

        final double R = 6372795; // R Eath metres

        double imageLat = Math.toRadians(location.getLat());
        double imageLng = Math.toRadians(location.getLng());

        myLat = Math.toRadians(myLat);
        myLng = Math.toRadians(myLng);

        double deltaLat = (imageLat-myLat);
        double deltaLng = (imageLng-myLng);

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                Math.cos(myLat) * Math.cos(imageLat) *
                        Math.sin(deltaLng/2) * Math.sin(deltaLng/2);


        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        distance = R * c;

        return distance;
    }

    public double getDistance (){
        return distance;
    }

    public double calcAzimut (double myLat, double myLng){
        // TODO check calculate Azimut

        double imageLat = Math.toRadians(location.getLat());
        double imageLng = Math.toRadians(location.getLng());

        myLat = Math.toRadians(myLat);
        myLng = Math.toRadians(myLng);

       double deltaLng = (imageLng-myLng);

        double p = Math.sin(deltaLng)* Math.cos(imageLat);

        double q = Math.cos(myLat)*Math.sin(imageLat) - Math.sin(myLat)*Math.cos(imageLat)*Math.cos(deltaLng);

        azimut = Math.atan(p/q);

        return  azimut;
    }

    public double getAzimut (){
        return azimut;
    }
}
