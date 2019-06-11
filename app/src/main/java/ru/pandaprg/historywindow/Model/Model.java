package ru.pandaprg.historywindow.Model;

import android.content.Context;

public class Model {

    private static Model model;
    Context ctx;

    private Model (Context ctx) {
        this.ctx = ctx;
    }

    public static Model getInstanse (Context ctx){
        if (model == null){
            model = new Model (ctx);
        }
        return model;
    }
}
