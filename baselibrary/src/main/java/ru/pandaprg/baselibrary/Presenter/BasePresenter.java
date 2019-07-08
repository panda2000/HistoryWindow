package ru.pandaprg.baselibrary.Presenter;

import android.util.Log;

import ru.pandaprg.baselibrary.View.BaseActivity;

public abstract class BasePresenter implements BasePresenterInterface {
    protected BaseActivity view = null;

    public boolean isAttached(){
        if (view != null)
            return true;
        return  false;
    }

    public void attach(BaseActivity view){
        this.view = view;
        Log.d("Presenter", "view is attached");

    }

    public void detach (){
        view = null;
    }
}
