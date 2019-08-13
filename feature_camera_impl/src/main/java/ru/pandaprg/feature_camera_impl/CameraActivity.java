package ru.pandaprg.feature_camera_impl;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.core_camera_api.MyCameraContract;
import ru.pandaprg.core_camera_impl.MyCamera;
import ru.pandaprg.feature_camera_api.CameraContract;

public class CameraActivity extends BaseActivity implements CameraContract, SeekBar.OnSeekBarChangeListener {

    private SeekBar alphaBar;

    // Поля Камеры
    private MyCameraContract cam;
    private TextureView videoView = null;
    private TextView tvMess = null;

    private CameraCoordinator coordinator = new CameraCoordinator(this);


    private Button mButtonChangeCamera = null;


    private Button mButtonCompass = null;
    //-------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutID = R.layout.activity_camera;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //--------------------------------------------------------------

        mButtonChangeCamera = (Button) findViewById(R.id.btn_open_camera1);
        mButtonCompass = (Button) findViewById(R.id.btnCompass);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coordinator.showActivity( v.getId());
            }
        };

        mButtonCompass.setOnClickListener(ocl);
        mButtonChangeCamera.setOnClickListener(ocl);

        videoView = (TextureView) findViewById(R.id.video_view);

        alphaBar = (SeekBar) findViewById(R.id.alphaBar);
        alphaBar.setProgress(128);
        alphaBar.setOnSeekBarChangeListener(this);

//        presenter = new CameraPresenter(this);
//        presenter.attach(this);


        //---------- Для камеры -------------------------------


        cam = new MyCamera(this);
        cam.setTextureView(videoView);

        mButtonChangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cam.choiceCamera(0);
                //Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                //startActivity(intent);
            }
        });
    }

    public void showMessage (String mess) {tvMess.setText(mess);}
/*
    public void showPicture (){
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PictureFragment.newInstance())
                    .commitNow();
        }
    }

    public void hidePicture (){
        getSupportFragmentManager().beginTransaction()
                .remove(PictureFragment.newInstance())
                .commitNow();
    }
*/
    //--------------------- SeekBar interface --------------------------------

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // presenter.onChangeAlphaBar(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
