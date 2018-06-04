package com.example.minsup.grazie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingBasket extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    ListView listView;
    ShoppingListViewAdapter adapter;

    String orderName, orderPrice, orderQuantity, orderArrival, orderChoiceTaste;
    Drawable drawable;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);

        // 화면 설정
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));


        // 아이템 가져오기
        adapter = new ShoppingListViewAdapter();
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot Data : dataSnapshot.getChildren()) {
                    // child 내에 있는 데이터만큼 반복합니다.

                    orderName = Data.child("menuName").getValue().toString();
                    orderPrice = Data.child("menuPrice").getValue().toString();
                    orderQuantity = Data.child("menuQuantity").getValue().toString();
                    orderArrival = Data.child("menuArrival").getValue().toString();
                    orderChoiceTaste = Data.child("menuChoiceTaste").getValue().toString();
                    adapter.addItem(drawable, orderName, orderPrice, orderQuantity, orderArrival, orderChoiceTaste);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            Intent intent = getIntent();
            byte[] bytes = intent.getByteArrayExtra("orderImage");
            orderName = intent.getStringExtra("orderName");
            orderPrice = intent.getStringExtra("orderPrice");
            orderQuantity = intent.getStringExtra("orderQuantity");
            orderArrival = intent.getStringExtra("orderArrival");
            orderChoiceTaste = intent.getStringExtra("menuChoiceTaste");

            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            drawable = new BitmapDrawable(bitmap);

            // db 값 저장
//            databaseReference.child("order").child("orderName").push().setValue(orderName);
//            databaseReference.child("order").child("orderPrice").push().setValue(orderPrice);
//            databaseReference.child("order").child("orderQuantity").push().setValue(orderQuantity);
//            databaseReference.child("order").child("orderArrival").push().setValue(orderArrival);
//            databaseReference.child("order").child("orderChoiceTaste").push().setValue(orderChoiceTaste);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void setData() {


    }
}

