package com.example.minsup.grazie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText id = findViewById(R.id.id);
        final EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        TextView registerButton = findViewById(R.id.registerButton);

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
//                LoginActivity.this.startActivity(registerIntent);
//            }
//        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, Register3.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(loginIntent);
            }
        });

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userId = id.getText().toString();
//                String userPassword = password.getText().toString();
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if(success){
//                                String userSno = jsonResponse.getString("userSno");
//                                String userName = jsonResponse.getString("userName");
//                                String userPhone = jsonResponse.getString("userPhone");
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("userSno", userSno);
//                                intent.putExtra("userName", userName);
//                                intent.putExtra("userPhone", userPhone);
//                                LoginActivity.this.startActivity(intent);
//                            }
//                            else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("로그인에 실패하였습니다.")
//                                        .setNegativeButton("다시 시도", null)
//                                        .create()
//                                        .show();
//                            }
//
//                        } catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                LoginRequest loginRequest = new LoginRequest(userId, userPassword, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
//
////                TextView information = (TextView) findViewById(R.id.information);
////                information.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        startActivity(new Intent(LoginActivity.this, Pop.class));
////                    }
////                });
//
//            }
//        });

    }
}
