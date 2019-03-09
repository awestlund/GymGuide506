package com.example.gymguide;

import com.google.firebase.firestore.Exclude;

import java.util.List;

public class ReccomendedWorkouts {
    String workoutID;
    String workoutName;
    String workoutDescription;
    String workoutPhotoURL;
    String workoutDifficulty;
    List<String> exerciseID;
    List<String> category;

    public ReccomendedWorkouts() {};

    public ReccomendedWorkouts(String workoutID, String workoutName, String workoutDescription, String workoutPhotoURL, String workoutdifficulty, List<String> exerciseID, List<String> category) {
        this.workoutID = workoutID;
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.workoutPhotoURL = workoutPhotoURL;
        this.workoutDifficulty = workoutdifficulty;
        this.exerciseID = exerciseID;
        this.category = category;
    }

    @Exclude
    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public String getWorkoutPhotoURL() {
        return workoutPhotoURL;
    }

    public void setWorkoutPhotoURL(String workoutPhotoURL) {
        this.workoutPhotoURL = workoutPhotoURL;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutdifficulty) {
        this.workoutDifficulty = workoutdifficulty;
    }

    public List<String> getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(List<String> exerciseID) {
        this.exerciseID = exerciseID;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
