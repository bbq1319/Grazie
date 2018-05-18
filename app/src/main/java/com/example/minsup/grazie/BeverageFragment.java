package com.example.minsup.grazie;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeverageFragment extends Fragment {


    public BeverageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_beverage, container, false);

        ListView listView;
        MenuListViewAdapter adapter;

        adapter = new MenuListViewAdapter();

        listView = v.findViewById(R.id.menuBervListview);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "망고요거트스무디", "", "Mango Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "키위요거트스무디", "", "Kiwi Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "딸기요거트스무디", "", "Strawberry Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "유자요거트스무디", "", "Citron Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "플레인요거트스무디", "", "Plain Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "블루베리요거트스무디", "", "Blueberry Yoghurt Smoothie", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "민트초코프라페", "", "Mint Chocolate Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "모카프라페", "", "Mocha Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카라멜프라페", "", "Caramel Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "그린티프라페", "", "Green-Tee Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "오곡프라페", "", "Grain Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "자색고구마프라페", "", "Pupple Sweet Potato Frappe", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "돼지프라페", "", "", "2000원");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "체리에이드", "", "Cherry Ade", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "레몬에이드", "", "Lemon Ade", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "자몽에이드", "", "Grape fruit Ade", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "청포도에이드", "", "Green-Grape Ade", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "유자에이드", "", "Citron Ade", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "블루레몬에이드", "", "Blue Lemon Ade", "2000원");

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

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, orderFragment);
                transaction.addToBackStack("BeverageTag");
                transaction.commit();

            }
        });

        return v;
    }

}
