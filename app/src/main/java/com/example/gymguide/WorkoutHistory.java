package com.example.gymguide;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.util.List;

public class WorkoutHistory {
    String historyID;
    Timestamp workoutDate;
    List<String> exerciseID;
    String userID;

    public WorkoutHistory() {};

    public WorkoutHistory(String historyID, Timestamp workoutDate, List<String> exerciseID, String userID) {
        this.historyID = historyID;
        this.workoutDate = workoutDate;
        this.exerciseID = exerciseID;
        this.userID = userID;

        // FIXME send to database
    }

    @Exclude
    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
        // FIXME update database
    }

    public Timestamp getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Timestamp workoutDate) {
        this.workoutDate = workoutDate;
        // FIXME update database
    }

    public List<String> getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(List<String> exerciseID) {
        this.exerciseID = exerciseID;
        // FIXME update database
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        // FIXME update database
    }
}
