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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
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
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.entity.ImageItem;
import com.example.a666.petapp.entity.UploadUtil;
import com.example.a666.petapp.entity.UserInfo;
import com.example.a666.petapp.homepage.date.CustomDatePicker;
import com.example.a666.petapp.homepage.round_imageview.OnBooleanListener;
import com.example.a666.petapp.homepage.round_imageview.RoundImageView;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.FileUtil;
import com.example.a666.petapp.utils.TableUtils;
import com.example.a666.petapp.utils.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Personal_InformationActivity extends BaseActivity implements View.OnClickListener {
    private RoundImageView image_icon;
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

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    // private Uri cropImageUri;




    protected static final int CHOOSE_PICTURE = 0;
    private LinearLayout linear_mayuntuijian;
    private TextView tv_name_xinxi;
    private TextView textView;
    private TextView tv_Date;
    private TextView tv_shoujihao_xinxi;
    private TextView tv_weixin_xinxi;
    private TextView tv_lianxidizhi_xinxi;

    @Override
    protected void onResume() {
        super.onResume();
        onPermissionRequests(Manifest.permission.WRITE_EXTERNAL_STORAGE, new OnBooleanListener() {
            @Override
            public void onClick(boolean bln) {
                if (bln) {

                } else {
                    Toast.makeText(Personal_InformationActivity.this, "文件读写或无法正常使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 名字
        tv_name_xinxi.setText(AppUtils.userInfo.getUserName());

//
//
//
//        //性别
        int sex = AppUtils.userInfo.getUserSex();
        if (sex == 1) {
            tv_Gender.setText("男");
            AppUtils.userInfo.setUserSex(1);
        } else {
            tv_Gender.setText("女");
            AppUtils.userInfo.setUserSex(2);
        }
//
//        Date  birthday = AppUtils.userInfo.getBirthday();
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        tv_date.setText(sf.format(birthday));
//        //出生年月





//
//
//        绑定qq
        long qq = AppUtils.userInfo.getQq();
        tv_QQ_xinxi.setText("" + qq);

//        //绑定手机号
        long userPhone = AppUtils.userInfo.getUserPhone();
        tv_shoujihao_xinxi.setText("" + userPhone);


        //绑定微信
        String wechat = AppUtils.userInfo.getWechat();
        tv_weixin_xinxi.setText(wechat);








        //联系地址
        String address = AppUtils.userInfo.getAddress();
        tv_lianxidizhi_xinxi.setText(address);


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
        image_icon = (RoundImageView) findViewById(R.id.image_icon);
        tv_name_xinxi = (TextView) findViewById(R.id.tv_name_xinxi);
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
        tv_shoujihao_xinxi= (TextView) findViewById(R.id.tv_shoujihao_xinxi);
        //设置微信号
        linear_WeiXin = (LinearLayout) findViewById(R.id.linear_WeiXin);
        tv_weixin_xinxi= (TextView) findViewById(R.id.tv_weixin_xinxi);
        //QQ 信息未完善系列
        tv_QQ_xinxi = (TextView) findViewById(R.id.tv_QQ_xinxi);
        //QQ
        linear_QQ = (LinearLayout) findViewById(R.id.linear_QQ);
        //联系地址 跳转
        linear_Address = (LinearLayout) findViewById(R.id.linear_Address);
        tv_lianxidizhi_xinxi= (TextView) findViewById(R.id.tv_lianxidizhi_xinxi);
        image_Pull_out.setOnClickListener(this);
        image_icon.setOnClickListener(this);
        linear_Name.setOnClickListener(this);
        liner_Gender.setOnClickListener(this);
        linear_Date_of_Birth.setOnClickListener(this);
        linear_Phone.setOnClickListener(this);
        linear_WeiXin.setOnClickListener(this);
        linear_QQ.setOnClickListener(this);
        linear_Address.setOnClickListener(this);
        AppUtils.setAppContext(this);

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

                AppUtils.userInfo.setUserSex(1);
                FileUtil.saveUser(AppUtils.userInfo);
                UpdateSex();
                popupWindow.dismiss();
            }


        });
        but_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Gender.setText("女");
                AppUtils.userInfo.setUserSex(2);
                FileUtil.saveUser(AppUtils.userInfo);
                UpdateSex();
                popupWindow.dismiss();
            }
        });


    }
    //修改性别
    private void UpdateSex() {
        Map<String, Object> param = new HashMap<>();
        param.put(TableUtils.UserInfo.USERID, AppUtils.userInfo.getUserId());
        param.put(TableUtils.UserInfo.USERSEX, AppUtils.userInfo.getUserSex());
        // 生成提交服务器的JSON字符串
        String json = CJSON.toJSONMap(param);
        // FileUtil.getToken();
        OkHttpClient ohc = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add(CJSON.DATA, json);
        Request request = new Request.Builder()

                .url("http://123.56.150.230:8885/dog_family/user/updateUserInfo.jhtml")
                .post(builder.build())
                .build();

        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (CJSON.getRET(string)) {

                            ToastUtil.show("修改成功!");

                        } else {
                            ToastUtil.show("修改失败");
                        }

                    }
                });

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
        Button but_pai =(Button) view.findViewById(R.id.but_pai);
        Button but_Phone =(Button) view.findViewById(R.id.but_Phone);

        Button but_out = view.findViewById(R.id.but_out);
        //拍照
        but_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // autoObtainCameraPermission();


                popupWindow.dismiss();
            }
        });
        but_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //autoObtainStoragePermission();

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
    //上传头像

    public void photoDatas(List<ImageItem> item) {
        Bitmap bitmap = BitmapFactory.decodeFile(item
                .get(0).path);
        image_icon.setImageBitmap(bitmap);
        Map<String, Object> param = new HashMap<>();
        param.put(TableUtils.UserInfo.USERID, AppUtils
                .getUser().getUserId());
        String path = AppUtils.getUser().getUserImage();
        if (TextUtils.isEmpty(path)) {
            path = "";
        }
        param.put(TableUtils.UserInfo.USERIMAGE,
                path);
        new MyUploadFile().execute(
                CJSON.toJSONMap(param),
                item.get(0).path);
    }


    class MyUploadFile extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return new UploadUtil().uploadFile(new File(params[1]),
                    AppUtils.REQUESTURL + "user/updateImage.jhtml", params[0]);
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.i("TAG", "-----" + json);
            if (CJSON.getRET(json)) {
                ToastUtil.show("修改成功");
                UserInfo user = AppUtils.getUser();
                user.setUserImage(CJSON.getDESC(json));
                AppUtils.setUser(user);
            } else {
                ToastUtil.show("修改失败");
            }
        }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }


//
//    /**
//     * 自动获取相机权限
//     */
//    private void autoObtainCameraPermission() {
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//                ToastUtils.showShort(this, "您已经拒绝过一次");
//            }
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
//        } else {//有权限直接调用系统相机拍照
//            if (hasSdcard()) {
//                imageUri = Uri.fromFile(fileUri);
//                //通过FileProvider创建一个content类型的Uri
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    imageUri = FileProvider.getUriForFile(Personal_InformationActivity.this, "com.zz.fileprovider", fileUri);
//                }
//                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
//            } else {
//                ToastUtils.showShort(this, "设备没有SD卡！");
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            //调用系统相机申请拍照权限回调
//            case CAMERA_PERMISSIONS_REQUEST_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (hasSdcard()) {
//                        imageUri = Uri.fromFile(fileUri);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                            imageUri = FileProvider.getUriForFile(Personal_InformationActivity.this, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
//                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
//                    } else {
//                        ToastUtils.showShort(this, "设备没有SD卡！");
//                    }
//                } else {
//
//                    ToastUtils.showShort(this, "请允许打开相机！！");
//                }
//                break;
//
//
//            }
//            //调用系统相册申请Sdcard权限回调
//            case STORAGE_PERMISSIONS_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
//                } else {
//
//                    ToastUtils.showShort(this, "请允许打操作SDCard！！");
//                }
//                break;
//            default:
//        }
//    }
//
//    private static final int OUTPUT_X = 480;
//    private static final int OUTPUT_Y = 480;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                //拍照完成回调
//                case CODE_CAMERA_REQUEST:
//                    cropImageUri = Uri.fromFile(fileCropUri);
//                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
//                    break;
//                //访问相册完成回调
//                case CODE_GALLERY_REQUEST:
//                     if (hasSdcard()) {
//                        cropImageUri = Uri.fromFile(fileCropUri);
//                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            newUri = FileProvider.getUriForFile(this, "com.zz.fileprovider", new File(newUri.getPath()));
//                        }
//                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
//                    } else {
//                        ToastUtils.showShort(this, "设备没有SD卡！");
//                    }
//                    break;
//                case CODE_RESULT_REQUEST:
//                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
//                    if (bitmap != null) {
//                        showImages(bitmap);
//                    }
//                    break;
//                default:
//            }
//        }
//    }


//    /**
//     * 自动获取sdk权限
//     */
//
//    private void autoObtainStoragePermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
//        } else {
//            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
//        }
//
//    }
//
//    private void showImages(Bitmap bitmap) {
//
//        image_icon .setImageBitmap(bitmap);
//    }
//
//    /**
//     * 检查设备是否存在SDCard的工具方法
//     */
//    public static boolean hasSdcard() {
//        String state = Environment.getExternalStorageState();
//        return state.equals(Environment.MEDIA_MOUNTED);
//    }
//
//




}
