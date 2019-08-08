package ru.pandaprg.feature_camera_impl;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.pandaprg.baselibrary.View.BaseActivity;
import ru.pandaprg.core_camera_api.MyCameraContract;
import ru.pandaprg.core_camera_impl.MyCamera;
import ru.pandaprg.feature_camera_api.CameraContract;

public class CameraActivity extends BaseActivity implements CameraContract {

    // Поля Камеры
    private MyCameraContract cam;
    private TextureView videoView = null;
    private TextView tvMess = null;

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

        videoView = (TextureView) findViewById(R.id.video_view);

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

}
