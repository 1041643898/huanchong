package com.example.a666.petapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a666.petapp.R;
import com.example.a666.petapp.entity.Home_FilterBean;

import java.util.List;

//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
//
public class Home_Page_Adapter extends BaseAdapter {
    private List<Home_FilterBean.DescBean> list;
    private Context context;

    public Home_Page_Adapter(List<Home_FilterBean.DescBean> list, Context context) {
        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_main_listview_items, null);
            holder.tv_name = (TextView) convertView
                    .findViewById(R.id.main_listview_user_name);
            holder.tv_address = (TextView) convertView
                    .findViewById(R.id.main_listview_user_address);
            holder.tv_price = (TextView) convertView
                    .findViewById(R.id.main_listview_user_price);
            holder.rBar = (RatingBar) convertView.findViewById(R.id.main_ratingBar1);
            holder.pcv = (ImageView) convertView
                    .findViewById(R.id.main_listview_user_iv);
            holder.tv_location = (TextView) convertView
                    .findViewById(R.id.main_listview_location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.tv_name.setText(list.get(position).getFamily());
        holder.tv_address.setText(list.get(position).getAddress());
        holder.tv_price.setText("￥" + list.get(position).getPrice());
        holder.rBar.setRating((Float.parseFloat(list.get(position).getScore()) + 0));

        Glide.with(context).load(list.get(position).getUserImage()).into(holder.pcv);

        if (list.get(position).getCoordX() != null
                && list.get(position).getCoordY() != null
                && !list.get(position).getCoordX().isEmpty()
                && !list.get(position).getCoordY().isEmpty()) {







        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_address;
        TextView tv_price;
        TextView tv_location;
        RatingBar rBar;
        ImageView pcv;
    }
}