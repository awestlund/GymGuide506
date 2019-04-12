package com.example.gymguide;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;


import java.util.List;

public class WorkoutHistory  {
    String historyID;
    Timestamp workoutDate;
    List<String> exerciseID;
    String userID;

    public WorkoutHistory() {
        // Required empty public constructor
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
