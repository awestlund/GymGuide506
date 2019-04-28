package com.example.gymguide;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRCodeActivity extends AppCompatActivity {
    private Button scan_btn;
    private Button type_btn;
    private TextView text_input;
    private String qr_result = "";
    private String userInput = "";
    private FirebaseFirestore db;
    boolean isExercise = true;
    boolean isEquipment = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        db = FirebaseFirestore.getInstance();

        scan_btn = (Button) findViewById(R.id.scan_btn);
        type_btn = (Button) findViewById(R.id.type_btn);
        text_input = (TextView) findViewById(R.id.typedQR);

        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

        type_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                userInput = text_input.getText().toString().trim();
                onUserInput(userInput);
            }
        });

    }

    protected void onUserInput(String userInput){
        if (userInput != "") {

            qr_result = userInput;

            try {
                DocumentReference docRef = db.collection("exercise").document(qr_result);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Exercise e = null;
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                e = document.toObject(Exercise.class);
                                Intent gotoWorkoutActivityIntent = new Intent(QRCodeActivity.this, SingleExerciseActivity.class);
                                gotoWorkoutActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                gotoWorkoutActivityIntent.putExtra("exercise", e);
                                startActivity(gotoWorkoutActivityIntent);
                            } else {
                                isExercise = false;
                            }
                        } else {
                            Toast.makeText(QRCodeActivity.this, "get failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            } catch (Exception e) {
                //TODO
            }
            try {
                    DocumentReference dRef = db.collection("equipment").document(qr_result);
                    dRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            Equipment e = null;
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    e = document.toObject(Equipment.class);
                                    e.setEquipmentID(qr_result);
                                    Intent gotoEquipmentActivityIntent = new Intent(QRCodeActivity.this, EquipmentActivity.class);
                                    gotoEquipmentActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    gotoEquipmentActivityIntent.putExtra("equipment", e);
                                    startActivity(gotoEquipmentActivityIntent);
                                } else {
                                    isEquipment = false;
                                }
                            } else {
                                Toast.makeText(QRCodeActivity.this, "get failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            } catch (Exception e) {
                Toast.makeText(QRCodeActivity.this, "Incorrect Input", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();

            } else {
                    qr_result = result.getContents().trim();

                    try {
                        DocumentReference docRef = db.collection("exercise").document(qr_result);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                Exercise e = null;
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        e = document.toObject(Exercise.class);
                                        Intent gotoWorkoutActivityIntent = new Intent(QRCodeActivity.this, SingleExerciseActivity.class);
                                        gotoWorkoutActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        gotoWorkoutActivityIntent.putExtra("exercise", e);
                                        startActivity(gotoWorkoutActivityIntent);
                                    } else {
                                        isExercise = false;
                                    }
                                } else {
                                    Toast.makeText(QRCodeActivity.this, "get failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    } catch (Exception e) {
                        //TODO
                    }
                    try {
                            DocumentReference dRef = db.collection("equipment").document(qr_result);
                            dRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    Equipment e = null;
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            e = document.toObject(Equipment.class);
                                            e.setEquipmentID(qr_result);
                                            Intent gotoEquipmentActivityIntent = new Intent(QRCodeActivity.this, EquipmentActivity.class);
                                            gotoEquipmentActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            gotoEquipmentActivityIntent.putExtra("equipment", e);
                                            startActivity(gotoEquipmentActivityIntent);
                                        } else {
                                            isEquipment = false;
                                        }
                                    } else {
                                        Toast.makeText(QRCodeActivity.this, "get failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                        //TODO
                    }

                }
            }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


