package ru.pandaprg.core_web_historypin_api;

public interface RequestParametersContract {

    public void setMyLocation(double myLat, double myLng);

    public void setDelta(double delta);

    public String getUser();

    public Boundsable getBounds();

}
