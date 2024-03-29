package ru.pandaprg.core_web_historypin_impl.POJO.Gallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("viewed")
    @Expose
    private Integer viewed;
    @SerializedName("comments")
    @Expose
    private Integer comments;

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

}
