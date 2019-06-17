package ru.pandaprg.historywindow.Model;

import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.Gallery.Location;

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

        double R = 63772795; // R Eath metres
        /*
         φ1 = lat1.toRadians();
        var φ2 = lat2.toRadians();
        var Δφ = (lat2-lat1).toRadians();
        var Δλ = (lon2-lon1).toRadians();
*/
        double a = 0;
/*
        var a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);

*/
        double c = 1;
        // c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        distance = R * c;

        return distance;
    }

    public double getDistance (){
        return distance;
    }
}
