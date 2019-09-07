package ru.pandaprg.feature_start_screen_impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.pandaprg.domain.DomainContract;
import ru.pandaprg.domain.InContract;
import ru.pandaprg.domain.InitializationContractIn;
import ru.pandaprg.domain.InitializationContractOut;
import ru.pandaprg.domain.OutContract;
import ru.pandaprg.domain.UseCase;
import ru.pandaprg.feature_common_impl.footer.Footer;
import ru.pandaprg.feature_common_impl.footer.FooterOutContract;
import ru.pandaprg.feature_common_impl.header.Header;
import ru.pandaprg.feature_common_impl.header.HeaderOutContract;
import ru.pandaprg.feature_screen1_impl.Postman;
import ru.pandaprg.feature_screen1_impl.Screen1Fragment;
import ru.pandaprg.feature_screen1_impl.SetTextToFragment;

public class ScreenStart extends AppCompatActivity implements OutContract, InitializationContractOut, Postman, FooterOutContract, HeaderOutContract {

    private static final String TAG = "ScreenStart";

    DomainContract useCase;

    Button button1;
    int increment1;
    Button button2;
    int increment2;
    Button button3;
    int increment3;

    TextView textView;

    Footer footer;
    Header header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        View rootView = findViewById(R.id.start_screen);
        if (rootView == null) {
            Log.d(TAG, "onCreate: Footer rootView is NULL" );
        }

        try {   // Создаем Header
            header = new Header(this,rootView);
        }catch (Exception e){
            Log.d(TAG, "onCreate Header: "+ e.getMessage() );
        }

        try {   // Создаем Footer
            footer = new Footer(this,rootView);
        }catch (Exception e){
            Log.d(TAG, "onCreate Footer: "+ e.getMessage() );
        }


        textView = (TextView) findViewById(R.id.textView);


        //Log.i(TAG, "onCreate: getInstence");
        useCase = UseCase.getInstence(this);
        useCase.attach(this);

        //Log.i(TAG, "onCreate: getIncrement");
        ((InitializationContractIn)useCase).getIncrement(1);
        ((InitializationContractIn)useCase).getIncrement(2);
        ((InitializationContractIn)useCase).getIncrement(3);

        ((InContract)useCase).getNumber();
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.frgmCont, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onGetIncrement(int n, int i) {
        Log.i(TAG, "onGetIncrement: ");
        switch (n){
            case 1:
                header.setButton1Name("+" +i);
                increment1 = i;
                break;
            case 2:
                header.setButton2Name("+" +i);
                increment2 = i;
                break;
            case 3:
                header.setButton3Name("+" +i);
                increment3 = i;
                break;
        }
    }

    @Override
    public void out(int b) {
        String str = "Counter = " + b;
        textView.setText(str);
        Screen1Fragment screen1Fragment = (Screen1Fragment) getSupportFragmentManager().findFragmentById(R.id.frgmCont);
        if (screen1Fragment != null) {
            ((SetTextToFragment) screen1Fragment).setText(str);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void fragmentMail(int increment) {
        Log.i(TAG, "onCreate: in (+"+increment+")");
        ((InContract)useCase).in(increment);
    }

    @Override
    public void onButtonAClick() {
        ((InContract) useCase).in(100);
        footer.setButtonAName("100");
    }

    @Override
    public void onButtonBClick() {
        ((InContract) useCase).in(-100);
        footer.setButtonBName("-100");
    }

    @Override
    public void onButton1Click() {
        ((InContract) useCase).in(increment1);
        //addFragment(new Screen1Fragment(), false, "one");
    }

    @Override
    public void onButton2Click() {
        ((InContract) useCase).in(increment2);
        //addFragment(new Screen2Fragment(), false, "one");
    }

    @Override
    public void onButton3Click() {
        ((InContract) useCase).in(increment3);

    }
}
