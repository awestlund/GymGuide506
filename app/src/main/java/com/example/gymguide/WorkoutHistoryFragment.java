package com.example.gymguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutHistoryFragment extends Fragment {
    public static CompletedWorkoutsView compWorkoutsAdapter;
    FirebaseFirestore db;
    FirebaseAuth auth;

    public WorkoutHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        final CalendarView simpleCalendarView = (CalendarView) rootView.findViewById(R.id.calendarView); // get the reference of CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String today = "";
                if(String.valueOf(month).length() == 1){today = today + "0" + (month+1);}
                else{today = today + (month+1);}

                if(String.valueOf(dayOfMonth).length() == 1){today = today + "0" + dayOfMonth;}
                else{today = today + dayOfMonth;}

                today = today + year;

                if(auth.getCurrentUser() != null) {
                    final RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.days_completed_workouts_recyclerview);
                    compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    final List<Exercise> wh = new ArrayList<>();
                    compWorkoutsRV.setAdapter(null);
                    db.collection("workoutHistory").document(auth.getUid()).collection("History").document(today).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete( Task<DocumentSnapshot> task) {
                            DocumentSnapshot d = task.getResult();
                            List<String> exercises = (List<String>) d.get("exerciseID");
                            if (exercises != null){
                                for (String s : exercises) {
                                    DocumentReference docRef = db.collection("exercise").document(s);
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot ds = task.getResult();
                                            Exercise e = null;
                                            if (task.isSuccessful() && task != null) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    e = document.toObject(Exercise.class);
                                                    wh.add(e);
                                                }
                                                compWorkoutsAdapter = new CompletedWorkoutsView(getContext(), wh);
                                                compWorkoutsRV.setAdapter(compWorkoutsAdapter);
                                            }
                                        }
                                    });
                                }
                        }
                        }
                    });
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
