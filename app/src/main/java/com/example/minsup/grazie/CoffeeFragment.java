package com.example.minsup.grazie;


import android.app.FragmentManager;
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

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "2000원");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                MenuListViewItem item = (MenuListViewItem) parent.getItemAtPosition(position);

                Drawable menuImage = item.getMenuImage();
                String menuName = item.getMenuName();
                String menuPrice = item.getMenuPrice();

                OrderFragment orderFragment = new OrderFragment();
                Bundle bundle = new Bundle(3);
                bundle.putString("menuImage", String.valueOf(menuImage));
                bundle.putString("menuName", menuName);
                bundle.putString("menuPrice", menuPrice);
                orderFragment.setArguments(bundle);
                FragmentManager manager1 = getFragmentManager();
                manager1.beginTransaction().replace(R.id.content, orderFragment).commit();

            }
        });

        return v;
    }

}
