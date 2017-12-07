package com.example.a666.petapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 666 on 2017/12/6.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private List<ImageView> AssembleList;

    public ViewpagerAdapter(List<ImageView> assembleList) {
        AssembleList = assembleList;
    }

    @Override
    public int getCount() {
        return AssembleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(AssembleList.get(position));
        return AssembleList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(AssembleList.get(position));
    }
}
