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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends Fragment{
    public static CompletedWorkoutsView compWorkoutsAdapter;
    RecommendedWorkoutsView recWorkoutsAdapter;
    FirebaseFirestore db;
    FirebaseAuth auth;
    
    public HomeActivity() {
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
    public void onResume() {
        super.onResume();
        if(compWorkoutsAdapter != null){
            compWorkoutsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat year = new SimpleDateFormat("y");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String monthCaps = month.format(d).toString().toUpperCase();
        TextView dayText = (TextView) rootView.findViewById(R.id.day_textview);
        TextView monthText = (TextView) rootView.findViewById(R.id.month_textview);
        TextView yearText = (TextView) rootView.findViewById(R.id.year_textview);
        dayText.setText(day.format(d));
        monthText.setText(monthCaps);
        yearText.setText(year.format(d));
        ImageButton T = (ImageButton) rootView.findViewById(R.id.qrcode_button);
        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),QRCodeActivity.class);
                startActivity(i);
            }
        });



        if(auth.getCurrentUser() != null) {
            final RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.completed_workouts_recyclerview);
            compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            final List<Exercise> wh = new ArrayList<>();
            //start test change path
            db.collection("workoutHistory").document(auth.getUid()).collection("CurrentWorkout").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task != null) {
                                final List<Exercise> userCompExcerciseList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String s = document.getId();
                                    DocumentReference docRef = db.collection("exercise").document(s);
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot ds = task.getResult();
                                            Exercise e = null;
                                            if (task.isSuccessful() && task != null) {

                                                DocumentSnapshot doc = task.getResult();
                                                if (doc.exists()) {
                                                    e = doc.toObject(Exercise.class);
                                                    e.setExerciseID(doc.getId());
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

            final RecyclerView recWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.recommended_workouts_recyclerview);
            recWorkoutsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));

            final List<Exercise> usersRecWorkouts = new ArrayList<>();
            db.collection("exercise").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    try {
                                        Exercise e = doc.toObject(Exercise.class);
                                        e.setExerciseID(doc.getId());
                                        usersRecWorkouts.add(e);
                                    }
                                    catch (Exception ex){
                                        Toast.makeText(getContext(), "Error Loading some workouts", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                recWorkoutsAdapter = new RecommendedWorkoutsView(getContext(), usersRecWorkouts);
                                recWorkoutsRV.setAdapter(recWorkoutsAdapter);
                            } else {
                            }
                        }
                    });
        }
        else{
            RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.completed_workouts_recyclerview);
            compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            List<Exercise> compExcerciseList = new ArrayList<>();
            compWorkoutsAdapter = new CompletedWorkoutsView(getContext(), compExcerciseList);
            compWorkoutsRV.setAdapter(compWorkoutsAdapter);

            RecyclerView recWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.recommended_workouts_recyclerview);
            recWorkoutsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
            List<Exercise> recExcerciseList = new ArrayList<>();
            recWorkoutsAdapter = new RecommendedWorkoutsView(getContext(), recExcerciseList);
            recWorkoutsRV.setAdapter(recWorkoutsAdapter);
        }
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
        }
    }
}

