package com.example.a666.petapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a666.petapp.HomePageActivity;
import com.example.a666.petapp.R;
import com.example.a666.petapp.entity.Screen_Pet_Bean;

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
public class Screen_Pet_Adapter extends BaseAdapter {


    private List<Screen_Pet_Bean.DescBean> list;
    private Context context;

    public Screen_Pet_Adapter(List<Screen_Pet_Bean.DescBean> list, HomePageActivity homePageActivity) {

        this.list = list;
        this.context = homePageActivity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderScreenPet viewHolderScreenPet;
        if (null == view) {
            viewHolderScreenPet = new ViewHolderScreenPet();
            view = LayoutInflater.from(context).inflate(R.layout.home_page_popuwindow_shaixuan, null);
            viewHolderScreenPet.textView5 = view.findViewById(R.id.textView5);
            view.setTag(viewHolderScreenPet);

        }else{
            viewHolderScreenPet = (ViewHolderScreenPet) view.getTag();
        }
        viewHolderScreenPet.textView5.setText(list.get(i).getTypeName());

        return view;

    }

    class ViewHolderScreenPet {
        TextView textView5;
    }
}
