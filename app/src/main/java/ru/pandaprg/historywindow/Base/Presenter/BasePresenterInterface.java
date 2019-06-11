package ru.pandaprg.historywindow.Base.Presenter;

import ru.pandaprg.historywindow.Base.View.BaseActivity;

public interface BasePresenterInterface {

    boolean isAttached();

    void attach (BaseActivity view);

    void detach ();
}
