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
import android.widget.Toast;

import com.example.gymguide.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeActivity extends Fragment{
    private List<Exercise> userCompExcerciseList = new ArrayList<>();
    private List<Exercise> userRecExcerciseList = new ArrayList<>();
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
        if(auth.getCurrentUser() != null) {
            RecyclerView compWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.completed_workouts_recyclerview);
            compWorkoutsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

            db.collection("f ").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if(e !=null){
                        Log.w("Listen failed.", e);
                        return;
                    }
                    List<Exercise> usersCompWorkouts = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        Exercise tmp = (Exercise) doc.getData();
                        if(tmp !=null){
                            usersCompWorkouts.add(tmp);
                        }
                    }
                }
            });
            for (int i = 0; i < 10; i++) {
                Exercise e = new Exercise();
                e.setExerciseVideoURL("http://images.clipartpanda.com/number-one-clipart-847-blue-number-one-clip-art.png");
                userCompExcerciseList.add(e);
            }
            compWorkoutsAdapter = new CompletedWorkoutsView(getContext(), userCompExcerciseList);
            compWorkoutsRV.setAdapter(compWorkoutsAdapter);

            RecyclerView recWorkoutsRV = (RecyclerView) rootView.findViewById(R.id.recommended_workouts_recyclerview);
            recWorkoutsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
            db.collection("f ").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if(e !=null){
                        Log.w("Listen failed.", e);
                        return;
                    }
                    List<Exercise> usersRecWorkouts = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        Exercise tmp = (Exercise) doc.getData();
                        if(tmp !=null){
                            usersRecWorkouts.add(tmp);
                        }
                    }
                }
            });
            for (int i = 0; i < 10; i++) {
                Exercise e = new Exercise();
                e.setExerciseVideoURL("http://images.clipartpanda.com/number-one-clipart-847-blue-number-one-clip-art.png");
                userRecExcerciseList.add(e);
            }
            recWorkoutsAdapter = new RecommendedWorkoutsView(getContext(), userRecExcerciseList, new CustomItemClickListener(){
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(HomeActivity.this.getActivity(), "Clicked Item: "+position, Toast.LENGTH_SHORT).show();
                }
            });
            recWorkoutsRV.setAdapter(recWorkoutsAdapter);
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
            recWorkoutsAdapter = new RecommendedWorkoutsView(getContext(), userRecExcerciseList, new CustomItemClickListener(){
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(HomeActivity.this.getActivity(), "Clicked Item: "+position, Toast.LENGTH_SHORT).show();
                }
            });
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

