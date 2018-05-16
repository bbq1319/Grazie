package com.example.minsup.grazie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Minsub on 2018-05-09.
 */

public class MenuListViewAdapter extends BaseAdapter {

    private ArrayList<MenuListViewItem> listViewItemList = new ArrayList<>();

    public MenuListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_listview_item, parent, false);
        }

        ImageView iconDrawable = convertView.findViewById(R.id.imageView);
        TextView menuName = convertView.findViewById(R.id.txt_name);
        TextView menuTaste = convertView.findViewById(R.id.txt_taste);
        TextView menuEngName = convertView.findViewById(R.id.txt_eng_name);
        TextView menuPrice = convertView.findViewById(R.id.txt_price);

        MenuListViewItem menuListViewItem = listViewItemList.get(position);

        iconDrawable.setImageDrawable(menuListViewItem.getMenuImage());
        menuName.setText(menuListViewItem.getMenuName());
        menuTaste.setText(menuListViewItem.getMenuTaste());
        menuEngName.setText(menuListViewItem.getMenuEngName());
        menuPrice.setText(menuListViewItem.getMenuPrice());

        return convertView;
    }

    public void addItem(Drawable icon, String name, String taste, String engName, String price) {
        MenuListViewItem item = new MenuListViewItem();

        item.setMenuImage(icon);
        item.setMenuName(name);
        item.setMenuTaste(taste);
        item.setMenuEngName(engName);
        item.setMenuPrice(price);

        listViewItemList.add(item);
    }

}
