package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class I {

    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("w")
    @Expose
    private Integer w;
    @SerializedName("h")
    @Expose
    private Integer h;

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

}
