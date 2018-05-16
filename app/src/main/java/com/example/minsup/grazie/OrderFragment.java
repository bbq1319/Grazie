package com.example.minsup.grazie;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    int amount = 1;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        ImageView orderImage = v.findViewById(R.id.orderImage);
        TextView orderName = v.findViewById(R.id.orderName);
        TextView orderTaste = v.findViewById(R.id.orderTaste);
        TextView orderEngName = v.findViewById(R.id.orderEngName);
        TextView orderPrice = v.findViewById(R.id.orderPrice);

        Bitmap menuImage = getArguments().getParcelable("menuImage");
        String menuName = getArguments().getString("menuName");
        String menuTaste = getArguments().getString("menuTaste");
        String menuEngName = getArguments().getString("menuEngName");
        String menuPrice = getArguments().getString("menuPrice");

        orderImage.setImageBitmap(menuImage);
        orderName.setText(menuName);
        orderTaste.setText(menuTaste);
        orderEngName.setText(menuEngName);
        orderPrice.setText(menuPrice);



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

        Spinner spinner_arrival = v.findViewById(R.id.taste_spinner);
        spinner_arrival.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }

}
