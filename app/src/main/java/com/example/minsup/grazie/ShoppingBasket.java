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

public class ShoppingBasket extends AppCompatActivity {

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
        ListView listView;
        ShoppingListViewAdapter adapter = new ShoppingListViewAdapter();
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        try{
            Intent intent = getIntent();
            byte[] bytes = intent.getByteArrayExtra("orderImage");
            String orderName = intent.getStringExtra("orderName");
            String orderPrice = intent.getStringExtra("orderPrice");

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Drawable drawable = new BitmapDrawable(bitmap);

            adapter.addItem(drawable, orderName, orderPrice,"1","12");

            setResult(1, intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

