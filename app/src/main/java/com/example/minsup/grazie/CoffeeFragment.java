package com.example.minsup.grazie;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

        listView = v.findViewById(R.id.menuCoffeeListview);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "에스프레소", "", "Espresso", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아메리카노", "", "Americano", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "헤이즐넛", "", "Hazlenut", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카푸치노", "", "Cappuchino", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페라떼", "", "Cafe Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "라떼", "바닐라/브라우니/카라멜", "Vanilla/Brownie/Caramel", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카라멜마끼야또", "",  "Caramel Macchiato","2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페캬라멜로", "", "Cafe Caramelo", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "카페모카", "다크/카라멜/브라우니", "Dark/Caramel/Brownie", "2000원");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "다크초코라떼", "", "Dark-Chocolate Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "화이트초코라떼", "", "White-Chocolate Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "그린티라떼", "", "Green-tea Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "자색고구마라떼", "", "Sweet-potato Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "블랙티라떼", "", "Black-tea Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "밀크티라떼", "", "Milk-tea Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "블랙씨리얼라떼", "", "Black-tea Latte", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "스위트콘라떼", "", "Milk-tea Latte", "2000원");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "요거트버블티", "", "Yoghurt Bubble Tea", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "딸기버블티", "", "Strawberry Bubble Tea", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "쿠키바닐라버블티", "", "Cookies Vanilla Bubble Tea", "2000원");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.americano), "아몬드버블티", "", "Almond Bubble Tea", "2000원");

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
                transaction.addToBackStack("CoffeeTag");
                transaction.commit();

            }
        });

        return v;
    }

}
