package ru.pandaprg.baselibrary.Model;

import ru.pandaprg.baselibrary.Presenter.BasePresenter;

public class BaseModel implements BaseModelInterface {

    private static BaseModel model;
    protected BasePresenter presenter;


    protected BaseModel (BasePresenter presenter) {
        this.presenter = presenter;
    }

    public static BaseModel getInstanse (BasePresenter presenter){

        if (model == null){
            model = new BaseModel (presenter);
        }
        return model;
    }
}
