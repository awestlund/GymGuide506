package com.example.gymguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WorkoutListActivity extends AppCompatActivity {
    private TextView qrText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        qrText = findViewById(R.id.tvQrResult);

        Bundle b  = getIntent().getExtras();
        String qr_result = "";
        if (b != null){
            qr_result = b.getString("key");
            qrText.setText(qr_result);
        }
    }
}
