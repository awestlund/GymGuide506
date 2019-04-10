package com.example.gymguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentActivity extends AppCompatActivity {

    Equipment equipment;
    private TextView equipmentName;
    private TextView description_textview;
    private ImageView imageView;
    FirebaseFirestore db;
    FirebaseAuth auth;
    RecommendedWorkoutsView equipmentWorkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        equipmentName = findViewById(R.id.equipmentName);
        description_textview = findViewById(R.id.description_textview);
        imageView = findViewById(R.id.imageView);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        equipment = (Equipment) getIntent().getSerializableExtra("equipment");

        equipmentName.setText(equipment.getEquipmentName());
        description_textview.setText(equipment.getEquipmentDescription());

        try {
            URL url = new URL(equipment.equipmentPhotoURL);
            InputStream in = new BufferedInputStream(url.openStream());
            Bitmap b = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(b);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        final RecyclerView equipmentWorkouts = (RecyclerView) findViewById(R.id.equipmentWorkouts);
        equipmentWorkouts.setLayoutManager(new GridLayoutManager(EquipmentActivity.this, 2, LinearLayoutManager.VERTICAL, false));

        final List<Exercise> equipmentWorkoutsList = new ArrayList<>();
        db.collection("exercise").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if ((doc.getData().get("equipmentID").toString()).equals(equipment.getEquipmentID())) {
                                    try {
                                        Exercise e = new Exercise();
                                        e.setExercisePhotoURL(doc.getData().get("exerciseVideoURL").toString());
                                        e.setEquipmentID(doc.getData().get("equipmentID").toString());
                                        e.setExerciseDescription(doc.getData().get("exerciseDescription").toString());
                                        e.setExerciseName(doc.getData().get("exerciseName").toString());
                                        e.setExerciseVideoURL((doc.getData().get("exercisePhotoURL")).toString());
                                        e.setExerciseID(doc.getId());
                                        equipmentWorkoutsList.add(e);
                                    } catch (Exception ex) {
                                        Toast.makeText(EquipmentActivity.this, "Error Loading some workouts", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    System.out.println("exercise " + doc.getData().get("exerciseName").toString());
                                    System.out.println("equipmentID " + doc.getData().get("equipmentID").toString());
                                    System.out.println("thisEquipment " + equipment.getEquipmentID());
                                    System.out.println();
                                }
                            }
                            equipmentWorkoutAdapter = new RecommendedWorkoutsView(EquipmentActivity.this, equipmentWorkoutsList);
                            equipmentWorkouts.setAdapter(equipmentWorkoutAdapter);
                        } else {
                            Toast.makeText(EquipmentActivity.this, "Error accessing database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
