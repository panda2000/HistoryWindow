package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Streetview {

    @SerializedName("heading")
    @Expose
    private Double heading;
    @SerializedName("pitch")
    @Expose
    private Double pitch;
    @SerializedName("zoom")
    @Expose
    private Double zoom;
    @SerializedName("opacity")
    @Expose
    private Integer opacity;
    @SerializedName("params")
    @Expose
    private Params params;

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Double getZoom() {
        return zoom;
    }

    public void setZoom(Double zoom) {
        this.zoom = zoom;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

}
