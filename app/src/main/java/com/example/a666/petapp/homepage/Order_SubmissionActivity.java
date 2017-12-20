package com.example.a666.petapp.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a666.petapp.R;

public class Order_SubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_progress;
    private TextView foster_order_id;
    private TextView foster_order_time;
    private TextView foster_order_money;
    private Button but_xianxi;
    private Button foster_order_btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__submission);
        initView();
    }

    private void initView() {
        iv_progress = (ImageView) findViewById(R.id.iv_progress);
        foster_order_id = (TextView) findViewById(R.id.foster_order_id);
        foster_order_time = (TextView) findViewById(R.id.foster_order_time);
        foster_order_money = (TextView) findViewById(R.id.foster_order_money);
        but_xianxi = (Button) findViewById(R.id.but_xianxi);
        foster_order_btn_ok = (Button) findViewById(R.id.foster_order_btn_ok);

        but_xianxi.setOnClickListener(this);
        foster_order_btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_xianxi:

                break;
            case R.id.foster_order_btn_ok:
                finish();
                break;
        }
    }
}
