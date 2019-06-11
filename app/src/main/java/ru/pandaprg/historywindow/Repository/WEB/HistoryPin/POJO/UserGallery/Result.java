package ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("node_type")
    @Expose
    private String nodeType;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("pins_count")
    @Expose
    private String pinsCount;
    @SerializedName("sponsored")
    @Expose
    private Sponsored sponsored;
    @SerializedName("times_viewed")
    @Expose
    private Integer timesViewed;
    @SerializedName("on_explore")
    @Expose
    private Integer onExplore;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("edit_url")
    @Expose
    private String editUrl;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("project_item_id")
    @Expose
    private Integer projectItemId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pin_class")
    @Expose
    private String pinClass;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("media_url")
    @Expose
    private String mediaUrl;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("caption_original")
    @Expose
    private String captionOriginal;
    @SerializedName("desc_original")
    @Expose
    private String descOriginal;
    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("streetview")
    @Expose
    private String streetview;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("complete")
    @Expose
    private Integer complete;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("counts")
    @Expose
    private Counts counts;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPinsCount() {
        return pinsCount;
    }

    public void setPinsCount(String pinsCount) {
        this.pinsCount = pinsCount;
    }

    public Sponsored getSponsored() {
        return sponsored;
    }

    public void setSponsored(Sponsored sponsored) {
        this.sponsored = sponsored;
    }

    public Integer getTimesViewed() {
        return timesViewed;
    }

    public void setTimesViewed(Integer timesViewed) {
        this.timesViewed = timesViewed;
    }

    public Integer getOnExplore() {
        return onExplore;
    }

    public void setOnExplore(Integer onExplore) {
        this.onExplore = onExplore;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinClass() {
        return pinClass;
    }

    public void setPinClass(String pinClass) {
        this.pinClass = pinClass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaptionOriginal() {
        return captionOriginal;
    }

    public void setCaptionOriginal(String captionOriginal) {
        this.captionOriginal = captionOriginal;
    }

    public String getDescOriginal() {
        return descOriginal;
    }

    public void setDescOriginal(String descOriginal) {
        this.descOriginal = descOriginal;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStreetview() {
        return streetview;
    }

    public void setStreetview(String streetview) {
        this.streetview = streetview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}