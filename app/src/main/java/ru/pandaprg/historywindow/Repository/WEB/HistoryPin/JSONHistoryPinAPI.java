package ru.pandaprg.historywindow.Repository.WEB.HistoryPin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.Gallery.POJOGallery;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.POJO.UserGallery.POJOUserGallery;

public interface JSONHistoryPinAPI {
    /*
    {lat_lo}=39.179422
    {lng_lo}=19.928202
    {lat_hi}=45.987757
    {lng_hi}=26.618876
    {sort}=popular
    {paging}=1
    */


    @GET ("/en/api/oreo/pin/get_gallery.json")  //?bounds={lat_lo},{lng_lo},{lat_hi},{lng_hi}&sort={sort}&paging={paging}
    public Call<POJOGallery> getGallery (@Query("bounds") String bounds, @Query("sort") String sort, @Query("paging") int paging);

    @GET ("/en/api/pin/get_gallery.json")  //?bounds={lat_lo},{lng_lo},{lat_hi},{lng_hi}&sort={sort}&paging={paging}
    public Call<POJOUserGallery> getUserGallery (@Query("user") String user);

    @GET ("/en/api/pin/get_gallery.json")  //?bounds={lat_lo},{lng_lo},{lat_hi},{lng_hi}&sort={sort}&paging={paging}
    public Call<POJOUserGallery> getUserGallery (@Query("user") String user, @Query("bounds") String bounds);

}
