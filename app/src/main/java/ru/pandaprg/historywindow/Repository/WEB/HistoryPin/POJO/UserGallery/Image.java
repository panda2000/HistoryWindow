package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("pin_class")
    @Expose
    private String pinClass;
    @SerializedName("media_url")
    @Expose
    private String mediaUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPinClass() {
        return pinClass;
    }

    public void setPinClass(String pinClass) {
        this.pinClass = pinClass;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

}
