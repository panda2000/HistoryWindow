package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("zoom")
    @Expose
    private Integer zoom;
    @SerializedName("range")
    @Expose
    private Integer range;
    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("geo_tags")
    @Expose
    private String geoTags;
    @SerializedName("for_streetview")
    @Expose
    private Boolean forStreetview;
    @SerializedName("streetview")
    @Expose
    private ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.Gallery.Streetview streetview;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getGeoTags() {
        return geoTags;
    }

    public void setGeoTags(String geoTags) {
        this.geoTags = geoTags;
    }

    public Boolean getForStreetview() {
        return forStreetview;
    }

    public void setForStreetview(Boolean forStreetview) {
        this.forStreetview = forStreetview;
    }

    public ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.Gallery.Streetview getStreetview() {
        return streetview;
    }

    public void setStreetview(
            ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.Gallery.Streetview streetview) {
        this.streetview = streetview;
    }

}
