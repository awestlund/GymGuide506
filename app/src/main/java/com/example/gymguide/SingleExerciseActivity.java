package com.example.gymguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.FirestoreGrpc;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;


public class SingleExerciseActivity extends AppCompatActivity {

    Exercise e;
    ImageView workoutImage;
    private TextView qrText;
    FloatingActionButton fab;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise_activity);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        e = (Exercise) getIntent().getSerializableExtra("exercise");

        workoutImage = findViewById(R.id.workout_imageView);
        fab = findViewById(R.id.btnAddWorkout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView workoutName = (TextView) findViewById(R.id.exercise_title);
        workoutName.setText(e.getExerciseName());

        TextView workoutDescription = (TextView) findViewById(R.id.description_textview);
        workoutDescription.setText(Html.fromHtml(e.getExerciseDescription().toString()));

        try {
            URL url = new URL(e.getExerciseVideoURL());
            InputStream in = new BufferedInputStream(url.openStream());
            Bitmap b = BitmapFactory.decodeStream(in);
            workoutImage.setImageBitmap(b);
        } catch (Exception x) {
            x.printStackTrace();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutHistory wh = new WorkoutHistory();
                wh.setUserID(auth.getUid());
                Date now = new java.util.Date();
                Timestamp current = new java.sql.Timestamp(now.getTime());
                wh.setWorkoutDate(current);
                List<String> exerciseIDs = new ArrayList<>();
                exerciseIDs.add(e.getExerciseID());
                wh.setExerciseID(exerciseIDs);
                db.collection("workoutHistory").add(wh);
            }
        });
    }

}
