package com.example.minsup.grazie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingBasket extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ListView listView;
    ShoppingListViewAdapter adapter;

    int amountPrice = 0;
    String orderName, orderPrice, orderQuantity, orderChoiceTaste;
    TextView amount_price;
    Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);

        amount_price = findViewById(R.id.amount_price);
        orderBtn = findViewById(R.id.orderBtn);

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

        final DatabaseReference databaseReference = firebaseDatabase.getReference().child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot Data : dataSnapshot.getChildren()) {
                    orderName = Data.child("menuName").getValue().toString();
                    orderPrice = Data.child("menuPrice").getValue().toString();
                    orderQuantity = Data.child("menuQuantity").getValue().toString();
                    orderChoiceTaste = Data.child("menuChoiceTaste").getValue().toString();

                    String order_price = orderPrice.substring(0,4);
                    amountPrice = amountPrice + Integer.parseInt(order_price) * Integer.parseInt(orderQuantity);

                    amount_price.setText(String.valueOf(amountPrice) + "원");

                    adapter.addItem(orderName, orderPrice + " X " + orderQuantity, orderChoiceTaste);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // 하단 부분
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}

