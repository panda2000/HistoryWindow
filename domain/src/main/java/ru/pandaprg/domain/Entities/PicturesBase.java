package ru.pandaprg.domain.Entities;

public class PicturesBase {
    private int ID;
    private double Lat;
    private double Lng;
    private String Name;
    private String Shref;

    public PicturesBase () {
        ID = 1;
        Lat = 50.2;
        Lng = 52.2;
        Name = "test";
        Shref = "http://test.com/0.jpg";
    }


}
