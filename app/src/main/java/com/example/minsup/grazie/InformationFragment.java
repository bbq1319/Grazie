package com.example.minsup.grazie;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InformationFragment extends Fragment {

    Button logout;
    TextView info_name, info_email;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_information, container, false);

        logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        info_name = v.findViewById(R.id.info_name);
        info_email = v.findViewById(R.id.info_email);

        info_name.setText(user.getDisplayName());
        info_email.setText(user.getEmail());

        return v;
    }
}
