package com.example.a666.petapp.homepage.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private Button btn_submitPingjia;
    private ImageView iv_myIcon;
    private TextView tv_userssname;
    private RatingBar ratingbar_score;
    private EditText add_gospeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        btn_submitPingjia = (Button) findViewById(R.id.btn_submitPingjia);
        iv_myIcon = (ImageView) findViewById(R.id.iv_myIcon);
        tv_userssname = (TextView) findViewById(R.id.tv_userssname);
        ratingbar_score = (RatingBar) findViewById(R.id.ratingbar_score);
        add_gospeak = (EditText) findViewById(R.id.add_gospeak);

        btn_submitPingjia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitPingjia:
                add_gospeak.setText("");
                Toast.makeText(this, "谢谢您反馈的建议，我们会继续改善继续加油", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_back:
                finish();
        }
    }





}
