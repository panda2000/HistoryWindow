package ru.pandaprg.historywindow.Base.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity implements BaseActivityInterface {

    public int LayoutID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(LayoutID);
    }

    @Override
    public void init() {

    }
}
