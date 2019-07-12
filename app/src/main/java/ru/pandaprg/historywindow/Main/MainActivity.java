package ru.pandaprg.historywindow.Main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.gallerylib.PictureFragment;
import ru.pandaprg.historywindow.Hardware.Camera.MyCamera;
import ru.pandaprg.historywindow.Hardware.Camera.MyCameraContract;
import ru.pandaprg.historywindow.R;
import ru.pandaprg.navigator.Hardware.PermissionsContract;
import ru.pandaprg.navigator.compass.ui.arrow.ArrowFragment;

public class MainActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private MainPresenter presenter;

    PermissionsContract navigatorPermissions;

    private TextView textView;
    private TextView textAccel;
   // private ImageView picture_view;
    private SeekBar alphaBar;

    // Поля Камеры
    private MyCameraContract cam;
    private TextureView videoView = null;
    private TextView tvMess = null;

    private Button mButtonChangeCamera = null;
    private Button mButtonOpenCamera2 = null;
    //-------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutID = R.layout.activity_main;
        super.onCreate(savedInstanceState);


        // ----Добавляем во фрагмент Стрелку из модуля навигатор--------

        // TODO check this code and Architect !!!!!
        showArrow (savedInstanceState);

        //--------------------------------------------------------------

        mButtonChangeCamera = (Button) findViewById(R.id.btn_open_camera1);
        mButtonOpenCamera2 = (Button) findViewById(R.id.btn_open_camera2);

        videoView = (TextureView) findViewById(R.id.video_view);

        alphaBar = (SeekBar) findViewById(R.id.alphaBar);
        alphaBar.setProgress(128);
        alphaBar.setOnSeekBarChangeListener(this);



        // ------------ Presenter -----------------------------
        navigatorPermissions.CheckPermissions(this, MainActivity.this);

        presenter = new MainPresenter(this);
        presenter.attach(this);

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
                cam.choiceCamera(0);
            }
        });

        mButtonOpenCamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam.choiceCamera(1);
            }
        });


        //------------ Picasso ------------------------------------------------
        //Move to GalleryLib module

    }


    public void showGPSLocation (String mess){
        textView.setText(mess);
    }

    public void showAccelerometerData (String xy, String xz, String yz){
        textAccel.setText(xy+ "  "+xz + "  "+yz);
    }


    //
    public void showArrow (Bundle savedInstanceState){
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(ru.pandaprg.navigator.R.id.container, ArrowFragment.newInstance())
                    .commitNow();
        }
    }

    public void showPicture (Bundle savedInstanceState){
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(ru.pandaprg.navigator.R.id.container, PictureFragment.newInstance())
                    .commitNow();
        }
    }


    public void showMessage (String mess) {tvMess.setText(mess);}



    // TODO : refactor move to Navigator Lib
/*
    //region Marshmellows permissions
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    private void checkAndAddPermission() {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<>();
        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");

        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("Coarse");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                ActivityCompat.requestPermissions(MainActivity.this, permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);

                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    startBackgroundServiceForLocationUpdate();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startBackgroundServiceForLocationUpdate() {

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
//endregion




}
