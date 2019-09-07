package ru.pandaprg.historywindow.Main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.core_base_hardware_impl.Permissions;
import ru.pandaprg.historywindow.Coordinator.MainCoordinator;
import ru.pandaprg.historywindow.R;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    private Bundle savedInstanceState;

    private MainCoordinator coordinator = new MainCoordinator(this);


    private Button mButtonChangeCamera;

    private Button mButtonCompass;
    //-------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutID = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;

        presenter = new MainPresenter(this);
        presenter.attach(this);


        //---------------- Permission ----------------------------
        Permissions permissions = new Permissions();
        permissions.CheckPermissions(this,this);

        //---------------- Buttons ----------------------------
        mButtonCompass = (Button) findViewById(R.id.btnCompass);
        mButtonChangeCamera = (Button) findViewById(R.id.btn_open_camera1);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coordinator.showActivity( v.getId());
            }
        };

        mButtonChangeCamera.setOnClickListener(ocl);
        mButtonCompass.setOnClickListener(ocl);



        //------------ Picasso ------------------------------------------------
        //Move to GalleryLib module


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







}
