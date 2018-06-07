package com.example.minsup.grazie;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    int amount = 1;
    String menuChoiceTaste, menuName, menuTaste, menuEngName, menuPrice;
    Bitmap menuImage;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        // 주문정보
        ImageView orderImage = v.findViewById(R.id.orderImage);
        TextView orderName = v.findViewById(R.id.orderName);
        TextView orderTaste = v.findViewById(R.id.orderTaste);
        TextView orderEngName = v.findViewById(R.id.orderEngName);
        TextView orderPrice = v.findViewById(R.id.orderPrice);

        menuImage = getArguments().getParcelable("menuImage");
        menuName = getArguments().getString("menuName");
        menuTaste = getArguments().getString("menuTaste");
        menuEngName = getArguments().getString("menuEngName");
        menuPrice = getArguments().getString("menuPrice");

        orderImage.setImageBitmap(menuImage);
        orderName.setText(menuName);
        orderTaste.setText(menuTaste);
        orderEngName.setText(menuEngName);
        orderPrice.setText(menuPrice);

        // 수량체크
        final TextView orderQuantity = v.findViewById(R.id.orderQuantity);
        Button order_plus = v.findViewById(R.id.orderPlus);
        Button order_minus = v.findViewById(R.id.orderMinus);

        orderQuantity.setText("" + amount);
        amount = Integer.parseInt(orderQuantity.getText().toString());

        order_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = Integer.parseInt(orderQuantity.getText().toString()) + 1;
                orderQuantity.setText("" + amount);
            }
        });

        order_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount <= 1){
                    Toast.makeText(getActivity().getApplicationContext(), "1개 이상 주문하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    amount = Integer.parseInt(orderQuantity.getText().toString()) - 1;
                    orderQuantity.setText("" + amount);
                }
            }
        });

        // 스피너
        TextView taste_choice = v.findViewById(R.id.taste_choice);
        Spinner spinner_taste = v.findViewById(R.id.taste_spinner);

        if(menuName.equals("라떼")){

            taste_choice.setVisibility(View.VISIBLE);
            spinner_taste.setVisibility(View.VISIBLE);

            ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.latte_taste, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(R.layout.custom_simple_dropdown_item_1line);
            spinner_taste.setAdapter(adapter);
            spinner_taste.setSelection(0);
            spinner_taste.setPrompt("맛을 선택해주세요.");

            spinner_taste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    menuChoiceTaste = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else if(menuName.equals("카페모카")){

            taste_choice.setVisibility(View.VISIBLE);
            spinner_taste.setVisibility(View.VISIBLE);

            spinner_taste.setPrompt("맛을 선택해주세요.");

            ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cafeMocha_taste, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(R.layout.custom_simple_dropdown_item_1line);
            spinner_taste.setAdapter(adapter);

            spinner_taste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    menuChoiceTaste = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else{
            menuChoiceTaste = "";
        }


        // 담기, 주문하기
        Button order_put = v.findViewById(R.id.order_put);
        final Button order = v.findViewById(R.id.order);

        order_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // db 값 저장
                Map<String, Object> map = new HashMap<>();

                amount = Integer.parseInt(orderQuantity.getText().toString());
                String quantity = String.valueOf(amount);

                DatabaseReference databaseReference = firebaseDatabase.getReference(user.getUid());

                map.put("menuName", menuName);
                map.put("menuPrice", menuPrice);
                map.put("menuQuantity", quantity);
                map.put("menuChoiceTaste", menuChoiceTaste);

                databaseReference.push().setValue(map);

                CoffeeFragment coffeeFragment = new CoffeeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, coffeeFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderCorfirmFragment orderCorfirmFragment = new OrderCorfirmFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, orderCorfirmFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }

}

