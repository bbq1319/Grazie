package com.example.minsup.grazie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Minsub on 2018-05-30.
 */

public class ShoppingListViewAdapter extends BaseAdapter
{

    private ArrayList<ShoppingListViewItem> listViewItemList = new ArrayList<>();

    public ShoppingListViewAdapter(){

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
            convertView = inflater.inflate(R.layout.shopping_listview_item, parent, false);
        }

        ImageView iconDrawable = convertView.findViewById(R.id.imageView);
        TextView menuName = convertView.findViewById(R.id.cho_name);
        TextView menuTaste = convertView.findViewById(R.id.cho_taste);
        TextView menuEngName = convertView.findViewById(R.id.cho_eng_name);
        TextView menuPrice = convertView.findViewById(R.id.cho_price);
        TextView arrivalTime = convertView.findViewById(R.id.cho_arrivalTime);
        TextView choiceTaste = convertView.findViewById(R.id.cho_choiceTaste);

        ShoppingListViewItem shoppingListViewItem = listViewItemList.get(position);

        iconDrawable.setImageDrawable(shoppingListViewItem.getMenuImage());
        menuName.setText(shoppingListViewItem.getMenuName());
        menuTaste.setText(shoppingListViewItem.getMenuTaste());
        menuEngName.setText(shoppingListViewItem.getMenuEngName());
        menuPrice.setText(shoppingListViewItem.getMenuPrice());
        arrivalTime.setText(shoppingListViewItem.getArrivalTime());
        choiceTaste.setText(shoppingListViewItem.getMenuTasteChoice());

        return convertView;
    }

    public void addItem(Drawable icon, String name, String taste, String engName, String price, String arrivalTime, String menuTasteChoice) {
        ShoppingListViewItem item = new ShoppingListViewItem();

        item.setMenuImage(icon);
        item.setMenuName(name);
        item.setMenuTaste(taste);
        item.setMenuEngName(engName);
        item.setMenuPrice(price);

        listViewItemList.add(item);
    }

}
