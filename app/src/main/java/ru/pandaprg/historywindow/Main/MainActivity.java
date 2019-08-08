package ru.pandaprg.historywindow.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.core_base_hardware_impl.Permissions;
import ru.pandaprg.core_camera_api.MyCameraContract;
import ru.pandaprg.core_camera_impl.MyCamera;
import ru.pandaprg.feature_camera_impl.CameraActivity;
import ru.pandaprg.feature_compass2_impl.CompassActivity;
import ru.pandaprg.gallerylib.PictureFragment;
import ru.pandaprg.historywindow.Main.arrow.ArrowFragment;
import ru.pandaprg.historywindow.R;

public class MainActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private MainPresenter presenter;

    private ArrowFragment arrow;

    private Bundle savedInstanceState;

  //  PermissionsContract navigatorPermissions;

    private TextView textView;
    private TextView textAccel;
   // private ImageView picture_view;
    private SeekBar alphaBar;

    // Поля Камеры
    private MyCameraContract cam;
    private TextureView videoView = null;
    private TextView tvMess = null;

    private Button mButtonChangeCamera = null;

    private Button mButtonCompass = null;
    //-------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutID = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;


        // ----Добавляем во фрагмент Стрелку из модуля навигатор--------

        // TODO check this code and Architect !!!!!
        //showArrow ();
        mButtonCompass = (Button) findViewById(R.id.btnCompass);

        //--------------------------------------------------------------

        mButtonChangeCamera = (Button) findViewById(R.id.btn_open_camera1);

        videoView = (TextureView) findViewById(R.id.video_view);

        alphaBar = (SeekBar) findViewById(R.id.alphaBar);
        alphaBar.setProgress(128);
        alphaBar.setOnSeekBarChangeListener(this);



        presenter = new MainPresenter(this);
        presenter.attach(this);


        //---------------- Permission ----------------------------
        Permissions permissions = new Permissions();
        permissions.CheckPermissions(this,this);

        //-------------- GPS -------------------------------------
        textView = (TextView) findViewById(R.id.textGPS);
        tvMess = (TextView) findViewById(R.id.tvMess);
        textAccel = (TextView) findViewById(R.id.tvAccel);

        //---------- Для камеры -------------------------------


        cam = new MyCamera(this);
        cam.setTextureView(videoView);

        mButtonChangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cam.choiceCamera(0);
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });


        //------------ Picasso ------------------------------------------------
        //Move to GalleryLib module

        mButtonCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume () {
        super.onResume();
        presenter.onResume();
    }

    public void showGPSLocation (String mess){
        textView.setText(mess);
    }

    public void showAccelerometerData (String xy, String xz, String yz){
        textAccel.setText(xy+ "  "+xz + "  "+yz);
    }


    //
    public void showArrow (){
        if (savedInstanceState == null) {
            arrow = ArrowFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, arrow)
                    .commitNow();
        }
    }

    public void rotationArrow (float olddeg, float deg) {
        arrow.rotationArrow(olddeg, deg);
    }

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


    public void showMessage (String mess) {tvMess.setText(mess);}



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
