package com.example.a666.petapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.adapter.Home_Page_Adapter;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.entity.Home_FilterBean;
import com.example.a666.petapp.entity.PetTypeVO;
import com.example.a666.petapp.homepage.OrderActivity;
import com.example.a666.petapp.homepage.Personal_InformationActivity;
import com.example.a666.petapp.homepage.ShowUserInfoActivity;
import com.example.a666.petapp.loginactivity.LoginMainActivity;
import com.example.a666.petapp.mypet.MyPetActivity;
import com.example.a666.petapp.utils.AppUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zaaach.citypicker.CityPickerActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomePageActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int REQUEST_CODE_PICK_CITY = 233;


    private ImageView image_Personage_Centre;
    private EditText edit_sousuo;
    private ImageView image_Orientation;
    private LinearLayout linear_Nearby;
    private LinearLayout linear_Pat_Genre;
    private LinearLayout linear_ShaiXuan;
    private CheckBox popu_but_nearby;
    private CheckBox popu_but_pat_genre;
    private CheckBox popu_but_ShaiXuan;
    private ImageView image_name_icon;
    private TextView tv_name;
    private TextView tv_phone;
    private LinearLayout linear_name;
    private LinearLayout linear_Xiaoxi;
    private LinearLayout linear_Pet;
    private LinearLayout linear_DingDan;
    private LinearLayout linear_QianBao;
    private LinearLayout linear_XunZhi;
    private LinearLayout linear_SheZhi;
    private Button but_ShenQing;
    private DrawerLayout drawerLayout;
    private LinearLayout liner_nav;
    private PullToRefreshListView main_listview;
    private PopupWindow popupWindow;
    private CheckBox personal_xizao;
    private CheckBox personal_jiesong;
    private CheckBox personal_yuandan;
    private CheckBox personal_cunjie;
    private CheckBox personal_qingming;
    private CheckBox personal_laodong;
    private CheckBox personal_duanwu;
    private CheckBox personal_zhongqiu;
    private CheckBox personal_guoqing;
    private Button personal_chongzhi;
    private Button personal_queding;
    private TextView personal_tiaozhuan;
    private TextView personal_dizhi;
    private int page = 1;
    public static final String URL = "http://123.56.150.230:8885/dog_family/";
    private TextView tv_pet;
    private DrawerLayout activity_na;
    private Intent intent;
    private ListView lv;
    private PopupWindow popupWindow1;
    private MainChooseAdapter main_adapter;
    private List<String> list1, list2;
    private List<Boolean> checkList1, checkList2;
    private Map<String, String> findByOrder;
    private List<PetTypeVO> petTypes;
    private String string;


    private List<Home_FilterBean.DescBean> list;
    private LinearLayout liner_foster_teacher;

    //初始化窗口属性，让状态栏和导航栏透明
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initView() {


        //初始化窗口属性，让状态栏和导航栏透明
        initWindow();
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        liner_nav = (LinearLayout) findViewById(R.id.liner_nav);
        linear_name = (LinearLayout) findViewById(R.id.linear_name);
        linear_name.setOnClickListener(this);
        image_name_icon = (ImageView) findViewById(R.id.image_name_icon);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        linear_Xiaoxi = (LinearLayout) findViewById(R.id.linear_Xiaoxi);
        linear_Pet = (LinearLayout) findViewById(R.id.linear_Pet);
        linear_DingDan = (LinearLayout) findViewById(R.id.linear_DingDan);
        linear_QianBao = (LinearLayout) findViewById(R.id.linear_QianBao);
        linear_XunZhi = (LinearLayout) findViewById(R.id.linear_XunZhi);
        linear_SheZhi = (LinearLayout) findViewById(R.id.linear_SheZhi);
        but_ShenQing = (Button) findViewById(R.id.but_ShenQing);


        //点击侧换个人中心
//-------------上侧滑界面-----------------下主页面-----------------------------
        //搜索城市
        edit_sousuo = (EditText) findViewById(R.id.edit_sousuo);
        //点击显示侧滑界面
        image_Personage_Centre = (ImageView) findViewById(R.id.image_Personage_Centre);
        image_Personage_Centre.setOnClickListener(this);
        //定位
        image_Orientation = (ImageView) findViewById(R.id.image_Orientation);
        //附近优先
        linear_Nearby = (LinearLayout) findViewById(R.id.linear_Nearby);
        //宠物类型
        linear_Pat_Genre = (LinearLayout) findViewById(R.id.linear_Pat_Genre);
        //筛选
        linear_ShaiXuan = (LinearLayout) findViewById(R.id.linear_ShaiXuan);

        //刷新ListView
        main_listview = (PullToRefreshListView) findViewById(R.id.main_listview);


        image_Personage_Centre.setOnClickListener(this);
        edit_sousuo.setOnClickListener(this);
        image_Orientation.setOnClickListener(this);
        linear_Nearby.setOnClickListener(this);
        linear_Pat_Genre.setOnClickListener(this);
        linear_ShaiXuan.setOnClickListener(this);
        linear_name.setOnClickListener(this);
        linear_Pet.setOnClickListener(this);
        but_ShenQing.setOnClickListener(this);
        linear_DingDan.setOnClickListener(this);
        linear_Xiaoxi.setOnClickListener(this);
        linear_XunZhi.setOnClickListener(this);
        linear_QianBao.setOnClickListener(this);
        linear_SheZhi.setOnClickListener(this);


        //3个筛选代码
        popupWindow1();
        popupWindow2();
        popuwiondow3();


    }


    private void popupWindow1() {
        popupWindow1 = new PopupWindow(HomePageActivity.this);
        popu_but_nearby = (CheckBox) findViewById(R.id.popu_but_nearby);
        list1 = new ArrayList<String>();

        checkList1 = new ArrayList<Boolean>();
        list1.add("附近优先");
        list1.add("好评优先");
        list1.add("订单优先");
        list1.add("价格从高到低");
        list1.add("价格从低到高");
        checkList1.add(true);
        checkList1.add(false);
        checkList1.add(false);
        checkList1.add(false);
        checkList1.add(false);
        findByOrder = new HashMap<>();
        findByOrder.put("附近优先", "distance asc");
        findByOrder.put("好评优先", "score desc");
        findByOrder.put("订单优先", "orderCount desc");
        findByOrder.put("价格从高到低", "price desc");
        findByOrder.put("价格从低到高", "price asc");


    }

    private void popupWindow2() {
        popu_but_pat_genre = (CheckBox) findViewById(R.id.popu_but_Pat_Genre);
        list2 = new ArrayList<>();
        list2.add("大型犬");
        list2.add("中型犬");
        list2.add("小型犬");
        list2.add("猫");
        list2.add("小宠");
        checkList2 = new ArrayList<>();
        checkList2.add(true);
        checkList2.add(false);
        checkList2.add(false);
        checkList2.add(false);
        checkList2.add(false);


    }

    public void click(View view) {
        CheckBox rButton = (CheckBox) view;
        boolean check = rButton.isChecked();
        if (popupWindow1 != null && popupWindow1.isShowing()) {
            popupWindow1.dismiss();
        }
        switch (view.getId()) {
            case R.id.popu_but_nearby:

                if (check) {
                    popu_but_pat_genre.setChecked(false);
                    popu_but_ShaiXuan.setChecked(false);
                    initPopupWindow(view, 1, popu_but_nearby);
                }
                break;
            case R.id.popu_but_Pat_Genre:
                if (check) {
                    popu_but_nearby.setChecked(false);
                    popu_but_ShaiXuan.setChecked(false);
                    initPopupWindow(view, 2, popu_but_pat_genre);
                }
                break;

        }

    }


    private void initPopupWindow(View view, int num, CheckBox rCheckBox) {

        // 泡泡窗体里面嵌套的view对象
        View layout_view = LayoutInflater.from(HomePageActivity.this).inflate(
                R.layout.popup_window, null);
        popupWindow1.setContentView(layout_view);

        ListView listView = (ListView) layout_view.findViewById(R.id.lv);
        if (num == 1) {
            main_adapter = new MainChooseAdapter(list1, checkList1, rCheckBox,
                    1);

        }
        if (num == 2) {
            main_adapter = new MainChooseAdapter(list2, checkList2, rCheckBox,
                    2);

        }

        listView.setAdapter(main_adapter);

        // 设置宽和高
        popupWindow1.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow1.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // // //设置背景
        Drawable background = new ColorDrawable(Color.parseColor("#FFFFFF"));

        popupWindow1.setBackgroundDrawable(background);
        // //设置当前窗体可以获取焦点 作用：点击窗体其他位置能让窗体消失
        //pw.setFocusable(true);
        // //设置弹出的动画效果
        // //pw.setAnimationStyle(animationStyle);
        // //在此控件下方显示
        popupWindow1.showAsDropDown(view, 0, 20);
        //backgroundAlpha(0.5f);
        // //在当前控件的正中心显示
        // //pw.showAtLocation(layout_view, Gravity.CENTER, 100, 100);
        //pw.setOnDismissListener(new poponDismissListener());
    }


    private void popuwiondow3() {
        //------------------------------------------------------------------
        popu_but_ShaiXuan = (CheckBox) findViewById(R.id.popu_but_ShaiXuan);
        popu_but_ShaiXuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    popupWindow3();
                } else {
                    popupWindow.dismiss();

                }

            }
        });

    }

    //筛选城市
    private void popupWindow3() {
        //显示popuwindow
        View inflate = LayoutInflater.from(HomePageActivity.this).inflate(R.layout.homepage_shaixuan_popuwindow, null);
        //创建一个popuwindow对象
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //显示popuwindow
        popupWindow.showAsDropDown(linear_ShaiXuan, 0, 0);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        personal_chongzhi = inflate.findViewById(R.id.personal_chongzhi);
        personal_queding = inflate.findViewById(R.id.personal_queding);
        personal_tiaozhuan = inflate.findViewById(R.id.personal_tiaozhuan);
        personal_dizhi = inflate.findViewById(R.id.personal_dizhi);
        personal_tiaozhuan.setOnClickListener(this);
        personal_chongzhi.setOnClickListener(this);
        personal_queding.setOnClickListener(this);
        personal_xizao = inflate.findViewById(R.id.personal_xizao);
        personal_jiesong = inflate.findViewById(R.id.personal_jiesong);
        personal_yuandan = inflate.findViewById(R.id.personal_yuandan);
        personal_cunjie = inflate.findViewById(R.id.personal_cunjie);
        personal_qingming = inflate.findViewById(R.id.personal_qingming);
        personal_laodong = inflate.findViewById(R.id.personal_laodong);
        personal_duanwu = inflate.findViewById(R.id.personal_duanwu);
        personal_zhongqiu = inflate.findViewById(R.id.personal_zhongqiu);
        personal_guoqing = inflate.findViewById(R.id.personal_guoqing);
        //点击事件
        ClickEvent();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        return super.onTouchEvent(event);
    }

    //----------------------------------------------------------------------------------
//主页显示


    protected void initDate() {
        //主页的ListView 展示
        HomeListView();


    }

    private void HomeListView() {
        //主页的ListView 展示
        OkHttpClient ohc2 = new OkHttpClient();
        Map<String, Object> param2 = new HashMap<>();
        param2.put("beginIndex", (page - 1) * 10);
        param2.put("endIndex", page * 10);
        param2.put("orderBy", "");
// 生成提交服务器的JSON字符串
        String json2 = CJSON.toJSONMap(param2);
        FormBody.Builder body2 = new FormBody.Builder();
        body2.add(CJSON.DATA, json2);
        Request request2 = new Request.Builder()
                .url(URL + "users/getUsersInfoByVO.jhtml")
                .post(body2.build())
                .build();
        ohc2.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                Log.e("TAG", "------------------" + string + "----------");
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Home_FilterBean home_filterBean = gson.fromJson(string, Home_FilterBean.class);
                        list = home_filterBean.getDesc();
                        BindMainListviewDate(list);
                    }
                });
            }
        });
    }

    //刷新ListView界面
    private void BindMainListviewDate(final List<Home_FilterBean.DescBean> ls) {
        list.addAll(ls);
        Home_Page_Adapter home_page_adapter = new Home_Page_Adapter(list, HomePageActivity.this);

        main_listview.setAdapter(home_page_adapter);

        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                if (AppUtils.isUserState) {
                    AppUtils.userInfo.setState(3);
                }
//                 ToastUtil.show(ls.get(arg2).getUsersId());
//                 AppUtils.isUserState=false;
//                Intent intent = new Intent(HomePageActivity.this,
//                        ShowUserInfoActivity.class);
//                intent.putExtra("userId",
//                        list.get(Integer.parseInt(arg3 + "")).getUsersId());
//                startActivity(intent);
                Intent intent = new Intent(HomePageActivity.this,
                        ShowUserInfoActivity.class);
//                intent.putExtra("userId",
//                        list.get(Integer.parseInt(arg3 + "")).getUserId());

                intent.putExtra("name", ls.get(arg2).getFamily());
                //地址
                intent.putExtra("dizhi", ls.get(arg2).getAddress());
                intent.putExtra("star", ls.get(arg2).getScore());
                intent.putExtra("icon", list.get(arg2).getUserImage());


                startActivity(intent);

            }
        });
        main_listview.getRefreshableView().setDivider(
                getResources().getDrawable(R.drawable.corners));
        main_listview.getRefreshableView().setDividerHeight(1);
        main_listview.setMode(PullToRefreshBase.Mode.BOTH);
        main_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        main_listview.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        main_listview.onRefreshComplete();
                        page++;
                        //   initDate();
                    }
                }, 2000);
            }
        });
    }
    //----------------------------------------------------------------------------------


    @Override
    protected void initLisenter() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //点击显示侧滑
            case R.id.image_Personage_Centre:
                if (drawerLayout.isDrawerOpen(liner_nav)) {
                    drawerLayout.closeDrawer(liner_nav);
                } else {
                    drawerLayout.openDrawer(liner_nav);
                }

                break;
            case R.id.edit_sousuo:
                //搜索城市
                break;

            case R.id.image_Orientation:
                //定位
                // startActivity(new Intent(HomePageActivity.this, LocationActivity.class));
                break;

            //筛选城市定位 选择其他城市
            case R.id.personal_tiaozhuan:
                startActivityForResult(new Intent(HomePageActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);

                break;


            //侧滑的点击事件
            case R.id.linear_name:

                Intent intent = getIntent();
                boolean ret = intent.getBooleanExtra("ret", false);
                String userName = intent.getStringExtra("userName");
                long userPhone = intent.getLongExtra("userPhone", 1);

                if (ret == false) {
                    startActivity(new Intent(HomePageActivity.this, LoginMainActivity.class));
                } else {
                    startActivity(new Intent(HomePageActivity.this, Personal_InformationActivity.class));
                    tv_name.setText(userName);
                    tv_phone.setText(userPhone + "");
                }


                break;

            //消息

            case R.id.linear_Xiaoxi:
                startActivity(new Intent(HomePageActivity.this, NewsActivity.class));
                break;
            //宠物
            case R.id.linear_Pet:
                startActivity(new Intent(HomePageActivity.this, MyPetActivity.class));

                break;
            //订单
            case R.id.linear_DingDan:
                Intent intent1 = new Intent(HomePageActivity.this, OrderActivity.class);
                startActivity(intent1);
                break;
            //钱包
            case R.id.linear_QianBao:
                Intent intent4 = new Intent(HomePageActivity.this, WalletActivity.class);
                startActivity(intent4);
                break;
            //须知
            case R.id.linear_XunZhi:
                Intent intent3 = new Intent(HomePageActivity.this, InstructionsActivity.class);
                startActivity(intent3);
                break;
            //
            case R.id.linear_SheZhi:
                Intent intent5 = new Intent(HomePageActivity.this, SettingActivity.class);
                startActivity(intent5);
                break;
            //申请成为寄养家庭
            case R.id.but_ShenQing:


                Intent intent2 = new Intent(HomePageActivity.this, FosterfamiliesActivity.class);
                startActivity(intent2);
                break;

            //重置CheckBox 选中状态
            case R.id.personal_chongzhi:
                personal_xizao.setChecked(false);
                personal_jiesong.setChecked(false);
                personal_yuandan.setChecked(false);
                personal_cunjie.setChecked(false);
                personal_qingming.setChecked(false);
                personal_laodong.setChecked(false);
                personal_duanwu.setChecked(false);
                personal_zhongqiu.setChecked(false);
                personal_guoqing.setChecked(false);
                break;

            //确定
            case R.id.personal_queding:
                popu_but_ShaiXuan.setChecked(false);
                popupWindow.dismiss();
                break;


        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {

            case R.id.personal_xizao:

                break;
            case R.id.personal_jiesong:

                break;
            case R.id.personal_yuandan:

                break;
            case R.id.personal_cunjie:

                break;
            case R.id.personal_qingming:

                break;
            case R.id.personal_laodong:

                break;
            case R.id.personal_duanwu:

                break;
            case R.id.personal_zhongqiu:

                break;
            case R.id.personal_guoqing:

                break;
        }
    }

    @Override
    //筛选选择其他城市
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                personal_dizhi.setText(city);

            }
        }
    }

    //点击事件
    private void ClickEvent() {
        linear_Nearby.setOnClickListener(this);
        linear_Pat_Genre.setOnClickListener(this);
        linear_ShaiXuan.setOnClickListener(this);
        image_Orientation.setOnClickListener(this);
        but_ShenQing.setOnClickListener(this);
        linear_Pet.setOnClickListener(this);
        linear_DingDan.setOnClickListener(this);
        linear_QianBao.setOnClickListener(this);
        linear_Xiaoxi.setOnClickListener(this);
        linear_XunZhi.setOnClickListener(this);
        linear_SheZhi.setOnClickListener(this);
        edit_sousuo.setOnClickListener(this);
        personal_xizao.setOnCheckedChangeListener(this);
        personal_jiesong.setOnCheckedChangeListener(this);
        personal_yuandan.setOnCheckedChangeListener(this);
        personal_cunjie.setOnCheckedChangeListener(this);
        personal_qingming.setOnCheckedChangeListener(this);
        personal_laodong.setOnCheckedChangeListener(this);
        personal_duanwu.setOnCheckedChangeListener(this);
        personal_zhongqiu.setOnCheckedChangeListener(this);
        personal_guoqing.setOnCheckedChangeListener(this);
    }

    //-----------------------------------------------------------------------------------------------
    //点击两次退出程序
    private static boolean isExit = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    private int mPage = 1;

    class MainChooseAdapter extends BaseAdapter {

        private List<String> list;
        private List<Boolean> checkList;
        private CheckBox cBox;
        private int num;

        public MainChooseAdapter(List<String> list, List<Boolean> checkList,
                                 CheckBox rb, int num) {
            this.list = list;
            this.checkList = checkList;
            cBox = rb;
            this.num = num;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public String getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int arg0, View arg1, ViewGroup arg2) {
            View view = LayoutInflater.from(HomePageActivity.this).inflate(
                    R.layout.main_listview_items, null);
            if (checkList.get(arg0)) {
                TextView tv = (TextView) view.findViewById(R.id.main_item_tv);
                tv.setText(getItem(arg0));
                tv.setTextColor(Color.parseColor("#FFA820"));
            } else {
                TextView tv = (TextView) view.findViewById(R.id.main_item_tv);
                tv.setText(getItem(arg0));
                tv.setTextColor(Color.parseColor("#BFBFBF"));
                ImageView iv = (ImageView) view.findViewById(R.id.main_item_iv);
                iv.setVisibility(View.GONE);
            }
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    for (int i = 0; i < checkList.size(); i++) {
                        if (i == arg0) {
                            checkList.set(arg0, true);
                        } else {
                            checkList.set(i, false);
                        }
                    }
                    if (num == 1) {
                        checkList1 = checkList;
                    } else if (num == 2) {
                        checkList2 = checkList;
                    }
                    popupWindow1.dismiss();
                    cBox.setText(getItem(arg0));
                    cBox.setChecked(false);
                    main_adapter.notifyDataSetChanged();

                    HashMap<String, Object> param = new HashMap<>();
                    mPage = 1;
                    //    mBigList.clear();
                    if (findByOrder.get(getItem(arg0)) != null) {
                        param.put("orderBy", findByOrder.get(getItem(arg0)));
                        // param.put("beginIndex", "1");
                        param.put("coordX", AppUtils.locationY);
                        param.put("coordY", AppUtils.locationX);
                        // param.put("endIndex", "10");
                    } else {
                        param.put("orderBy",
                                findByOrder.get(popu_but_nearby.getText().toString()));
                        // param.put("beginIndex", "1");
                        // param.put("endIndex", "10");
                        param.put("coordX", AppUtils.locationY);
                        param.put("coordY", AppUtils.locationX);
//                        param.put("petTypeCode", petTypes.get(arg0)
//                                .getTypeCode());
                    }

                    // 生成json字符串
                    // String josn = CJSON.toJSONMap(param);
                    selectDataByOrder(param);
                    // Param(findByOrder.get(getItem(arg0)));

                }


            });
            return view;
        }
    }

    private void selectDataByOrder(HashMap<String, Object> param) {
        //主页的ListView 展示
        OkHttpClient ohc2 = new OkHttpClient();

        param.put("beginIndex", (mPage - 1) * 10);
        param.put("endIndex", mPage * 10);
// 生成提交服务器的JSON字符串
        String json2 = CJSON.toJSONMap(param);
        FormBody.Builder body2 = new FormBody.Builder();
        body2.add(CJSON.DATA, json2);
        Request request2 = new Request.Builder()
                .url(URL + "users/getUsersInfoByVO.jhtml")
                .post(body2.build())
                .build();
        ohc2.newCall(request2).enqueue(new Callback() {

            private String string;

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                Log.e("TAG", "--------12212121----------" + string + "----------");
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

                        Gson gson = new Gson();

                        Home_FilterBean home_filterBean = gson.fromJson(string, Home_FilterBean.class);
                        List<Home_FilterBean.DescBean> list = home_filterBean.getDesc();

                    }
                });
            }
        });

    }

}
