package com.example.gymguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;


public class SingleExerciseActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setContentView(R.layout.activity_single_exercise_activty);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Exercise e = (Exercise) getIntent().getSerializableExtra("exercise");
        System.out.println(e.getExerciseVideoURL());
        TextView workoutName = (TextView) findViewById(R.id.exercise_title);
        workoutName.setText(e.getExerciseName());

        TextView workoutDescription = (TextView) findViewById(R.id.description_textview);
        workoutDescription.setText(e.getExerciseDescription());

        ImageView workoutImage = findViewById(R.id.workout_image);
        try {
            URL url = new URL(e.getExerciseVideoURL());
            InputStream in = new BufferedInputStream(url.openStream());
            Bitmap b = BitmapFactory.decodeStream(in);
            workoutImage.setImageBitmap(b);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
