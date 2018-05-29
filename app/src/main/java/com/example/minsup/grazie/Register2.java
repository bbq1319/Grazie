package com.example.minsup.grazie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

public class Register2 extends AppCompatActivity {

    private static final int PER_LOGIN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAllowNewEmailAccounts(true).build(), PER_LOGIN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PER_LOGIN)
        {
            handleSignInResponse(resultCode, data);
            return;
        }
    }

    private void handleSignInResponse(int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            Intent intent = new Intent(Register2.this, Status.class);
            startActivity(intent);
            finish();
            return;
        }
        else{
            Toast.makeText(this, "로그인을 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
