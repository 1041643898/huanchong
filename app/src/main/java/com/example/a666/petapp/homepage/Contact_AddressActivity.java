package com.example.a666.petapp.homepage;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.FileUtil;
import com.example.a666.petapp.utils.TableUtils;
import com.zaaach.citypicker.CityPickerActivity;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.name;


//修改地址
public class Contact_AddressActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private ImageView image_Pull_out;
    private TextView tv_submit;
    private TextView tv_Chengshi;
    private ImageView linear_City;
    private EditText et_Address;
    private LinearLayout linear;
    private String address;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_contact__address;
    }

    @Override
    protected void initView() {
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_Chengshi = (TextView) findViewById(R.id.tv_Chengshi);
        linear_City = (ImageView) findViewById(R.id.linear_City);
        et_Address = (EditText) findViewById(R.id.et_Address);
        linear = (LinearLayout) findViewById(R.id.linear);

        image_Pull_out.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        linear_City.setOnClickListener(this);

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    private void submit() {
        // validate
        address = et_Address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入具体街道-小区-栋-号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_Pull_out:
                finish();
                break;
            case R.id.tv_submit:
                //提交
                submit();
                Map<String, Object> param = new HashMap<>();
                String userId = AppUtils.userInfo.getUserId();
                param.put(TableUtils.UserInfo.USERID, userId);
                param.put(TableUtils.UserInfo.ADDRESS,address);
                // 生成提交服务器的JSON字符串
                String json = CJSON.toJSONMap(param);

                FormBody.Builder builder = new FormBody.Builder();
                builder.add(CJSON.DATA, json);
                OkHttpClient ohc = new OkHttpClient();
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
                                Log.e("TAG",string);
                                AppUtils.userInfo.setUserName(address);
                                FileUtil.saveUser(AppUtils.userInfo);
                                finish();
                            }
                        });

                    }
                });
                break;
            case R.id.linear_City:
                startActivityForResult(new Intent(Contact_AddressActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
                break;

        }
    }
    //筛选选择其他城市
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tv_Chengshi.setText(city);

            }
        }
    }
}
