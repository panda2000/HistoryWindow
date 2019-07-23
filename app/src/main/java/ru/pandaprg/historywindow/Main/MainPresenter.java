package ru.pandaprg.historywindow.Main;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import ru.pandaprg.baselibrary.Presenter.BasePresenter;
import ru.pandaprg.core_accelerometr_impl.AccelerometrData;
import ru.pandaprg.core_accelerometr_impl.MainAccelerometer;
import ru.pandaprg.core_gps_impl.GPSData;
import ru.pandaprg.core_gps_impl.MainGPS;
import ru.pandaprg.core_hardware_api.HardwareContract;
import ru.pandaprg.core_hardware_api.HardwareDataContract;
import ru.pandaprg.historywindow.Model.Model;
import ru.pandaprg.historywindow.Repository.WEB.HistoryPin.MainHistoryPin;

public class MainPresenter extends BasePresenter implements HardwareContract {

    private final String TAG = "MainPresenter";

    //private MainActivity view;
    private Model model;

    private Context ctx;

    private HardwareContract gps;
    private HardwareContract accel;


    private MainHistoryPin historyPin;

    public MainPresenter (Context ctx){
        this.ctx = ctx;

        model = Model.getInstanse(this);

        // --------------- Для GPS --------------------------------
        gps = new MainGPS(ctx, this);

        accel = new MainAccelerometer(ctx, this);

         //------------------- Для WEB HistoryPin-------------------
        // myLat = 46.3757;
        //            myLng = 48.0485;


    }

    //----------------------- GPS -----------------------------

    //@Override
    //TODO исправить баг с одной точкой входа всех данных
    public void onChange(GPSData data) {

        double lat = data.getLat();
        double lng = data.getLng();
        Date time = data.getTime();

        ((Model)model).setMyLocation(lat, lng, time);

        //historyPin = new MainHistoryPin(this, 46.3757, 48.0485);
        historyPin = new MainHistoryPin(this, ((Model)model).getParameters());

        if (isAttached()) {
            String mess = String.format("Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT", lat, lng, time);
            ((MainActivity)view).showGPSLocation(mess);
            Log.i (TAG,mess);
        } else {
            Log.e (TAG,"view is null");
        }
    }

//----------------------- Accelerometr -----------------------------
    //@Override
//TODO исправить баг с одной точкой входа всех данных
    public void onChange(AccelerometrData data) {
        long xy = data.getXy();
        long xz = data.getXz();
        long yz = data.getYz();

        //Log.i(TAG, data.getXy()+ "  "+data.getXz()+"  "+data.getYz());
    //TODO  ru.pandaprg.baselibrary.Model.BaseModel cannot be cast to ru.pandaprg.historywindow.Model.Model
        ((Model)model).setMyAccelerometr(xy, xz, yz);

        ((MainActivity)view).showAccelerometerData(String.valueOf(xy), String.valueOf(xz), String.valueOf(yz) );
    }
//-------------------------------------------------------------------
    //?????????????????????????????????????????

    @Override
    public void onChange(HardwareDataContract data) {
        Log.i(TAG, "HardwareData");
    }

    @Override
    public void Change(HardwareDataContract data) {
        Log.i(TAG, "HardwareData");
    }


//--------------------------------------------------------------------
    // TODO : move to Navigator Lib

    public void onRotation (float oldDeg, float currentDeg){
        Log.i(TAG, "Rotation from " + oldDeg + " to " + currentDeg);
        ((MainActivity)view).rotationArrow(oldDeg, currentDeg);

    }

    public void showArrow (){
        ((MainActivity)view).showArrow();
    }

//--------------------------------------------------------------------
/*
    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }

    public void onHistoryPinPictureFind(POJOUserGallery gallery){
        List <ImagesData> imagesData = convertHystoryPin2Model(gallery);
        ((Model)model).findPictures(imagesData);


    }

    public  void onPictureFind (String imageURL){
        showImage(imageURL);
    }

    public void showImage (String imageURL){
        ((MainActivity)view).showPicture(imageURL);
    }


    // TODO move to galleryLib ?
    private LinkedList<ImagesData> convertHystoryPin2Model (POJOUserGallery gallery) {

        LinkedList <ImagesData> imagesData = new LinkedList  <ImagesData> ();
        ImagesData iData = null;

        if (gallery != null ) {

            if (Integer.parseInt(String.valueOf(gallery.getCount())) > 0) {
                ((MainActivity)view).showMessage("Picture found");

                List<Result> results = gallery.getResults();

                double myLat = ((Model)model).getMyLocationLat();
                double myLng = ((Model)model).getMyLocationLng();

                for (Result res: results) {
                    Log.d(TAG, res.getImage());
                    Log.d(TAG, res.getDate());
                    Log.d(TAG, res.getLocation().getLat().toString());
                    Log.d(TAG, myLat+"");
                    Log.d(TAG, myLng+"");

                    iData = new ImagesData(res.getImage(),
                            res.getDate(),
                            res.getLocation(),
                            myLat,
                            myLng);

                    imagesData.add(iData);

                }


            }
            else
                onHistoryPinPictureNotFind();

        } else {
            Log.d(TAG, "NO Data");
            onHistoryPinPictureNotFind();
        }

        return imagesData;
    }
*/
    public void showMessage (String mess) {((MainActivity)view).showMessage(mess);}



    public void onHistoryPinPictureNotFind(){
        ((MainActivity)view).showMessage("Picture not found");
        ((MainActivity)view).hidePicture();
    }


}
