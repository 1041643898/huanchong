package com.example.a666.petapp.homepage;

import android.content.Intent;

import com.example.a666.petapp.R;
import com.example.a666.petapp.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class CalenDarActivity extends BaseActivity {

    private DatePicker dataPicker;
    private int year;
    private int month;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_calen_dar;
    }

    @Override
    protected void initView() {
        dataPicker = (DatePicker) findViewById(R.id.dataPicker);
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        final String str = formatter.format(curDate);

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dataPicker.setDate(year, month + 1);
        dataPicker.setMode(DPMode.SINGLE);
        dataPicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {


            @Override
            public void onDatePicked(String date) {

                String[] split = date.split("-");
                DatePicker datePicker = new DatePicker(CalenDarActivity.this);
                int year = Integer.valueOf(split[0]);
                int month = Integer.valueOf(split[1]);
                datePicker.setDate(year, month);
                datePicker.setMode(DPMode.SINGLE);

                Intent intent = new Intent(CalenDarActivity.this, ReservationsActivity.class);
                intent.putExtra("date", date);


                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }
}
