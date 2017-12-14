package com.example.a666.petapp.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a666.petapp.R;

import java.util.ArrayList;

/**
 * Created by 小仙女 on 2017/12/7.
 */

public class ShapeUtils extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private int size;
    private ArrayList<Bitmap> bitmaps;
    private Button but_Enter;
    private LinearLayout linearL;
    private Context context;

    private ArrayList<ImageView> shapImage = new ArrayList<>();
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public ShapeUtils(ArrayList<Bitmap> bitmaps, Button button, LinearLayout layout, Context context) {
        size=bitmaps.size();
        this.but_Enter = button;
        this.linearL = layout;
        this.context = context;
        this.bitmaps=bitmaps;
        initView();
    }

    private void initView() {
        //ViewPager的集合图片，进行轮播
        for (int i = 0; i < size; i++) {

            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(bitmaps.get(i));
            imageViews.add(imageView);

            //ViewPager的Shape圆点，进行显示
            ImageView shapeImage = new ImageView(context);
            shapeImage.setBackgroundResource(R.drawable.vp_unchecked);
            linearL.addView(shapeImage);
            shapImage.add(shapeImage);
        }

        shapImage.get(0).setBackgroundResource(R.drawable.vp_checked);
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (imageViews.get(position).equals(object)) {
            container.removeView((View) object);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }



    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < size; i++) {
            if (i == position) {
                shapImage.get(i).setBackgroundResource(R.drawable.vp_checked);
            } else {
                shapImage.get(i).setBackgroundResource(R.drawable.vp_unchecked);
            }
        }
        if (position == size - 1) {
            but_Enter.setVisibility(View.VISIBLE);
            linearL.setVisibility(View.GONE);
        } else {
            but_Enter.setVisibility(View.GONE);
            linearL.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

