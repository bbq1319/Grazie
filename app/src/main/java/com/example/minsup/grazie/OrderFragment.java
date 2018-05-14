package com.example.minsup.grazie;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


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
        TextView orderPrice = v.findViewById(R.id.orderPrice);

        String menuImage = getArguments().getString("menuImage");
        String menuName = getArguments().getString("menuName");
        String menuPrice = getArguments().getString("menuPrice");

        orderImage.setImageDrawable(Drawable.createFromPath(menuImage));
        orderName.setText(menuName);
        orderPrice.setText(menuPrice);

        return v;
    }

}
