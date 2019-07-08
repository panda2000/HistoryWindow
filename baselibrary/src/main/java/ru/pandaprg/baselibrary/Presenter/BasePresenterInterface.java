package ru.pandaprg.baselibrary.Presenter;

import ru.pandaprg.baselibrary.View.BaseActivity;

public interface BasePresenterInterface {

    boolean isAttached();

    void attach (BaseActivity view);

    void detach ();
}
