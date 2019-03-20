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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText passwordText, usernameText, nameText;
    private Button createAccBtn;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = FirebaseFirestore.getInstance();

        passwordText = (EditText) findViewById(R.id.etPassword);
        usernameText = (EditText) findViewById(R.id.etEmail);
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
                final String username = usernameText.getText().toString().trim(); // this is email
                final String password = passwordText.getText().toString().trim();
                final String name = nameText.getText().toString().trim();

                // make sure all relevant fields are populated
                if (!validateInput()) {
                    return;
                }
                // try to create a new user with the given credentials
                firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firebaseAuth.getCurrentUser().getUid(), name, username, "", "", "", null );

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
                        }
                        else if (password.length() < 6) {
                            Toast.makeText(CreateAccountActivity.this, "Password must 6 or more characters.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(CreateAccountActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    // check if username and password fields are not empty
    private boolean validateInput() {
        String username = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String name = nameText.getText().toString().trim();
        if ((username.isEmpty()) || (password.isEmpty()) || (name.isEmpty())) {
            Toast.makeText(CreateAccountActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
