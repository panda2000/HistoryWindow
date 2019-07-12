package ru.pandaprg.navigator.compass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.pandaprg.navigator.R;
import ru.pandaprg.navigator.compass.ui.arrow.ArrowFragment;

public class Arrow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrow_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ArrowFragment.newInstance())
                    .commitNow();
        }
    }

}
