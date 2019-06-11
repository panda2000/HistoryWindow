package ru.pandaprg.historywindow.Repository;

import android.content.Context;

public interface RepositoryInterface {
    void onCreate(Context ctx);
    void onResume();
    void onPause();
}
