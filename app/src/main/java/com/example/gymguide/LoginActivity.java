package com.example.gymguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordText, usernameText;
    private Button loginBtn, createAccBtn, guestBtn, rstPassword;
    private FirebaseAuth firebaseAuth;

    int VALID_INPUT = 0;
    int INVALID_INPUT = 1;
    int SIGN_IN_FAILED = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        passwordText = (EditText) findViewById(R.id.etPassword);
        usernameText = (EditText) findViewById(R.id.etEmail);
        loginBtn = (Button)findViewById(R.id.btn_login);
        createAccBtn = (Button)findViewById(R.id.btnCreateAccount);
        guestBtn = (Button)findViewById(R.id.btnGuest);
        rstPassword = (Button) findViewById(R.id.btnRstPass);

        firebaseAuth = FirebaseAuth.getInstance();
        // setting red title to be string "sign in"
        setTitle("Sign In");

        // getting the current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // if the current user isn't null redirect them to their home screen
//        if (user != null){
//            finish();
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }

        // try to login with info from usernameText and passwordText
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set username and password to the editText values
                String username = usernameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                // make sure all relevant fields are populated
                if (validateInput(username, password) != VALID_INPUT){
                    Toast.makeText(LoginActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (signInWithCredentials(username, password) == SIGN_IN_FAILED){
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });


        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity as guest
                firebaseAuth.signInAnonymously();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rstPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });

    }

    // check if username and password fields are not empty
    protected int validateInput(String username, String password) {

//        String username = usernameText.getText().toString().trim();
//        String password = passwordText.getText().toString().trim();
        if ((username.isEmpty()) || (password.isEmpty())) {
            return INVALID_INPUT;
        }
        return VALID_INPUT;
    }

    protected int signInWithCredentials(String username, String password){
        // try to sign in with credentials

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
        return SIGN_IN_FAILED;
    }


}
