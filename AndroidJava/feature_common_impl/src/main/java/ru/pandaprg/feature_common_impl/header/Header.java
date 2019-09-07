package ru.pandaprg.feature_common_impl.header;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.pandaprg.feature_common_impl.R;

public class Header implements View.OnClickListener, HeaderInContract {

    private static final String TAG = "Screen Header";

    private Button button1, button2, button3;
    private String buttonName1, buttonName2, buttonName3;
    private HeaderOutContract root;

    public Header (HeaderOutContract root, View view) {

        this.root = root;

        button1 = (Button) view.findViewById(R.id.headerButton1);
        button1.setOnClickListener(this);


        button2 = (Button) view.findViewById(R.id.headerButton2);
        button2.setOnClickListener(this);

        button3 = (Button) view.findViewById(R.id.headerButton3);
        button3.setOnClickListener(this);
    }

    @Override
    public void setButton1Name(String name) {
        buttonName1 = name;
        button1.setText(name);
    }

    @Override
    public void setButton2Name(String name) {
        buttonName2 = name;
        button2.setText(name);
    }

    @Override
    public void setButton3Name(String name) {
        buttonName3 = name;
        button3.setText(name);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick:");
        if (v.getId() == R.id.headerButton1) {
            Log.i(TAG, "onClick: Button "+buttonName1);
            root.onButton1Click();
        } else if (v.getId() == R.id.headerButton2) {
            Log.i(TAG, "onClick: Button "+buttonName2);
            root.onButton2Click();
        } else if (v.getId() == R.id.headerButton3) {
            Log.i(TAG, "onClick: Button "+buttonName3);
            root.onButton3Click();
        }
    }
}
