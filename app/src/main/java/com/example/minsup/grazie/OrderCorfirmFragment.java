package com.example.minsup.grazie;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCorfirmFragment extends Fragment {


    public OrderCorfirmFragment() {
        // Required empty public constructor
    }

    ListView confirmListView;
    Button arrivalBtn;
    EditText arrivalEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_corfirm, container, false);

        confirmListView  = v.findViewById(R.id.confirmListView);
        arrivalBtn = v.findViewById(R.id.arrivalBtn);
        arrivalEdit = v.findViewById(R.id.arrivalEdit);

        arrivalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

}
