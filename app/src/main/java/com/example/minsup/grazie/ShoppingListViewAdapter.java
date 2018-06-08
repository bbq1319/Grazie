package com.example.minsup.grazie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Minsub on 2018-05-30.
 */

public class ShoppingListViewAdapter extends BaseAdapter
{

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String orderName, orderPrice, orderQuantity, orderChoiceTaste;
    ShoppingListViewAdapter adapter;

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
        final Button delete = convertView.findViewById(R.id.delete);

        ShoppingListViewItem shoppingListViewItem = listViewItemList.get(position);

        menuName.setText(shoppingListViewItem.getMenuName());
        menuPrice.setText(shoppingListViewItem.getMenuPrice());
        choiceTaste.setText(shoppingListViewItem.getMenuTasteChoice());



        final DatabaseReference databaseReference = firebaseDatabase.getReference().child(user.getUid());
        final HashMap<Integer, String> map = new HashMap<>();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "asd", Toast.LENGTH_SHORT).show();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int i = 0;

                        for (DataSnapshot Data : dataSnapshot.getChildren()) {
                            map.put(i, Data.getKey());
                            i = i + 1;
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

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
