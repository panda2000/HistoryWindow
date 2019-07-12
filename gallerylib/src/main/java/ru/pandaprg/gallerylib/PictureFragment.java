package ru.pandaprg.gallerylib;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PictureFragment extends Fragment {

    private PictureViewModel mViewModel;

    private ImageView pictureView;

    public static PictureFragment newInstance() {
        return new PictureFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_fragment, container, false);
        pictureView = (ImageView) view.findViewById(R.id.pictureView);
        setPictureAplpha (128);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PictureViewModel.class);
        // TODO: Use the ViewModel
    }

    public void showPicture (String imageURL){
        Log.i ("Picasso","Show " + imageURL);
        Picasso.get().load(imageURL).into(pictureView);
        pictureView.setVisibility(View.VISIBLE);
    }

    public void hidePicture (){
        Log.i ("Picasso","Picture hide ");
        pictureView.setVisibility(View.GONE);
    }

    public void setPictureAplpha (int alpha){
        pictureView.setAlpha(alpha);
    }

}
