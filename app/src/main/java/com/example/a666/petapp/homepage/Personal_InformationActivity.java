package com.example.a666.petapp.homepage;

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

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.homepage.date.CustomDatePicker;
import com.example.a666.petapp.homepage.round_imageview.OnBooleanListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Personal_InformationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView image_icon;
    private LinearLayout linear_Name;
    private LinearLayout liner_Gender;
    private TextView tv_Gender;
    private LinearLayout linear_Date_of_Birth;
    private LinearLayout linear_Phone;
    private LinearLayout linear_WeiXin;
    private TextView tv_QQ_xinxi;
    private LinearLayout linear_QQ;
    private LinearLayout linear_Address;
    private ImageView image_Pull_out;
    private PopupWindow popupWindow;
    private TextView tv_date;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    public final String USER_IMAGE_NAME = "image.png";
    public final String USER_CROP_IMAGE_NAME = "temporary.png";
    public Uri imageUriFromCamera;
    public Uri cropImageUri;
    public final int GET_IMAGE_BY_CAMERA_U = 5001;
    public final int CROP_IMAGE_U = 5003;
    protected static final int CHOOSE_PICTURE = 0;

    @Override
    protected void onResume() {

        onPermissionRequests(Manifest.permission.WRITE_EXTERNAL_STORAGE, new OnBooleanListener() {
            @Override
            public void onClick(boolean bln) {
                if (bln) {

                } else {
                    Toast.makeText(Personal_InformationActivity.this, "文件读写或无法正常使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onResume();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_personal__information;
    }

    @Override
    protected void initView() {
        //退出界面
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        //上传头像
        image_icon = (ImageView) findViewById(R.id.image_icon);

        //设置名字
        linear_Name = (LinearLayout) findViewById(R.id.linear_Name);
        //设置性别
        liner_Gender = (LinearLayout) findViewById(R.id.liner_Gender);
        tv_Gender = (TextView) findViewById(R.id.tv_Gender);
        //设置出生日期
        linear_Date_of_Birth = (LinearLayout) findViewById(R.id.linear_Date_of_Birth);
        //显示出生日期
        tv_date = (TextView) findViewById(R.id.tv_Date);
        //设置手机号
        linear_Phone = (LinearLayout) findViewById(R.id.linear_Phone);
        //设置微信号
        linear_WeiXin = (LinearLayout) findViewById(R.id.linear_WeiXin);
        //QQ 信息未完善系列
        tv_QQ_xinxi = (TextView) findViewById(R.id.tv_QQ_xinxi);
        //QQ
        linear_QQ = (LinearLayout) findViewById(R.id.linear_QQ);
        //联系地址 跳转
        linear_Address = (LinearLayout) findViewById(R.id.linear_Address);
        image_Pull_out.setOnClickListener(this);
        image_icon.setOnClickListener(this);
        linear_Name.setOnClickListener(this);
        liner_Gender.setOnClickListener(this);
        linear_Date_of_Birth.setOnClickListener(this);
        linear_Phone.setOnClickListener(this);
        linear_WeiXin.setOnClickListener(this);
        linear_QQ.setOnClickListener(this);
        linear_Address.setOnClickListener(this);

        //设置出生日期
        ShowDate_of_Birth();
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
    public void onClick(View view) {
        switch (view.getId()) {
            //退出
            case R.id.image_Pull_out:
                finish();
                break;
            //头像上传
            case R.id.image_icon:
                ShowPopupWindow_Icon(view);
                break;
            //修改名字
            case R.id.linear_Name:

                startActivity(new Intent(this, Modif_NameActivity.class));
                break;
            //性别
            case R.id.liner_Gender:
                ShowPopupWindow_Gender(view);
                break;
            //出生日期
            case R.id.linear_Date_of_Birth:
                customDatePicker1.show(tv_date.getText().toString());

                break;
            //手机
            case R.id.linear_Phone:
                startActivity(new Intent(this, PhoneActivity.class));
                break;
            //微信
            case R.id.linear_WeiXin:
                startActivity(new Intent(this, WeiXinActivity.class));
                break;
            //  QQ
            case R.id.linear_QQ:
                startActivity(new Intent(this, QQActivity.class));
                break;
            //联系地址
            case R.id.linear_Address:
                startActivity(new Intent(this, Contact_AddressActivity.class));
                break;
        }
    }

    //出生日期

    private void ShowDate_of_Birth() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tv_date.setText(now.split(" ")[0]);
        tv_date.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_date.setText(time.split(" ")[0]);

            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_date.setText(time);
            }
        }, "1900-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }

    // PopupWindow  性别选择
    private void ShowPopupWindow_Gender(View view) {
        view = LayoutInflater.from(Personal_InformationActivity.this).inflate(R.layout.popupwindow_gender, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }

                });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(view, 150, 300);
        Button but_nan = view.findViewById(R.id.but_nan);
        Button but_nv = view.findViewById(R.id.but_nv);

        but_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Gender.setText("男");
                popupWindow.dismiss();
            }


        });
        but_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Gender.setText("女");
                popupWindow.dismiss();
            }
        });


    }


    // PopupWindow   头像上传
    private void ShowPopupWindow_Icon(View view) {
        view = LayoutInflater.from(Personal_InformationActivity.this).inflate(R.layout.popupwindow_icon, null);
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
        Button but_pai = view.findViewById(R.id.but_pai);
        Button but_Phone = view.findViewById(R.id.but_Phone);

        Button but_out = view.findViewById(R.id.but_out);
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
                                File photoFile = createImagePathFile(Personal_InformationActivity.this);
                                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                                /*
                                * 这里就是高版本需要注意的，需用使用FileProvider来获取Uri，同时需要注意getUriForFile
                                * 方法第二个参数要与AndroidManifest.xml中provider的里面的属性authorities的值一致
                                * */
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                imageUriFromCamera = FileProvider.getUriForFile(Personal_InformationActivity.this,
                                        "com.example.a666.petapp.fileprovider", photoFile);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);

                                startActivityForResult(intent, GET_IMAGE_BY_CAMERA_U);
                            } else {
                                Toast.makeText(Personal_InformationActivity.this, "扫码拍照或无法正常使用", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    imageUriFromCamera = createImagePathUri(Personal_InformationActivity.this);
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

    private OnBooleanListener onPermissionListener;

    public void onPermissionRequests(String permission, OnBooleanListener onBooleanListener) {
        onPermissionListener = onBooleanListener;
        Log.d("MainActivity", "0");
        if (ContextCompat.checkSelfPermission(this, permission)
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限通过
                if (onPermissionListener != null) {
                    onPermissionListener.onClick(true);
                }
            } else {
                //权限拒绝
                if (onPermissionListener != null) {
                    onPermissionListener.onClick(false);
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public Uri createImagePathUri(Activity activity) {
        //文件目录可以根据自己的需要自行定义
        Uri imageFilePath;
        File file = new File(activity.getExternalCacheDir(), USER_IMAGE_NAME);
        imageFilePath = Uri.fromFile(file);
        return imageFilePath;
    }

    public File createImagePathFile(Activity activity) {
        //文件目录可以根据自己的需要自行定义
        Uri imageFilePath;
        File file = new File(activity.getExternalCacheDir(), USER_IMAGE_NAME);
        imageFilePath = Uri.fromFile(file);
        return file;
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
                    image_icon.setImageBitmap(imageBitmap);

                    break;
                default:
                    break;
            }
        }

    }
    public void cropImage(Uri imageUri, int aspectX, int aspectY,
                          int return_flag) {
        File file = new File(this.getExternalCacheDir(), USER_CROP_IMAGE_NAME);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //高版本一定要加上这两句话，做一下临时的Uri
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            FileProvider.getUriForFile(Personal_InformationActivity.this, "com.xuezj.fileproviderdemo.fileprovider", file);
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
