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
import android.widget.TextView;

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
    CompletedWorkoutsView compWorkoutsAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Date d = new Date();
        SimpleDateFormat day = new SimpleDateFormat("EEE, MMM d");
        TextView dateText = (TextView) rootView.findViewById(R.id.date_textview);
        dateText.setText(day.format(d));
        Button T=(Button)rootView.findViewById(R.id.qrcode_button);
        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),QRCodeActivity.class);
                startActivity(i);
            }
        });
        System.out.println("User ID: " + auth.getUid());
        if(auth.getCurrentUser() != null) {
            final RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.completed_workouts_recyclerview);
            compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            CollectionReference compWorkouts = db.collection("workoutHistory");
            compWorkouts.whereEqualTo("userID", auth.getUid());
            compWorkouts.whereEqualTo("userID", auth.getUid());
            db.collection("workoutHistory").whereEqualTo("userID", auth.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                System.out.println(task.getResult().size());
                                final List<Exercise> userCompExcerciseList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ArrayList<String> temp = (ArrayList<String>) document.get("exerciseID");
                                    for(int i=0; i<temp.size(); i++){
                                        String s = temp.get(i);
                                        final DocumentReference docRef = db.collection("exercise").document(s.trim());
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot doc = task.getResult();
                                                    Exercise e = new Exercise();
                                                    e.setExerciseVideoURL(doc.getData().get("exerciseVideoURL").toString());
                                                    e.setEquipmentID(doc.getData().get("equipmentID").toString());
                                                    e.setExerciseDescription(doc.getData().get("exerciseDescription").toString());
                                                    e.setExerciseName(doc.getData().get("exerciseName").toString());
                                                    userCompExcerciseList.add(e);
                                                    compWorkoutsAdapter = new CompletedWorkoutsView(getContext(), userCompExcerciseList);
                                                    compWorkoutsRV.setAdapter(compWorkoutsAdapter);
                                                }
                                            }
                                        });
                                    }
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
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Exercise e = new Exercise();
                                    e.setExerciseVideoURL(document.get("exerciseVideoURL").toString());
                                    usersRecWorkouts.add(e);
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
            //your codes here

        }
    }
}

