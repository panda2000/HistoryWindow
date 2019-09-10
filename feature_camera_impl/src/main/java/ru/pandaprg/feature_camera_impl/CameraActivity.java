package ru.pandaprg.feature_camera_impl;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.core_camera_api.MyCameraContract;
import ru.pandaprg.core_camera_impl.MyCamera;
import ru.pandaprg.feature_camera_api.CameraContract;
import ru.pandaprg.feature_common_impl.header.Header;
import ru.pandaprg.feature_common_impl.header.HeaderOutContract;

public class CameraActivity extends BaseActivity implements CameraContract, HeaderOutContract, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "CameraActivity";

    private SeekBar alphaBar;

    // Поля Камеры
    private MyCameraContract cam;
    private TextureView videoView = null;
    private TextView tvMess = null;



    private Button mButtonChangeCamera = null;

    Header header;

    private Button mButtonCompass = null;
    //-------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutID = R.layout.activity_camera;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //--------------------------------------------------------------
/*
        mButtonChangeCamera = (Button) findViewById(R.id.btn_open_camera1);
        mButtonCompass = (Button) findViewById(R.id.btnCompass);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        };

        mButtonCompass.setOnClickListener(ocl);
        mButtonChangeCamera.setOnClickListener(ocl);
*/
        View rootView = findViewById(R.id.camera_screen);
        if (rootView == null) {
            Log.d(TAG, "onCreate: Footer rootView is NULL" );
        }

        try {   // Создаем Header
            header = new Header(this,rootView);
        }catch (Exception e){
            Log.d(TAG, "onCreate Header: "+ e.getMessage() );
        }


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

    @Override
    public void onButton1Click() {

    }

    @Override
    public void onButton2Click() {

    }

    @Override
    public void onButton3Click() {

    }
}
