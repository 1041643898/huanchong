package com.example.a666.petapp.mypet.mypetadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a666.petapp.R;
import com.example.a666.petapp.mypet.MyPetActivity;

import java.util.ArrayList;

/**
 * Created by 小仙女 on 2017/12/8.
 */

public class SubAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;

    public SubAdapter(MyPetActivity mainActivity, ArrayList<String> list) {

        this.context = mainActivity;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;
        if (null == convertView) {
            hold = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.mypet_item, null);
            hold.title = (TextView) convertView.findViewById(R.id.mypet_textview);

            convertView.setTag(hold);


        } else {
            hold = (ViewHolder) convertView.getTag();
        }
        hold.title.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView title;
    }
}
