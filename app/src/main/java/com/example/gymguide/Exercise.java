package com.example.gymguide;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Exercise implements Serializable {
    String exerciseName;
    String exerciseDescription;
    String exerciseID;
    String exerciseVideoURL;
    String exercisePhotoURL;
    String equipmentID;

    public Exercise() {};

    public Exercise(String exerciseName, String exerciseDescription, String exerciseID, String exerciseVideoURL, String exercisePhotoURL, String equipmentID) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseID = exerciseID;
        this.exerciseVideoURL = exerciseVideoURL;
        this.exercisePhotoURL = exercisePhotoURL;
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
    public String getExercisePhotoURL() {
        return exercisePhotoURL;
    }

    public void setExercisePhotoURL(String exercisePhotoURL) {
        this.exercisePhotoURL = exercisePhotoURL;
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
