package com.example.gymguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import static java.lang.Thread.sleep;


public class SingleExerciseActivity extends AppCompatActivity {

    Exercise e;
    ImageView workoutImage;
    private TextView qrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise_activity);

        e = (Exercise) getIntent().getSerializableExtra("exercise");

        workoutImage = findViewById(R.id.workout_imageView);

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
    }

}
