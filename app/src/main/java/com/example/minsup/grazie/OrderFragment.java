package com.example.minsup.grazie;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
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

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    int amount = 1;

    public OrderFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        // 주문정보
        final ImageView orderImage = v.findViewById(R.id.orderImage);
        TextView orderName = v.findViewById(R.id.orderName);
        TextView orderTaste = v.findViewById(R.id.orderTaste);
        TextView orderEngName = v.findViewById(R.id.orderEngName);
        TextView orderPrice = v.findViewById(R.id.orderPrice);

        final Bitmap menuImage = getArguments().getParcelable("menuImage");
        final String menuName = getArguments().getString("menuName");
        final String menuTaste = getArguments().getString("menuTaste");
        final String menuEngName = getArguments().getString("menuEngName");
        final String menuPrice = getArguments().getString("menuPrice");

        orderImage.setImageBitmap(menuImage);
        orderName.setText(menuName);
        orderTaste.setText(menuTaste);
        orderEngName.setText(menuEngName);
        orderPrice.setText(menuPrice);


        // 수량체크
        final TextView quantity = v.findViewById(R.id.orderQuantity);
        Button order_plus = v.findViewById(R.id.orderPlus);
        Button order_minus = v.findViewById(R.id.orderMinus);

        quantity.setText("" + amount);

        order_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = Integer.parseInt(quantity.getText().toString()) + 1;
                quantity.setText("" + amount);
            }
        });

        order_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount <= 1){
                    Toast.makeText(getActivity().getApplicationContext(), "1개 이상 주문하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    amount = Integer.parseInt(quantity.getText().toString()) - 1;
                    quantity.setText("" + amount);
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

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }


        // 담기, 주문하기
        Button order_put = v.findViewById(R.id.order_put);
        Button order = v.findViewById(R.id.order);

        order_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                menuImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();

                Intent intent = new Intent(getActivity(), ShoppingBasket.class);
                intent.putExtra("orderImage", bytes);
                intent.putExtra("orderName", menuName);
                intent.putExtra("orderPrice", menuPrice);
                startActivityForResult(intent, 1);

//                CoffeeFragment coffeeFragment = new CoffeeFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.content, coffeeFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

}

