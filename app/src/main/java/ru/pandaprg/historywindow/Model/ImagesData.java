package ru.pandaprg.historywindow.Model;

import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.Location;

public class ImagesData {
    private String imageURL;
    private String imageDate;
    private Location location;
    private double distance;

    public ImagesData (String imageURL, String imageDate, Location location,double myLat, double myLng) {
        this.imageDate = imageDate;
        this.imageURL = imageURL;
        this.location = location;
        this.distance = calcDistance(myLat, myLng);
    }

    public void setImageURL (String imageURL){this.imageURL = imageURL;}
    public String getImageURL () {return imageURL;}

    public void setImageDate (String imageDate){this.imageDate = imageDate;}
    public String getImageDate () {return imageDate;}

    public void setLocation (Location location){this.location = location;}
    public Location getLocation () {return location;}


    public double calcDistance(double myLat, double myLng){
        // TODO calc distance

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
}
