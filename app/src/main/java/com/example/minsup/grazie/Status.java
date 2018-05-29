package com.example.minsup.grazie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Status extends AppCompatActivity {

    TextView txt_email, txt_status, txt_uid;
    Button btn_send, btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        txt_email = findViewById(R.id.txt_email);
        txt_status = findViewById(R.id.txt_status);
        txt_uid = findViewById(R.id.txt_uid);

        btn_send = findViewById(R.id.btn_send);
        btn_refresh = findViewById(R.id.btn_refresh);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_send.setEnabled(true);

                                if(task.isSuccessful()){
                                    Toast.makeText(Status.this, "Verification email send to : " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Status.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                btn_refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().getCurrentUser()
                                .reload()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        setInfo();
                                    }
                                });
                    }
                });
            }
        });

        setInfo();
    }

    private void setInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        txt_email.setText(new StringBuilder("Email : ").append(user.getEmail()));
        txt_uid.setText(new StringBuilder("UID : ").append(user.getUid()));
        txt_status.setText(new StringBuilder("STATUS : ").append(user.getUid()));
    }
}
