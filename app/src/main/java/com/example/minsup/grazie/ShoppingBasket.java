package com.example.minsup.grazie;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    ListView listView;
    ShoppingListViewAdapter adapter;
    List<Object> array = new ArrayList<Object>();

    String orderName, orderPrice, orderQuantity, orderArrival, menuChoiceTaste;
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

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));


        // 아이템 가져오기
        adapter = new ShoppingListViewAdapter();
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        initDatabase();

        mReference = mDatabase.getReference("order"); // 변경값을 확인할 child 이름
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    // child 내에 있는 데이터만큼 반복합니다.

                    orderName = data.child("orderName").getValue().toString();
                    orderPrice = data.child("orderPrice").getValue().toString();
                    orderQuantity = data.child("orderQuantity").getValue().toString();
                    orderArrival = data.child("orderArrival").getValue().toString();
                    menuChoiceTaste = data.child("menuChoiceTaste").getValue().toString();

                    adapter.addItem(drawable, orderName, orderPrice, orderQuantity, orderArrival, menuChoiceTaste);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        try{
//            Intent intent = getIntent();
//            byte[] bytes = intent.getByteArrayExtra("orderImage");
//            String orderName = intent.getStringExtra("orderName");
//            String orderPrice = intent.getStringExtra("orderPrice");
//            String orderQuantity = intent.getStringExtra("orderQuantity");
//            String orderArrival = intent.getStringExtra("orderArrival");
//            String orderChoiceTaste = intent.getStringExtra("menuChoiceTaste");
//
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            Drawable drawable = new BitmapDrawable(bitmap);
//
//            adapter.addItem(drawable, orderName, orderPrice, orderQuantity, orderArrival,orderChoiceTaste);
//
//            setResult(1, intent);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

}

