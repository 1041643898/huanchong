package com.example.a666.petapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * 功能引导页展示的图片集合
     */
    private static int[] mImageIds = new int[]{R.mipmap.pager01,
            R.mipmap.pager02, R.mipmap.pager03};
    /**
     * 功能引导页
     */
    private ViewPager mVpGuide;
    /**
     * 功能引导页展示的 ImageView 集合
     */
    private List<ImageView> mImageList;
    private Button mBtnStart;
    /**
     * 小灰点的父控件
     */
    private LinearLayout mDotGroup;
    /**
     *
     */
    //小蓝点
    private View mRedDot;
    /**
     * 相邻小灰点之间的距离
     */
    private int mDotDistance;
    //第一个白色圆点
    private View dot_view1;
    //第二个白色圆点
    private View dot_view2;
    //第三个白色圆点
    private View dot_view3;
    //上下文返回一个对象 以便使用
    private Context context=MainActivity.this;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //保存引导页
        mShare = context.getSharedPreferences("user", MODE_PRIVATE);
        mEditor = mShare.edit();
        //第二次就不让他运行
        boolean isTrue = mShare.getBoolean("isTrue", false);
        //第二次就直接运行跳转，不运行引导页
        if (isTrue){
            Intent intent = new Intent(MainActivity.this, ShouYeActivity.class);
            startActivity(intent);
            finish();
        }

        initView();


    }


    private void initView() {
        mVpGuide = (ViewPager) findViewById(R.id.vp_guide);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        dot_view1 = findViewById(R.id.dot_view1);
        dot_view2 = findViewById(R.id.dot_view2);
        dot_view3 = findViewById(R.id.dot_view3);
        mDotGroup = (LinearLayout) findViewById(R.id.ll_dot_group);
        mRedDot = findViewById(R.id.view_red_dot);
        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < mImageIds.length; i++) {
                    //如果运行到最后一页
                    if (position == mImageIds.length - 1) {
                        //小蓝点跟小白圆点都隐藏
                        mRedDot.setVisibility(View.GONE);
                        dot_view1.setVisibility(View.GONE);
                        dot_view2.setVisibility(View.GONE);
                        dot_view3.setVisibility(View.GONE);
                    } else {
                        //小蓝点跟小白圆点都显示
                        mRedDot.setVisibility(View.VISIBLE);
                        dot_view1.setVisibility(View.VISIBLE);
                        dot_view2.setVisibility(View.VISIBLE);
                        dot_view3.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mImageList = new ArrayList<>();
        // 将要展示的 3 张图片存入 ImageView 集合中
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            // 将图片设置给对应的 ImageView
            image.setBackgroundResource(mImageIds[i]);
            mImageList.add(image);
        }
        mDotGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // OnGlobalLayoutListener可能会被多次触发，
                        // 因此在得到了高度之后，要将 OnGlobalLayoutListener 注销掉
                        mDotGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        // 计算小灰点之间的距离
                        mDotDistance = mDotGroup.getChildAt(1).getLeft()
                                - mDotGroup.getChildAt(0).getLeft();
                        //Log.d(TAG, "小红距离：" + mDotDistance);
                    }
                });
        mVpGuide.setAdapter(new GuideAdapter());
        mVpGuide.setOnPageChangeListener(new GuidePageChangeListener());
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 按钮一旦被点击，更新 SharedPreferences
//                PrefUtils.setBoolean(MainActivity.this, PrefUtils.GUIDE_SHOWED, true);
                // 跳转到主页面
                // Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(MainActivity.this, Main2Activity.class));
                //保存引导页的数据
                mEditor.putBoolean("isTrue",true);

                mEditor.commit();
                //跳转到首页
                startActivity(new Intent(MainActivity.this, ShouYeActivity.class));

                finish();
            }
        });
    }




    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageList.get(position));
            return mImageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageList.get(position));
        }
    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        // 页面滑动时回调此方法
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 页面滑动过程中，小红点移动的距离
            int distance = (int) (mDotDistance * (positionOffset + position));
            // Log.d(TAG, "小红点移动的距离：" + distance);
            // 获取小蓝点的布局参数
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedDot
                    .getLayoutParams();
            // 修改小红点的左边缘和父控件(RelativeLayout)左边缘的距离
            params.leftMargin = distance;
            // 修改小红点的布局参数
            mRedDot.setLayoutParams(params);
        }

        // 某个页面被选中时回调此方法
        @Override
        public void onPageSelected(int position) {
            // 如果是最后一个页面，按钮可见，否则不可见
            if (position == mImageIds.length - 1) {
                mBtnStart.setVisibility(View.VISIBLE);
            } else {
                mBtnStart.setVisibility(View.INVISIBLE);
            }
        }
    }


}
