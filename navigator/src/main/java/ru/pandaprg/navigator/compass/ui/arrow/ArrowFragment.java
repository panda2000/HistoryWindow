package ru.pandaprg.navigator.compass.ui.arrow;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import ru.pandaprg.navigator.R;

public class ArrowFragment extends Fragment {

    private ArrowViewModel mViewModel;

    private ImageView imageArrow;


    public static ArrowFragment newInstance() {
        return new ArrowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrow_fragment, container, false);
        imageArrow = (ImageView) view.findViewById(R.id.arrowView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ArrowViewModel.class);
        // TODO: Use the ViewModel
    }

    public void showArrow (){
        imageArrow.setImageResource(R.drawable.arrow);
    }

    public void rotationPicture (float olddeg, float deg) {
        // picture_view.animate().rotation(deg);

        Animation an = new RotateAnimation(olddeg, deg,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //deg = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        imageArrow.startAnimation(an);
    }

}
