package com.example.gymguide;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    String userID;
    String userName;
    String userEmail;
    String userWorkoutGoal;
    String workoutDifficulty;
    String userProfilePictureURL;
    List<String> workoutCategory;

    public User(){};

    public User(String userID, String userName, String userEmail, String userWorkoutGoal, String workoutDifficulty, String userProfilePictureURL, List<String> workoutCategory) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userWorkoutGoal = userWorkoutGoal;
        this.workoutDifficulty = workoutDifficulty;
        this.userProfilePictureURL = userProfilePictureURL;
        this.workoutCategory = workoutCategory;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserWorkoutGoal() {
        return userWorkoutGoal;
    }

    public void setUserWorkoutGoal(String userWorkoutGoal) {
        this.userWorkoutGoal = userWorkoutGoal;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }

    public String getUserProfilePictureURL() {
        return userProfilePictureURL;
    }

    public void setUserProfilePictureURL(String userProfilePictureURL) {
        this.userProfilePictureURL = userProfilePictureURL;
    }

    public List<String> getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(List<String> workoutCategory) {
        this.workoutCategory = workoutCategory;
    }
}
