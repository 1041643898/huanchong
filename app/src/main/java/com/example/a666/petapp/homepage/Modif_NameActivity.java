package com.example.a666.petapp.homepage;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;
import com.example.a666.petapp.entity.CJSON;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.FileUtil;
import com.example.a666.petapp.utils.TableUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//修改名字
public class Modif_NameActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image_Pull_out;
    private TextView tv_submit;
    private EditText et_name;
    private String name;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_modif__name;

    }

    @Override
    protected void initView() {
        AppUtils.setAppContext(this);
        image_Pull_out = (ImageView) findViewById(R.id.image_Pull_out);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        et_name = (EditText) findViewById(R.id.et_name);

        image_Pull_out.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    private void submit() {
        // validate
        name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入16字以内的名称(中文，数字，字母)", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    //修改姓名
    private void rename() {

        Map<String, Object> param = new HashMap<>();
        String userId = AppUtils.userInfo.getUserId();
        param.put(TableUtils.UserInfo.USERID, userId);
        param.put(TableUtils.UserInfo.USERNAME,name);
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
                        AppUtils.userInfo.setUserName(name);
                        FileUtil.saveUser(AppUtils.userInfo);
                        finish();
                    }
                });

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_Pull_out:

                finish();
                break;

            case R.id.tv_submit:
                submit();
                rename();
                break;

        }
    }
}
