package com.example.gymguide;
import com.google.firebase.firestore.Exclude;

public class Exercise {
    String exerciseName;
    String exerciseDescription;
    String exerciseID;
    String exerciseVideoURL;
    String equipmentID;

    public Exercise() {};

    public Exercise(String exerciseName, String exerciseDescription, String exerciseID, String exerciseVideoURL, String equipmentID) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseID = exerciseID;
        this.exerciseVideoURL = exerciseVideoURL;
        this.equipmentID = equipmentID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    @Exclude
    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseVideoURL() {
        return exerciseVideoURL;
    }

    public void setExerciseVideoURL(String exerciseVideoURL) {
        this.exerciseVideoURL = exerciseVideoURL;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }
}
