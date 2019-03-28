package com.example.gymguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.gymguide.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText passwordText, emailText, nameText;
    private Button createAccBtn;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    int VALID_INPUT = 0;
    int INVALID_INPUT = 1;
    int PASSWORD_SHORT = 2;
    int CREATE_ACCOUNT_FAILED = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = FirebaseFirestore.getInstance();

        passwordText = (EditText) findViewById(R.id.etPassword);
        emailText = (EditText) findViewById(R.id.etEmail);
        nameText =     (EditText) findViewById(R.id.etName);
        createAccBtn = (Button)findViewById(R.id.btnCreateAcc);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set username and password to the editText values
                final String email = emailText.getText().toString().trim();
                final String password = passwordText.getText().toString().trim();
                final String name = nameText.getText().toString().trim();


                // make sure all relevant fields are populated
                if (validateInput(email, password, name) != VALID_INPUT) {
                    Toast.makeText(CreateAccountActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int status = createAccount(email, password, name);
                if (status == 2){
                    Toast.makeText(CreateAccountActivity.this, "Password must 6 or more characters.", Toast.LENGTH_SHORT).show();
                }
                if (status == 3){
                    Toast.makeText(CreateAccountActivity.this, "Create account failed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    // check if username and password fields are not empty
    protected int validateInput(String email, String password, String name) {

        if ((email.isEmpty()) || (password.isEmpty()) || (name.isEmpty())) {
            return INVALID_INPUT;
        }
        return VALID_INPUT;
    }

    protected int createAccount(final String email, final String password, final String name){
        // try to create a new user with the given credentials

        if (password.length() < 6) {
            return PASSWORD_SHORT;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(firebaseAuth.getCurrentUser().getUid(), name, email, "", "", "", null );

                    db.collection("users").document(user.getUserID())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("create account", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("create account", "Error writing document", e);
                                }
                            });

                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(CreateAccountActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return CREATE_ACCOUNT_FAILED;
    }
}
