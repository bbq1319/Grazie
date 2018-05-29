package com.example.minsup.grazie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register3 extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    private EditText phoneText;
    private EditText codeText;
    private Button verifyButton;
    private Button sendButton;
    private Button resendButton;
    private Button signoutButton;
    private TextView statusText;

    private String phoneVerificationID;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendingToken;

    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        phoneText = findViewById(R.id.phoneText);
        codeText = findViewById(R.id.codeText);
        verifyButton = findViewById(R.id.verifyButton);
        sendButton = findViewById(R.id.sendButton);
        resendButton = findViewById(R.id.resendButton);
        signoutButton = findViewById(R.id.signoutButton);
        statusText = findViewById(R.id.statusText);

        verifyButton.setEnabled(false);
        resendButton.setEnabled(false);
        signoutButton.setEnabled(false);
        statusText.setText("Signed Out");

        fbAuth = FirebaseAuth.getInstance();

    }

    public void sendCode(View view){

        String phoneNumber = phoneText.getText().toString();

        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, verificationStateChangedCallbacks);
    }

    private void setUpVerificationCallbacks(){

        verificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signoutButton.setEnabled(true);
                statusText.setText("Signed In");
                resendButton.setEnabled(false);
                verifyButton.setEnabled(false);
                codeText.setText("");
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if(e instanceof FirebaseAuthInvalidCredentialsException){
                    Log.d(TAG, "Invalid credential: " + e.getLocalizedMessage());
                } else if(e instanceof FirebaseTooManyRequestsException) {
                    Log.d(TAG, "SMS Quota exceeded.");
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token){
                phoneVerificationID = verificationId;
                resendingToken = token;

                verifyButton.setEnabled(true);
                sendButton.setEnabled(false);
                resendButton.setEnabled(true);
            }
        };
    }

    public void verifyCode(View view){
        String code = codeText.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationID, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            signoutButton.setEnabled(true);
                            codeText.setText("");
                            statusText.setText("Signed In");
                            resendButton.setEnabled(false);
                            verifyButton.setEnabled(false);
                            FirebaseUser user = task.getResult().getUser();
                        } else {
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(Register3.this,"Invalid Verification",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void resendCode(View view){
        String phoneNumber = phoneText.getText().toString();

        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, verificationStateChangedCallbacks, resendingToken);
    }

    public void signOut(View view){
        fbAuth.signOut();
        statusText.setText("Signed Out");
        signoutButton.setEnabled(false);
        sendButton.setEnabled(true);
    }
}
