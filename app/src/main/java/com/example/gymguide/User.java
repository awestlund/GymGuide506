package com.example.gymguide;

import java.util.List;

public class User {
    String userID;
    String userName;
    String userEmail;
    String userWorkoutGoal;
    String workoutDifficulty;
    String userProfilePictureURL;
    List<String> workoutCategory;

    public User(String userID, String userName, String userEmail, String userWorkoutGoal, String workoutDifficulty, String userProfilePictureURL, List<String> workoutCategory) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userWorkoutGoal = userWorkoutGoal;
        this.workoutDifficulty = workoutDifficulty;
        this.userProfilePictureURL = userProfilePictureURL;
        this.workoutCategory = workoutCategory;

        // FIXME send user to database

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        //update database
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        // FIXME update database
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        // FIXME update database
    }

    public String getUserWorkoutGoal() {
        return userWorkoutGoal;
    }

    public void setUserWorkoutGoal(String userWorkoutGoal) {
        this.userWorkoutGoal = userWorkoutGoal;
        // FIXME update database
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
        // FIXME update database
    }

    public String getUserProfilePictureURL() {
        return userProfilePictureURL;
    }

    public void setUserProfilePictureURL(String userProfilePictureURL) {
        this.userProfilePictureURL = userProfilePictureURL;
        // FIXME update database
    }

    public List<String> getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(List<String> workoutCategory) {
        this.workoutCategory = workoutCategory;
        // FIXME update database
    }
}
