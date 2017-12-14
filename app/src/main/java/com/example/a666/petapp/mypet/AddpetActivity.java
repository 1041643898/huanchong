package com.example.ta;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ta.pet.AddpetnameActivity;
import com.example.ta.pet.AddweightActivity;
import com.example.ta.round_imageview.OnBooleanListener;
import com.example.ta.round_imageview.RoundImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddpetActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    public final String USER_IMAGE_NAME = "image.png";
    public final String USER_CROP_IMAGE_NAME = "temporary.png";
    public Uri imageUriFromCamera;
    public Uri cropImageUri;
    public final int GET_IMAGE_BY_CAMERA_U = 5001;
    public final int CROP_IMAGE_U = 5003;
    private LinearLayout addpet_pettype;
    private LinearLayout addpet_immune;
    private ImageView addpet_finsh;
    private TextView addpet_keep;
    private LinearLayout headportrait;
    private LinearLayout addpet_nickname;
    private LinearLayout addpet_ifsterilization;
    private LinearLayout addpet_dateofbirth;
    private LinearLayout addpet_weight;
    private LinearLayout addpet_petintro;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private TextView tv_Date;
    private RoundImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet);
        initView();
        initDate();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_addpet;
    }

    protected void initView() {
        //数据类型
        addpet_pettype = (LinearLayout) findViewById(R.id.addpet_pettype);
        addpet_pettype.setOnClickListener(this);
        //免疫情况
        addpet_immune = (LinearLayout) findViewById(R.id.addpet_immune);
        addpet_immune.setOnClickListener(this);
        //返回
        addpet_finsh = (ImageView) findViewById(R.id.addpet_finsh);
        addpet_finsh.setOnClickListener(this);
        //保存页面
        addpet_keep = (TextView) findViewById(R.id.addpet_keep);

        addpet_keep.setOnClickListener(this);
        //头像
        headportrait = (LinearLayout) findViewById(R.id.headportrait);
        headportrait.setOnClickListener(this);
        //昵称
        addpet_nickname = (LinearLayout) findViewById(R.id.addpet_nickname);
        addpet_nickname.setOnClickListener(this);

        //是否绝育
        addpet_ifsterilization = (LinearLayout) findViewById(R.id.addpet_ifsterilization);
        addpet_ifsterilization.setOnClickListener(this);
        //出生日期
        addpet_dateofbirth = (LinearLayout) findViewById(R.id.addpet_dateofbirth);
        addpet_dateofbirth.setOnClickListener(this);
        //体重
        addpet_weight = (LinearLayout) findViewById(R.id.addpet_weight);
        addpet_weight.setOnClickListener(this);
        //宠物简介
        addpet_petintro = (LinearLayout) findViewById(R.id.addpet_petintro);
        addpet_petintro.setOnClickListener(this);
        tv_Date = (TextView) findViewById(R.id.tv_Date);
        tv_Date.setOnClickListener(this);
        ShowDate_of_Birth();
        imageView = (RoundImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void initDate() {
        //侵入式状态栏 核心代码.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //给状态栏设置颜色。我设置透明。
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //宠物类型
            case R.id.addpet_pettype:
                startActivity(new Intent(AddpetActivity.this, PetTypeActivity.class));

                break;

            //免疫情况
            case R.id.addpet_immune:

                startActivity(new Intent(AddpetActivity.this, ImmuneActivity.class));
                break;
            //头像
            case R.id.headportrait:
                startActivity(new Intent(AddpetActivity.this, HeadPortraitActivity.class));

                break;
            case R.id.addpet_ifsterilization:

                startActivity(new Intent(AddpetActivity.this, IfsterilizationActivity.class));
                break;
            case R.id.addpet_dateofbirth:
                customDatePicker1.show(tv_Date.getText().toString());

                break;
            case R.id.addpet_finsh:
                finish();
                break;
            case R.id.imageView:
                ShowPopupWindow_Icon(v);


                break;
            case R.id.addpet_nickname:
                startActivity(new Intent(AddpetActivity.this, AddpetnameActivity.class));
                break;
            case R.id.addpet_weight:
                startActivity(new Intent(AddpetActivity.this, AddweightActivity.class));
                break;


        }
    }
    //出生日期

    private void ShowDate_of_Birth() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tv_Date.setText(now.split(" ")[0]);
        tv_Date.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_Date.setText(time.split(" ")[0]);

            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_Date.setText(time);
            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }

    // PopupWindow   头像上传
    private void ShowPopupWindow_Icon(View view) {
        view = LayoutInflater.from(AddpetActivity.this).inflate(R.layout.pop_icon, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//        popupWindow.setAnimationStyle(R.anim.anim_pop);
        popupWindow.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }

                });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(view, 150, 300);
        Button but_pai = (Button) view.findViewById(R.id.but_pai);
        Button but_Phone = (Button) view.findViewById(R.id.but_Phone);

        Button but_out = (Button) view.findViewById(R.id.but_out);
        //拍照
        but_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("MainActivity", "进入点击");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // 或者 android.os.Build.VERSION_CODES.KITKAT这个常量的值是19

                    onPermissionRequests(Manifest.permission.CAMERA, new OnBooleanListener() {
                        @Override
                        public void onClick(boolean bln) {
                            if (bln) {
                                Log.d("MainActivity", "进入权限");
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                File photoFile = createImagePathFile(AddpetActivity.this);
                                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                                /*
                                * 这里就是高版本需要注意的，需用使用FileProvider来获取Uri，同时需要注意getUriForFile
                                * 方法第二个参数要与AndroidManifest.xml中provider的里面的属性authorities的值一致
                                * */
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                imageUriFromCamera = FileProvider.getUriForFile(AddpetActivity.this,
                                        "com.example.ta.fileprovider", photoFile);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);

                                startActivityForResult(intent, GET_IMAGE_BY_CAMERA_U);
                            } else {
                                Toast.makeText(AddpetActivity.this, "扫码拍照或无法正常使用", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    imageUriFromCamera = createImagePathUri(AddpetActivity.this);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            imageUriFromCamera);
                    startActivityForResult(intent, GET_IMAGE_BY_CAMERA_U);
                }

                popupWindow.dismiss();
            }
        });
        but_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupWindow.dismiss();
            }


        });

        but_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode);
        System.out.println("数据" + resultCode + "" + this.RESULT_OK);
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                case GET_IMAGE_BY_CAMERA_U:
                    /*
                    * 这里我做了一下调用系统切图，高版本也有需要注意的地方
                    * */
                    if (imageUriFromCamera != null) {
                        cropImage(imageUriFromCamera, 1, 1, CROP_IMAGE_U);
                        break;
                    }
                    break;
                case CROP_IMAGE_U:
                    final String s = getExternalCacheDir() + "/" + USER_CROP_IMAGE_NAME;

                    Bitmap imageBitmap = GetBitmap(s, 320, 320);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
                    imageView.setImageBitmap(imageBitmap);

                    break;
                default:
                    break;
            }
        }


    }

    private OnBooleanListener onPermissionListener;

    public void onPermissionRequests(String permission, OnBooleanListener onBooleanListener) {
        onPermissionListener = onBooleanListener;
        Log.d("MainActivity", "0");
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            Log.d("MainActivity", "1");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                //权限已有
                onPermissionListener.onClick(true);
            } else {
                //没有权限，申请一下
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        1);
            }
        } else {
            onPermissionListener.onClick(true);
            Log.d("MainActivity", "2" + ContextCompat.checkSelfPermission(this,
                    permission));
        }
    }

    public File createImagePathFile(Activity activity) {
        //文件目录可以根据自己的需要自行定义
        Uri imageFilePath;
        File file = new File(activity.getExternalCacheDir(), USER_IMAGE_NAME);
        imageFilePath = Uri.fromFile(file);
        return file;
    }

    public Uri createImagePathUri(Activity activity) {
        //文件目录可以根据自己的需要自行定义
        Uri imageFilePath;
        File file = new File(activity.getExternalCacheDir(), USER_IMAGE_NAME);
        imageFilePath = Uri.fromFile(file);
        return imageFilePath;
    }

    public void cropImage(Uri imageUri, int aspectX, int aspectY,
                          int return_flag) {
        File file = new File(this.getExternalCacheDir(), USER_CROP_IMAGE_NAME);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //高版本一定要加上这两句话，做一下临时的Uri
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            FileProvider.getUriForFile(AddpetActivity.this, "com.xuezj.fileproviderdemo.fileprovider", file);
        }
        cropImageUri = Uri.fromFile(file);

        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);

        startActivityForResult(intent, return_flag);
    }

    public Bitmap GetBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(
                BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }
}
