package com.example.gymguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.util.List;

public class WorkoutHistory extends Fragment {
    String historyID;
    Timestamp workoutDate;
    List<String> exerciseID;
    String userID;

    public WorkoutHistory() {
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
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public WorkoutHistory(String historyID, Timestamp workoutDate, List<String> exerciseID, String userID) {
        this.historyID = historyID;
        this.workoutDate = workoutDate;
        this.exerciseID = exerciseID;
        this.userID = userID;
    }

    @Exclude
    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public Timestamp getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Timestamp workoutDate) {
        this.workoutDate = workoutDate;
    }

    public List<String> getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(List<String> exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
