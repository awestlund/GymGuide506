package com.example.gymguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class SingleExerciseActivity extends AppCompatActivity {

    Exercise e;
    ImageView workoutImage;
    VideoView videoPlayerView;
        DisplayMetrics dm;
        MediaController mc;
    private TextView qrText;
    FloatingActionButton fab;
    FirebaseFirestore db;
    FirebaseAuth auth;
    Button btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise_activity);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        e = (Exercise) getIntent().getSerializableExtra("exercise");
        workoutImage = findViewById(R.id.imageView);
        fab = findViewById(R.id.btnAddWorkout);
        btnVideo = findViewById(R.id.btn_video);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();

        TextView workoutName = (TextView) findViewById(R.id.exercise_title);
        workoutName.setText(e.getExerciseName());

        TextView workoutDescription = (TextView) findViewById(R.id.description_textview);
        workoutDescription.setText(Html.fromHtml(e.getExerciseDescription().toString()));

        try {
            URL url = new URL(e.getExercisePhotoURL());
            InputStream in = new BufferedInputStream(url.openStream());
            Bitmap b = BitmapFactory.decodeStream(in);
            workoutImage.setImageBitmap(b);
        } catch (Exception x) {
            x.printStackTrace();
        }

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(e.getExerciseVideoURL())));
                Log.i("Video", "Video Playing....");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getIntent().getSerializableExtra("exercise"));

                db.collection("workoutHistory").document(auth.getUid()).collection("CurrentWorkout").document(e.getExerciseID()).set(e);
            }
        });
    }


}
