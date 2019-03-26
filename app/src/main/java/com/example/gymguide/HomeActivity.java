package com.example.gymguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymguide.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeActivity extends Fragment{
    private List<Exercise> mExcerciseList = new ArrayList<>();
    private List<Exercise> nExcerciseList = new ArrayList<>();
    CompletedWorkoutsView compWorkoutsAdapter;
    RecommendedWorkoutsView recWorkoutsAdapter;

    public HomeActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("EEE, MMM d");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        TextView dateText = (TextView) rootView.findViewById(R.id.date_textview);
        TextView timeText = (TextView) rootView.findViewById(R.id.time_textview);
        dateText.setText(day.format(d));
        timeText.setText(time.format(d));

        Button T=(Button)rootView.findViewById(R.id.qrcode_button);
        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getContext(),HomeActivity.class);
                startActivity(i);
            }
        });

        RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.completed_workouts_recyclerview);
        compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        for(int i=0; i<10; i++){
            Exercise e = new Exercise();
            e.setExerciseVideoURL("http://images.clipartpanda.com/number-one-clipart-847-blue-number-one-clip-art.png");
            mExcerciseList.add(e);
        }
        compWorkoutsAdapter = new CompletedWorkoutsView(getContext(), mExcerciseList);
        compWorkoutsRV.setAdapter(compWorkoutsAdapter);

        RecyclerView recWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.recommended_workouts_recyclerview);
        recWorkoutsRV.setLayoutManager(new GridLayoutManager(getActivity(),2 ,  LinearLayoutManager.VERTICAL, false));
        for(int i=0; i<10; i++){
            Exercise e = new Exercise();
            e.setExerciseVideoURL("http://images.clipartpanda.com/number-one-clipart-847-blue-number-one-clip-art.png");
            nExcerciseList.add(e);
        }
        recWorkoutsAdapter = new RecommendedWorkoutsView(getContext(), nExcerciseList);
        recWorkoutsRV.setAdapter(recWorkoutsAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }
}

