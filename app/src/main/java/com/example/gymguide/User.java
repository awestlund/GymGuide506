package com.example.gymguide;
import com.google.firebase.firestore.Exclude;

public class User {
    String userID;

    String userName;
    String email;
    String workoutGoal;
    Integer difficulty;
    String profilePictureURL;
    String[] category;

    public User(){};

    public User(String userID, String email, String workoutGoal, Integer difficulty, String profilePictureURL, String userName, String[] category) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.workoutGoal = workoutGoal;
        this.difficulty = difficulty;
        this.profilePictureURL = profilePictureURL;
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Exclude
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkoutGoal() {
        return workoutGoal;
    }

    public void setWorkoutGoal(String workoutGoal) {
        this.workoutGoal = workoutGoal;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }
}
