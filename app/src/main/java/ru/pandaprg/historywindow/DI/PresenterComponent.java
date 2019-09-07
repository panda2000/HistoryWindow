package ru.pandaprg.historywindow.DI;

import android.content.Context;

import dagger.Component;

@Component
public interface PresenterComponent {
    void inject (Context ctx);

}
