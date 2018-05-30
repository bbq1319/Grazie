package com.example.minsup.grazie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
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
        ShoppingListViewAdapter adapter;

        adapter = new ShoppingListViewAdapter();

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
