package com.example.minsup.grazie;


import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoffeeFragment extends Fragment {


    public CoffeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coffee, container, false);

        ListView listView;
        MenuListViewAdapter adapter;

        adapter = new MenuListViewAdapter();

        listView = v.findViewById(R.id.menuListview);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "에스프레소", "", "Espresso", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "", "Americano", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "헤이즐넛", "", "Hazlenut", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카푸치노", "", "Cappuchino", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페라떼", "", "Cafe Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "라떼", "바닐라/브라우니/카라멜", "Vanilla/Brownie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카라멜마끼야또", "",  "Caramel Macchiato","2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페캬라멜로", "", "Cafe Caramelo", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페모카", "다크", "Cafe Mocha(Dark)", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페모카", "카라멜/브라우니", "Caramel/Brownie", "2000원");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                MenuListViewItem item = (MenuListViewItem) parent.getItemAtPosition(position);

                Drawable menuImage = item.getMenuImage();
                String menuName = item.getMenuName();
                String menuTaste = item.getMenuTaste();
                String menuEngName = item.getMenuEngName();
                String menuPrice = item.getMenuPrice();

                BitmapDrawable drawable = (BitmapDrawable) menuImage;
                Bitmap bitmap = drawable.getBitmap();

                OrderFragment orderFragment = new OrderFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("menuImage", bitmap);
                bundle.putString("menuName", menuName);
                bundle.putString("menuTaste", menuTaste);
                bundle.putString("menuEngName", menuEngName);
                bundle.putString("menuPrice", menuPrice);
                orderFragment.setArguments(bundle);
                FragmentManager manager1 = getFragmentManager();
                manager1.beginTransaction().replace(R.id.content, orderFragment).commit();

            }
        });

        return v;
    }

}
