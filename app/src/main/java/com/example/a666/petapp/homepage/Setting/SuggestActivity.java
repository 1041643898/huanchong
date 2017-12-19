package com.example.a666.petapp.homepage.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a666.petapp.R;

public class SuggestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_go;
    private EditText add_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        initView();
        tv_go.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_go = (TextView) findViewById(R.id.tv_go);
        add_content = (EditText) findViewById(R.id.add_content);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_go:
              add_content.setText("");
                Toast.makeText(this, "谢谢您的建议", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
