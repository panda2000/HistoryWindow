package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("i")
    @Expose
    private I i;
    @SerializedName("c")
    @Expose
    private C c;

    public I getI() {
        return i;
    }

    public void setI(I i) {
        this.i = i;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

}
