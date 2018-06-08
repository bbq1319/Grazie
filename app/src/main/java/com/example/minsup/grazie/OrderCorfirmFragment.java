package com.example.minsup.grazie;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCorfirmFragment extends Fragment {


    public OrderCorfirmFragment() {
        // Required empty public constructor
    }

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    int amountPrice = 0;
    String orderName, orderPrice, orderQuantity, orderChoiceTaste;

    ListView confirmListView;
    ShoppingListViewAdapter adapter;

    Button arrivalBtn;
    EditText arrivalEdit;
    TextView amount_price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_corfirm, container, false);

        confirmListView  = v.findViewById(R.id.confirmListView);
        arrivalBtn = v.findViewById(R.id.arrivalBtn);
        arrivalEdit = v.findViewById(R.id.arrivalEdit);
        amount_price = v.findViewById(R.id.amount_price);

        // 리스트 보여주기
        adapter = new ShoppingListViewAdapter();
        confirmListView = v.findViewById(R.id.confirmListView);
        confirmListView.setAdapter(adapter);

        final DatabaseReference databaseReference = firebaseDatabase.getReference().child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot Data : dataSnapshot.getChildren()) {
                    orderName = Data.child("menuName").getValue().toString();
                    orderPrice = Data.child("menuPrice").getValue().toString();
                    orderQuantity = Data.child("menuQuantity").getValue().toString();
                    orderChoiceTaste = Data.child("menuChoiceTaste").getValue().toString();

                    String order_price = orderPrice.substring(0,4);
                    amountPrice = amountPrice + Integer.parseInt(order_price) * Integer.parseInt(orderQuantity);

                    amount_price.setText(String.valueOf(amountPrice) + "원");

                    adapter.addItem(orderName, orderPrice + " X " + orderQuantity, orderChoiceTaste);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // 주문 버튼
        if(!arrivalEdit.equals("")){
            arrivalBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "도착시간을 입력해주세요", Toast.LENGTH_SHORT).show();
        }


        // 삭제 버튼
        confirmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "asd", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
