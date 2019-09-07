package ru.pandaprg.feature_screen1_impl;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.pandaprg.domain.DomainContract;
import ru.pandaprg.domain.OutContract;

public class Screen1Fragment extends Fragment implements OutContract, View.OnClickListener, SetTextToFragment {

    private static final String TAG = "Screen1Fragment";
    TextView textView;
    DomainContract useCase;
    int increment =7;

    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.screen1_fragment, container, false);

        Button button = (Button) rootView.findViewById(R.id.button1);
        button.setOnClickListener(this);

        textView = (TextView) rootView.findViewById(R.id.textView1);

        return rootView;
    }

    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void out(int b) {
        textView.setText(b+"");
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onCreate: in (+"+increment+")");
        try {
            ((Postman)activity).fragmentMail(increment);
        } catch (ClassCastException e) {
            Log.d(TAG, "onClick: "+e.getMessage());
        }
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }
}