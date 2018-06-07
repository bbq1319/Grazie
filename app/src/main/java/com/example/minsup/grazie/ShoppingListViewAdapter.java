package com.example.minsup.grazie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        TextView menuName = convertView.findViewById(R.id.cho_name);
        TextView menuPrice = convertView.findViewById(R.id.cho_price);
        TextView choiceTaste = convertView.findViewById(R.id.cho_choiceTaste);

        ShoppingListViewItem shoppingListViewItem = listViewItemList.get(position);

        menuName.setText(shoppingListViewItem.getMenuName());
        menuPrice.setText(shoppingListViewItem.getMenuPrice());
        choiceTaste.setText(shoppingListViewItem.getMenuTasteChoice());

        return convertView;
    }

    public void addItem(String name, String price, String menuTasteChoice) {
        ShoppingListViewItem item = new ShoppingListViewItem();

        item.setMenuName(name);
        item.setMenuPrice(price);
        item.setMenuTasteChoice(menuTasteChoice);

        listViewItemList.add(item);
    }

}
