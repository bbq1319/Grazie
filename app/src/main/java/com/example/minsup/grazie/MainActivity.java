package com.example.minsup.grazie;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_coffee:
                    CoffeeFragment coffeeFragment = new CoffeeFragment();
                    FragmentManager manager1 = getFragmentManager();
                    manager1.beginTransaction().replace(R.id.content, coffeeFragment).commit();
                    return true;
                case R.id.navigation_beverage:
                    BeverageFragment beverageFragment = new BeverageFragment();
                    FragmentManager manager2 = getFragmentManager();
                    manager2.beginTransaction().replace(R.id.content, beverageFragment).commit();
                    return true;
                case R.id.navigation_dessert:
                    DessertFragment dessertFragment = new DessertFragment();
                    FragmentManager manager3 = getFragmentManager();
                    manager3.beginTransaction().replace(R.id.content, dessertFragment).commit();
                    return true;
                case R.id.navigation_information:
                    InformationFragment informationFragment = new InformationFragment();
                    FragmentManager manager4 = getFragmentManager();
                    manager4.beginTransaction().replace(R.id.content, informationFragment).commit();
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoffeeFragment coffeeFragment = new CoffeeFragment();
        FragmentManager manager1 = getFragmentManager();
        manager1.beginTransaction().replace(R.id.content, coffeeFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
