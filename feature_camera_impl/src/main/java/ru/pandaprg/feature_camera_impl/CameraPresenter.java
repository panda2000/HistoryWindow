package ru.pandaprg.feature_camera_impl;

import ru.pandaprg.domain.UseCases.FindPicturesOut;

public class CameraPresenter implements FindPicturesOut {
    @Override
    public void onPicturesFound() {

    }

    @Override
    public void onPicturesNotFound() {

    }

    public void FindPicturesInThisPlace () {
        // Todo FindPicturesInThisPlace in FindPicturesIn interface
    }

//--------------------------------------------------------------------
/*
    public void onChangeAlphaBar (int alpha){
        Log.i (TAG,"onChangeAlphaBar = " + alpha);
        ((MainActivity)view).setPictureAplpha(alpha);
    }
*/


//--------------------------------------------------------------------
/*
    public  void onPictureFind (String imageURL){
        showImage(imageURL);
    }

    public void showImage (String imageURL){
        ((MainActivity)view).showPicture(imageURL);
    }
*/
//--------------------------------------------------------------------

/*
    public void onHistoryPinPictureNotFind(){
      //  ((MainActivity)view).showMessage("Picture not found");
      //  ((MainActivity)view).hidePicture();
    }

*/
}
