package ru.pandaprg.feature_common_impl.footer;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.pandaprg.feature_common_impl.R;

public class Footer implements View.OnClickListener, FooterInContract {

    private Button button1, button2;
    private FooterOutContract root;

    public Footer (FooterOutContract root, View view) {

        this.root = root;

        button1 = (Button) view.findViewById(R.id.footerButtonA);
        button1.setOnClickListener(this);
        button1.setText("A");

        button2 = (Button) view.findViewById(R.id.footerButtonB);
        button2.setOnClickListener(this);
        button2.setText("B");

    }

    private static final String TAG = "Screen Footer";

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick:");
        if (v.getId() == R.id.footerButtonA) {
            Log.i(TAG, "onClick: Button A");
            root.onButtonAClick();
        } else if (v.getId() == R.id.footerButtonB) {
            Log.i(TAG, "onClick: Button B");
            root.onButtonBClick();
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void setButtonAName(String name) {
        button1.setText(name);
        button1.setBackgroundColor(R.color.colorAccent);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void setButtonBName(String name) {
        button2.setText(name);
        button2.setTextColor(R.color.colorPrimary);
    }
}
