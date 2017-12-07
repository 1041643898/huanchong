package com.example.a666.petapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a666.petapp.app.MyApplication;

/**
 * Created by 666 on 2017/12/5.
 */

public  abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        MyApplication.baseFragment=this;
        initview(view);
        initData();
        return view;
    }

    protected abstract int  getLayoutId();

    protected abstract void initData();


    protected abstract void initview(View view);
}
