package com.example.a666.petapp.model;

/**
 * Created by 廖文博 on 2017/11/22.
 */

public class Model implements ModelInf {
    @Override
    public void getMoldelInf(String str, NoBent noBent) {
        OkHttpUtil.getIntens().getShu(str,noBent);
    }
}
