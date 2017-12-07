package com.example.a666.petapp.presenter;


import com.example.a666.petapp.model.Model;
import com.example.a666.petapp.model.ModelInf;
import com.example.a666.petapp.model.NoBent;
import com.example.a666.petapp.view.Viewinf;

/**
 * Created by 廖文博 on 2017/11/22.
 */

public class Presenter implements NoBent {

    ModelInf modelInf;
    Viewinf viewInf;
    public Presenter(Viewinf viewInf) {
        this.viewInf=viewInf;
       modelInf=new Model();
    }

    public void getUrls(String url){
        modelInf.getMoldelInf(url,this);
    }
    @Override
    public void getSucceed(String ShuJu) {
        viewInf.getUpdate(ShuJu);
    }

    @Override
    public void getFail(String fail) {
        viewInf.getUpdate(fail);
    }
}
